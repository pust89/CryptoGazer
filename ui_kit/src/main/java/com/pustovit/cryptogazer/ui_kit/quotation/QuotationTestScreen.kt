package com.pustovit.cryptogazer.ui_kit.quotation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuotationTestScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val viewModel = remember { QuotationTestViewModel() }

        val list: List<ListItemState> by viewModel.items.collectAsState()
        val selectedIds by viewModel.selectedItemIds.collectAsState()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {

            items(
                items = list,
                key = { it.id },
                itemContent = { item ->
                    ListItem(
                        state = item,
                        onEvent = viewModel::handleEvent,
                       // modifier = Modifier.animateItemPlacement() // Плавные анимации
                    )
                }
            )
        }
    }
}

internal class QuotationTestViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<ListItemState>>(emptyList())
    val items = _items.asStateFlow()

    private val _selectedItemIds = MutableStateFlow<Set<String>>(emptySet())
    val selectedItemIds = _selectedItemIds.asStateFlow()

    init {
        loadItems()
    }

    private fun loadItems() {
        _items.value = List(10) { index ->
            ListItemState(
                id = "item_$index",
                title = "Item $index",
                subtitle = if (index % 2 == 0) "Even item" else null,
                leadingIcon = if (index % 3 == 0) {
                    ListItemIcon.Vector(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Featured"
                    )
                } else {
                    ListItemIcon.Vector(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Document"
                    )
                },
                trailingActions = listItemActions(
                    listItemAction(
                        id = "edit",
                        icon = Icons.Default.Edit,
                        contentDescription = "Edit"
                    ),
                    listItemAction(
                        id = "delete",
                        icon = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Red
                    )
                ),
                selectionMode = SelectionMode.Multiple
            )
        }
    }

    fun handleEvent(event: ListItemEvent) {
        when (event) {
            is ListItemEvent.ItemClicked -> {
                // Handle item click
                println("Item clicked: ${event.itemId}")
            }

            is ListItemEvent.ActionClicked -> {
                when (event.actionId) {
                    "edit" -> editItem(event.itemId)
                    "delete" -> deleteItem(event.itemId)
                }
            }

            is ListItemEvent.ItemSelected -> {
                updateSelection(event.itemId, event.selected)
            }
        }
    }

    private fun updateSelection(itemId: String, selected: Boolean) {
        _selectedItemIds.update { current ->
            if (selected) {
                current + itemId
            } else {
                current - itemId
            }
        }

        // Обновляем состояние isSelected в элементах
        _items.update { items ->
            items.map { item ->
                if (item.id == itemId) {
                    item.copy(isSelected = selected)
                } else {
                    item
                }
            }
        }
    }

    private fun editItem(id: String) {
        // Edit logic
    }

    private fun deleteItem(id: String) {
        _items.update { items ->
            items.filter { it.id != id }
        }
        _selectedItemIds.update { it - id }
    }
}
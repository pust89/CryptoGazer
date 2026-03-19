package com.pustovit.cryptogazer.ui_kit.quotation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Состояние элемента списка.
 * Все поля immutable для оптимальной рекомпозиции.
 *
 * @param id Уникальный идентификатор элемента
 * @param title Заголовок (обязательный)
 * @param subtitle Подзаголовок (опционально)
 * @param leadingIcon Иконка слева (опционально)
 * @param trailingActions Действия справа (опционально)
 * @param backgroundColor Цвет фона элемента
 * @param isEnabled Доступен ли элемент для взаимодействия
 * @param isSelected Выбран ли элемент
 * @param selectionMode Режим выбора элемента
 */
@Immutable
data class ListItemState(
    val id: String,
    val title: String,
    val subtitle: String? = null,
    val leadingIcon: ListItemIcon? = null,
    val trailingActions: List<ListItemAction> = emptyList(),
    val backgroundColor: Color = Color.Transparent,
    val isEnabled: Boolean = true,
    val isSelected: Boolean = false,
    val selectionMode: SelectionMode = SelectionMode.None
) {
    val isSelectable: Boolean
        get() = selectionMode != SelectionMode.None
}

/**
 * Режим выбора элемента
 */
enum class SelectionMode {
    None,           // Нельзя выбрать
    Single,         // Одиночный выбор
    Multiple        // Множественный выбор
}

/**
 * Иконка элемента списка
 */
@Immutable
sealed class ListItemIcon {
    data class Vector(
        val imageVector: ImageVector,
        val contentDescription: String?,
        val tint: Color = Color.Unspecified,
        val size: Dp = 24.dp
    ) : ListItemIcon()

    data class Resource(
        @androidx.annotation.DrawableRes val id: Int,
        val contentDescription: String?,
        val tint: Color = Color.Unspecified,
        val size: Dp = 24.dp
    ) : ListItemIcon()

    data class Custom(
        val content: @Composable () -> Unit
    ) : ListItemIcon()
}

/**
 * Действие в элементе списка (кнопка)
 */
@Immutable
data class ListItemAction(
    val id: String,
    val icon: ListItemIcon,
    val contentDescription: String?,
    val enabled: Boolean = true,
    val tint: Color = Color.Unspecified
)

/**
 * События, которые может генерировать элемент списка
 */
sealed class ListItemEvent {
    data class ItemClicked(val itemId: String) : ListItemEvent()
    data class ActionClicked(val itemId: String, val actionId: String) : ListItemEvent()
    data class ItemSelected(val itemId: String, val selected: Boolean) : ListItemEvent()
}

/**
 * Компонент элемента списка с поддержкой:
 * - Различных состояний (выбран, неактивен)
 * - Иконки слева
- Текста (заголовок + опциональный подзаголовок)
 * - Кнопок действий справа
 * - Кастомного фона
 * - Обработки кликов с идентификацией элемента и действия
 *
 * @param state Состояние элемента
 * @param onEvent Колбэк для обработки событий
 * @param modifier Модификатор
 * @param shape Форма элемента
 * @param elevation Тень
 * @param horizontalPadding Горизонтальные отступы
 * @param verticalPadding Вертикальные отступы
 * @param interactionSource Для кастомизации ripple эффекта
 */
@Composable
fun ListItem(
    state: ListItemState,
    onEvent: (ListItemEvent) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(12.dp),
    elevation: Dp = 0.dp,
    horizontalPadding: Dp = 16.dp,
    verticalPadding: Dp = 12.dp,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val backgroundColor by animateColorAsState(
        targetValue = when {
            !state.isEnabled -> state.backgroundColor.copy(alpha = 0.5f)
            state.isSelected -> MaterialTheme.colorScheme.primaryContainer
            else -> state.backgroundColor
        },
        label = "background_color"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .let {
                if (state.isEnabled) {
                    it.clickable(
                        interactionSource = interactionSource,
                        indication = LocalIndication.current,
                        role = if (state.isSelectable) Role.Checkbox else Role.Button,
                        onClick = {
                            when {
                                state.isSelectable -> {
                                    onEvent(ListItemEvent.ItemSelected(
                                        itemId = state.id,
                                        selected = !state.isSelected
                                    ))
                                }
                                else -> {
                                    onEvent(ListItemEvent.ItemClicked(state.id))
                                }
                            }
                        }
                    )
                } else {
                    it
                }
            },
        shape = shape,
        elevation = CardDefaults.cardElevation(elevation),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = horizontalPadding,
                    vertical = verticalPadding
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Leading Icon
            state.leadingIcon?.let { icon ->
                Box(
                    modifier = Modifier.size(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    ListItemIconContent(
                        icon = icon,
                        enabled = state.isEnabled
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))
            }

            // Text Content
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = state.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (state.isEnabled)
                        MaterialTheme.colorScheme.onSurface
                    else
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                state.subtitle?.let {
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (state.isEnabled)
                            MaterialTheme.colorScheme.onSurfaceVariant
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Trailing Actions
            if (state.trailingActions.isNotEmpty()) {
                Spacer(modifier = Modifier.width(8.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    state.trailingActions.forEach { action ->
                        TrailingAction(
                            action = action,
                            itemId = state.id,
                            onEvent = onEvent,
                            enabled = state.isEnabled && action.enabled
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ListItemIconContent(
    icon: ListItemIcon,
    enabled: Boolean
) {
    val alpha = if (enabled) 1f else 0.38f

    when (icon) {
        is ListItemIcon.Vector -> {
            Icon(
                imageVector = icon.imageVector,
                contentDescription = icon.contentDescription,
                tint = icon.tint.copy(alpha = alpha),
                modifier = Modifier.size(icon.size)
            )
        }
        is ListItemIcon.Resource -> {
            Icon(
                painter = painterResource(id = icon.id),
                contentDescription = icon.contentDescription,
                tint = icon.tint.copy(alpha = alpha),
                modifier = Modifier.size(icon.size)
            )
        }
        is ListItemIcon.Custom -> {
            icon.content()
        }
    }
}

@Composable
private fun TrailingAction(
    action: ListItemAction,
    itemId: String,
    onEvent: (ListItemEvent) -> Unit,
    enabled: Boolean
) {
    val interactionSource = remember { MutableInteractionSource() }

    IconButton(
        onClick = {
            onEvent(ListItemEvent.ActionClicked(itemId, action.id))
        },
        enabled = enabled,
        interactionSource = interactionSource
    ) {
        when (action.icon) {
            is ListItemIcon.Vector -> {
                Icon(
                    imageVector = action.icon.imageVector,
                    contentDescription = action.contentDescription,
                    tint = action.tint
                )
            }
            is ListItemIcon.Resource -> {
                Icon(
                    painter = painterResource(id = action.icon.id),
                    contentDescription = action.contentDescription,
                    tint = action.tint
                )
            }
            is ListItemIcon.Custom -> {
                action.icon.content()
            }
        }
    }
}

/**
 * Функция-хелпер для создания списка действий
 */
fun listItemActions(vararg actions: ListItemAction): List<ListItemAction> {
    return listOf(*actions)
}

/**
 * Функция-хелпер для создания действия
 */
fun listItemAction(
    id: String,
    icon: ImageVector,
    contentDescription: String? = null,
    enabled: Boolean = true,
    tint: Color = Color.Unspecified
): ListItemAction {
    return ListItemAction(
        id = id,
        icon = ListItemIcon.Vector(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tint
        ),
        contentDescription = contentDescription,
        enabled = enabled,
        tint = tint
    )
}

/**
 * Функция-хелпер для создания действия из ресурса
 */
fun listItemAction(
    id: String,
    @androidx.annotation.DrawableRes iconRes: Int,
    contentDescription: String? = null,
    enabled: Boolean = true,
    tint: Color = Color.Unspecified
): ListItemAction {
    return ListItemAction(
        id = id,
        icon = ListItemIcon.Resource(
            id = iconRes,
            contentDescription = contentDescription,
            tint = tint
        ),
        contentDescription = contentDescription,
        enabled = enabled,
        tint = tint
    )
}

// PREVIEWS
@Preview(showBackground = true)
@Composable
private fun ListItemPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ListItem(
                state = ListItemState(
                    id = "1",
                    title = "John Doe",
                    subtitle = "Online",
                    leadingIcon = ListItemIcon.Vector(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile"
                    ),
                    trailingActions = listItemActions(
                        listItemAction(
                            id = "chat",
                            icon = Icons.Default.Email,
                            contentDescription = "Chat"
                        ),
                        listItemAction(
                            id = "call",
                            icon = Icons.Default.Call,
                            contentDescription = "Call"
                        )
                    )
                ),
                onEvent = { event ->
                    when (event) {
                        is ListItemEvent.ItemClicked -> println("Item clicked: ${event.itemId}")
                        is ListItemEvent.ActionClicked -> println("Action ${event.actionId} on item ${event.itemId}")
                        is ListItemEvent.ItemSelected -> println("Item ${event.itemId} selected: ${event.selected}")
                    }
                }
            )

            ListItem(
                state = ListItemState(
                    id = "2",
                    title = "Selected Item",
                    subtitle = "This item is selected",
                    isSelected = true,
                    leadingIcon = ListItemIcon.Vector(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Selected"
                    )
                ),
                onEvent = {}
            )

            ListItem(
                state = ListItemState(
                    id = "3",
                    title = "Disabled Item",
                    subtitle = "This item is disabled",
                    isEnabled = false,
                    leadingIcon = ListItemIcon.Vector(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Disabled"
                    )
                ),
                onEvent = {}
            )
        }
    }
}
package com.pustovit.cryptogazer.ui_kit.modifier

import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics

fun Modifier.scaleClick(
    enabled: Boolean = true,
    semanticsRole: Role = Role.Button,
    click: () -> Unit
): Modifier = composed {
    if (!enabled) return@composed this.semantics { role = semanticsRole }

    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = if (isPressed) {
            tween(durationMillis = 100, easing = EaseOut)
        } else {
            tween(
                delayMillis = 100,
                durationMillis = 200, easing = EaseOut
            )
        }
    )
    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .pointerInput(Unit) {
            awaitEachGesture {
                val down = awaitFirstDown(requireUnconsumed = false)
                down.consume()

                isPressed = true

                val upOrCancel = try {
                    waitForUpOrCancellation()
                } finally {
                    isPressed = false
                }

                if (upOrCancel != null) {
                    upOrCancel.consume()
                    click.invoke()
                }
            }
        }
        .semantics { role = semanticsRole }
}
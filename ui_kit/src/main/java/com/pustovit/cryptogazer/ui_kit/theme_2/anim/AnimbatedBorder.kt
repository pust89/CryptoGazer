package com.pustovit.cryptogazer.ui_kit.theme_2.anim

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun Modifier.animatedBorder(
    borderWidth: Dp = 2.dp,
    shape: Shape = RoundedCornerShape(16.dp),
    color: Color = Color.Green,
    durationMillis: Int = 2000,
    segmentFraction: Float = 0.3f,
    fadeSteps: Int = 100,
): Modifier = composed {

    val transition = rememberInfiniteTransition(label = "")
    val progress by transition.animateFloat(
        1f, 0f,
        animationSpec = infiniteRepeatable(tween(durationMillis, easing = LinearEasing)),
        label = ""
    )

    drawWithCache {
        val outline = shape.createOutline(size, layoutDirection, this)
        val path = Path().apply { addOutline(outline) }
        val pm = PathMeasure().apply { setPath(path, forceClosed = false) }
        val length = pm.length
        val stroke = borderWidth.toPx()

        onDrawWithContent {
            drawContent()

            val head = progress * length
            val tail = head + length * segmentFraction
            val segmentLen = length * segmentFraction

            // Рисуем подсегменты с изменяющейся прозрачностью
            for (i in 0..fadeSteps) {
                val t1 = i.toFloat() / fadeSteps      // 0..1 по длине сегмента
                val t2 = (i + 1).toFloat() / fadeSteps

                // Прозрачность: плавно растёт до середины, потом падает
                val alpha = when {
                    t1 < 0.5f -> 2f * t1          // 0 -> 1
                    else -> 2f * (1f - t1)        // 1 -> 0
                }.coerceIn(0f, 1f)

                val start = head + segmentLen * t1
                val end = head + segmentLen * t2

                val subPath = Path()
                val startNorm = start % length
                val endNorm = end % length

                if (startNorm <= endNorm) {
                    pm.getSegment(startNorm, endNorm, subPath, true)
                } else {
                    pm.getSegment(startNorm, length, subPath, true)
                    pm.getSegment(0f, endNorm, subPath, true)
                }

                drawPath(
                    path = subPath,
                    color = color.copy(alpha = alpha),
                    style = Stroke(width = stroke, cap = StrokeCap.Round)
                )
            }
        }
    }
}
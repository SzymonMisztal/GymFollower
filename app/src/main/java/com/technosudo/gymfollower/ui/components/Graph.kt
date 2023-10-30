package com.technosudo.gymfollower.ui.components

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.technosudo.gymfollower.data.ProgressData
import kotlin.math.round

@Composable
fun Graph(
    modifier: Modifier = Modifier,
    data: List<ProgressData> = emptyList(),
    labels: Boolean = true,
    graphColor: Color = Color.Green,
    backgroundColor: Color? = null
) {
    val spacing = if(labels) 100f else 0f
    val transparentGraphColor = remember {
        graphColor.copy(alpha = 0.5f)
    }
    val upperValue = remember(data) {
        data.maxBy { it.weight }.weight + 10
    }
    val lowerValue = remember(data) {
        data.minBy { it.weight }.weight - 10
    }
    val density = LocalDensity.current
    val textPaint = remember(density) {
        Paint().apply {
            color = android.graphics.Color.WHITE
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }
    Canvas(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val spacePerHour = (size.width - spacing) / (data.size - 1)
        val priceStep = (upperValue - lowerValue) / 5

        if(backgroundColor != null) {
            drawRect(
                color = backgroundColor,
                topLeft = Offset(spacing, 0f),
                size = Size(size.width - spacing, size.height - spacing)
            )
        }

        if(labels) {
            data.indices.forEach { i ->
                val record = data[i]
                val x = record.date.dayOfWeek.value
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        x.toString(),
                        spacing + i * spacePerHour,
                        size.height - 5,
                        textPaint
                    )
                }
            }
            (0..4).forEach { i ->
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        (lowerValue + priceStep * i).toString(),
                        30f,
                        size.height - spacing - i * size.height / 5f,
                        textPaint
                    )
                }
            }
        }

        var lastX = 0f
        var lastY = 0f

        val strokePath = Path().apply {

            val height = size.height

            for(i in data.indices) {

                val record = data[i]
                val ratio = (record.weight - lowerValue).toFloat() / (upperValue - lowerValue)

                val x = spacing + i * spacePerHour
                val y = height - spacing - (ratio * height)

                if(i == 0) {
                    moveTo(x, y)
                } else {
                    relativeCubicTo(
                        dx1 = spacePerHour / 2f,
                        dy1 = 0f,
                        dx2 = spacePerHour / 2f,
                        dy2 = y - lastY,
                        dx3 = spacePerHour,
                        dy3 = y - lastY
                    )
                }
                lastX = x
                lastY = y
            }
        }
        val fillPath = android.graphics.Path(strokePath.asAndroidPath())
            .asComposePath()
            .apply {
                lineTo(lastX, size.height - spacing)
                lineTo(spacing, size.height - spacing)
                close()
            }
        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    transparentGraphColor,
                    Color.Transparent
                ),
                endY = size.height - spacing
            )
        )
        drawPath(
            path = strokePath,
            color = graphColor,
            style = Stroke(
                width = 3.dp.toPx(),
                cap = StrokeCap.Round
            )
        )
    }
}
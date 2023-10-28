package com.technosudo.gymfollower.ui.components

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
    graphColor: Color = Color.Green
) {
    val spacing = 100f
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
        val spacePerHour = (size.width - spacing) / data.size
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
        val priceStep = (upperValue - lowerValue) / 5
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

private fun coordinates(
    record: ProgressData,
    lastRecord: ProgressData,
    max: Int,
    min: Int
) {
    val x = 0

}

//@Composable
//fun LineChart(
//    modifier: Modifier = Modifier,
//    data: List<ProgressData>,
//    graphColor: Color,
//    showDashedLine: Boolean,
//    showYLabels: Boolean = false
//) {
//
//    val spacing = 0f
//    val transparentGraphColor = remember(key1 = graphColor) {
//        graphColor.copy(alpha = 0.5f)
//    }
//
//    val (lowerValue, upperValue) = remember(key1 = data) {
//        Pair(
//            data.minBy { it.weight },
//            data.maxBy { it.weight }
//        )
//    }
//
//    val density = LocalDensity.current
//
//    Canvas(modifier = modifier) {
//
//        val spacePerHour = (size.width - spacing) / data.size
//        var lastX = 0f
//        var firstY = 0f
//        val strokePath = Path().apply {
//            val height = size.height
//            for (i in data.indices) {
//                val info = data[i]
//                val nextInfo = data.getOrNull(i + 1) ?: data.last()
//                val leftRatio =
//                    (info.weight - lowerValue.weight) / (upperValue.weight - lowerValue.weight)
//                val rightRatio =
//                    (nextInfo.weight - lowerValue.weight) / (upperValue.weight - lowerValue.weight)
//
//                val x1 = spacing + i * spacePerHour
//                val y1 = height - spacing - (leftRatio * height).toFloat()
//
//                if (i == 0) {
//                    firstY = y1
//                }
//
//                val x2 = spacing + (i + 1) * spacePerHour
//                val y2 = height - spacing - (rightRatio * height).toFloat()
//                if (i == 0) {
//                    moveTo(x1, y1)
//                }
//                lastX = (x1 + x2) / 2f
//                quadraticBezierTo(
//                    x1, y1, lastX, (y1 + y2) / 2f
//                )
//            }
//        }
//
//        val fillPath = android.graphics.Path(strokePath.asAndroidPath())
//            .asComposePath()
//            .apply {
//                lineTo(lastX, size.height - spacing)
//                lineTo(spacing, size.height - spacing)
//                close()
//            }
//
//        drawPath(
//            path = fillPath,
//            brush = Brush.verticalGradient(
//                colors = listOf(
//                    transparentGraphColor,
//                    Color.Transparent
//                ),
//                endY = size.height - spacing
//            ),
//        )
//
//        drawPath(
//            path = strokePath,
//            color = graphColor,
//            style = Stroke(
//                width = 2.dp.toPx(),
//                cap = StrokeCap.Round
//            )
//        )
//
//        if (showDashedLine) {
//            val dottedPath = Path().apply {
//                moveTo(0f, firstY)
//                lineTo(lastX, firstY)
//            }
//
//            drawPath(
//                path = dottedPath,
//                color = graphColor.copy(alpha = .8f),
//                style = Stroke(
//                    width = 1.5.dp.toPx(),
//                    cap = StrokeCap.Round,
//                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 20f), 0f)
//                )
//            )
//        }
//
//        if (showYLabels) {
//            val textPaint = Paint().apply {
//                color = Color(0xFFEBEBEB).toArgb()
//                textAlign = Paint.Align.RIGHT
//                textSize = density.run { 12.dp.toPx() }
//                typeface = setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD))
//                alpha = 192
//            }
//
//            drawContext.canvas.nativeCanvas.apply {
//                drawText(
//                    "MAX ${upperValue.weight}",
//                    size.width - 16.dp.toPx(),
//                    0 + 8.dp.toPx(),
//                    textPaint
//                )
//                drawText(
//                    "MIN ${lowerValue.weight}",
//                    size.width - 16.dp.toPx(),
//                    size.height - 4.dp.toPx(),
//                    textPaint
//                )
//            }
//        }
//    }
//}
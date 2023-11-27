package com.technosudo.gymfollower.ui.components

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
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
import com.technosudo.gymfollower.data.Period
import com.technosudo.gymfollower.data.ProgressData
import com.technosudo.gymfollower.ui.theme.RedNormal
import java.time.temporal.WeekFields
import java.util.Locale
import kotlin.math.round

@Composable
fun Graph(
    modifier: Modifier = Modifier,
    data: List<ProgressData> = emptyList(),
    periodType: Period = Period.Weeks,
    labels: Boolean = true,
    dashedLines: Boolean = true,
    graphColor: Color = Color.Green,
    backgroundColor: Color? = null
) {
    if(data.isEmpty()) {
        TextMedium(
            modifier = Modifier.fillMaxWidth(),
            text = "No data"
        )
        return
    }

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
        val spacePerHour = (size.width - spacing) / (data.size + if(data.size == 1) 1 else 0 - 1)
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
                val x = when(periodType) {
                    Period.Days -> record.date.dayOfWeek.toString().substring(0, 3)
                    Period.Weeks -> record.date.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear())
                    Period.Months -> record.date.month
                    Period.Years -> record.date.year
                }
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        x.toString(),
                        if(data.size == 1) (size.width + spacing) / 2 else spacing + i * spacePerHour,
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
                val ratio = (record.weight - lowerValue).toFloat() / (upperValue - lowerValue).toFloat()

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
                if(data.size == 1) {
                    relativeLineTo(size.width - spacing, 0f)
                    lastX = size.width
                }
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

        if(dashedLines) {
            val dashedLine = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            val ratio = (data.last().weight - lowerValue).toFloat() / (upperValue - lowerValue).toFloat()
            drawLine(
                color = RedNormal,
                start = Offset(spacing, size.height - spacing - (ratio * size.height)),
                end = Offset(size.width, size.height - spacing - (ratio * size.height)),
                pathEffect = dashedLine
            )
        }

//        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
//        Canvas(Modifier.fillMaxWidth().height(1.dp)) {
//
//            drawLine(
//                color = Color.Red,
//                start = Offset(0f, 0f),
//                end = Offset(size.width, 0f),
//                pathEffect = pathEffect
//            )
//        }
    }
}
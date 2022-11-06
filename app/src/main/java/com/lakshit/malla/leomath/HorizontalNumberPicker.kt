
package com.lakshit.malla.leomath
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

/**
 * A composable that generates a rounded triangle.
 * @param isLeftOrRight If true, its right facing or else its left facing traingle.
 */

@Composable
fun TriangleIndicators(modifier: Modifier = Modifier, isLeftOrRight:Boolean, triangleColor: Color = Color.Gray){
    Canvas(modifier = modifier
        .aspectRatio(1.2f)
        .rotate(if (isLeftOrRight) 90f else -90f)) {
        val rect = Rect(Offset.Zero, size)
        val trianglePath = Path().apply {
            moveTo(rect.topCenter.x, rect.topCenter.y)
            lineTo(rect.bottomRight.x, rect.bottomRight.y)
            lineTo(rect.bottomLeft.x, rect.bottomLeft.y)
            close()
        }

        drawIntoCanvas { canvas ->
            canvas.drawOutline(
                outline = Outline.Generic(trianglePath),
                paint = Paint().apply {
                    color = triangleColor
                    pathEffect = PathEffect.cornerPathEffect(rect.maxDimension / 3)
                }
            )
        }
    }
}

private fun <T> getItemIndexForOffset(
    range: List<T>,
    value: T,
    offset: Float,
    halfNumbersColumnHeightPx: Float
): Int {
    val indexOf = range.indexOf(value) - (offset / halfNumbersColumnHeightPx).toInt()
    return maxOf(0, minOf(indexOf, range.count() - 1))
}

@Composable
fun <T> ListItemPicker(
    modifier: Modifier = Modifier,
    label: (T) -> String = { it.toString() },
    value: T,
    onValueChange: (T) -> Unit,
    list: List<T>,
    textStyle: TextStyle = LocalTextStyle.current,
) {
    val minimumAlpha = 0.3f
    val verticalMargin = 8.dp
    val numbersColumnHeight = 80.dp
    val halfNumbersColumnHeight = numbersColumnHeight / 2
    val halfNumbersColumnHeightPx = with(LocalDensity.current) { halfNumbersColumnHeight.toPx() }

    val coroutineScope = rememberCoroutineScope()

    val animatedOffset = remember { Animatable(0f) }
        .apply {
            val index = list.indexOf(value)
            val offsetRange = remember(value, list) {
                -((list.count() - 1) - index) * halfNumbersColumnHeightPx to
                        index * halfNumbersColumnHeightPx
            }
            updateBounds(offsetRange.first, offsetRange.second)
        }

    val coercedAnimatedOffset = animatedOffset.value % halfNumbersColumnHeightPx

    val indexOfElement =
        getItemIndexForOffset(list, value, animatedOffset.value, halfNumbersColumnHeightPx)


    Layout(
        modifier = modifier
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { deltaX ->
                    coroutineScope.launch {
                        animatedOffset.snapTo(animatedOffset.value + deltaX)
                    }
                },
                onDragStopped = { velocity ->
                    coroutineScope.launch {
                        val endValue = animatedOffset.fling(
                            initialVelocity = velocity,
                            animationSpec = exponentialDecay(frictionMultiplier = 20f),
                            adjustTarget = { target ->
                                val coercedTarget = target % halfNumbersColumnHeightPx
                                val coercedAnchors =
                                    listOf(
                                        -halfNumbersColumnHeightPx,
                                        0f,
                                        halfNumbersColumnHeightPx
                                    )
                                val coercedPoint =
                                    coercedAnchors.minByOrNull { abs(it - coercedTarget) }!!
                                val base =
                                    halfNumbersColumnHeightPx * (target / halfNumbersColumnHeightPx).toInt()
                                coercedPoint + base
                            }
                        ).endState.value

                        val result = list.elementAt(
                            getItemIndexForOffset(list, value, endValue, halfNumbersColumnHeightPx)
                        )
                        onValueChange(result)
                        animatedOffset.snapTo(0f)
                    }
                }
            )
            .padding(horizontal = numbersColumnHeight / 1 + verticalMargin),
        content = {

            Box(
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = verticalMargin)
                    .offset { IntOffset(x = coercedAnimatedOffset.roundToInt(), y = 0) }
            ) {
                val baseLabelModifier = Modifier.align(Alignment.Center)
                ProvideTextStyle(textStyle) {
                    if (indexOfElement > 1)
                        Label(
                            text = label(list.elementAt(indexOfElement - 2)),
                            modifier = baseLabelModifier
                                .offset(x = -(halfNumbersColumnHeight * 2))
                                .alpha(
                                    maxOf(
                                        minimumAlpha,
                                        coercedAnimatedOffset / halfNumbersColumnHeightPx
                                    )
                                )
                        )
                    if (indexOfElement > 0)
                        Label(
                            text = label(list.elementAt(indexOfElement - 1)),
                            modifier = baseLabelModifier
                                .offset(x = -halfNumbersColumnHeight)
                                .alpha(
                                    maxOf(
                                        minimumAlpha,
                                        coercedAnimatedOffset / halfNumbersColumnHeightPx
                                    )
                                )
                        )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TriangleIndicators(modifier = Modifier.width(6.dp),isLeftOrRight = true, triangleColor = textStyle.color)
                        Spacer(modifier = Modifier.width(4.dp))
                        Label(
                            text = label(list.elementAt(indexOfElement)),
                            modifier = baseLabelModifier
                                .alpha(
                                    (maxOf(
                                        minimumAlpha,
                                        1 - abs(coercedAnimatedOffset) / halfNumbersColumnHeightPx
                                    ))
                                )
                        )
                        Spacer(modifier = Modifier.width(6.dp))

                        TriangleIndicators(modifier = Modifier.width(6  .dp),isLeftOrRight = false, triangleColor = textStyle.color)

                    }
                    if (indexOfElement < list.count() - 1)
                        Label(
                            text = label(list.elementAt(indexOfElement + 1)),
                            modifier = baseLabelModifier
                                .offset(x = halfNumbersColumnHeight)
                                .alpha(
                                    maxOf(
                                        minimumAlpha,
                                        -coercedAnimatedOffset / halfNumbersColumnHeightPx
                                    )
                                )
                        )
                    if (indexOfElement < list.count() - 2)
                        Label(
                            text = label(list.elementAt(indexOfElement + 2)),
                            modifier = baseLabelModifier
                                .offset(x = halfNumbersColumnHeight * 2)
                                .alpha(
                                    maxOf(
                                        minimumAlpha,
                                        -coercedAnimatedOffset / halfNumbersColumnHeightPx
                                    )
                                )
                        )
                }
            }
        }
    ) { measurables, constraints ->
        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.map { measurable ->
            // Measure each children
            measurable.measure(constraints)
        }


        // Set the size of the layout as big as it can
        layout(placeables.sumOf { it.width }, placeables
            .maxOf {
                it.height
            }
        ) {
            // Track the y co-ord we have placed children up to
            var yPosition = 0

            // Place children in the parent layout
            placeables.forEach { placeable ->

                // Position item on the screen
                placeable.placeRelative(x = yPosition, y = 0)

                // Record the y co-ord placed up to
                yPosition += placeable.width
            }
        }
    }
}

@Composable
private fun Label(text: String, modifier: Modifier) {
    Text(
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures(onLongPress = {
                // FIXME: Empty to disable text selection
            })
        },
        text = text,

        textAlign = TextAlign.Center,
        fontSize = 20.sp
    )
}

private suspend fun Animatable<Float, AnimationVector1D>.fling(
    initialVelocity: Float,
    animationSpec: DecayAnimationSpec<Float>,
    adjustTarget: ((Float) -> Float)?,
    block: (Animatable<Float, AnimationVector1D>.() -> Unit)? = null,
): AnimationResult<Float, AnimationVector1D> {
    val targetValue = animationSpec.calculateTargetValue(value, initialVelocity)
    val adjustedTarget = adjustTarget?.invoke(targetValue)
    return if (adjustedTarget != null) {
        animateTo(
            targetValue = adjustedTarget,
            initialVelocity = initialVelocity,
            block = block
        )
    } else {
        animateDecay(
            initialVelocity = initialVelocity,
            animationSpec = animationSpec,
            block = block,
        )
    }
}

@Preview
@Composable
fun NumberPickerPreview() {
    var rememberValue by remember {
        mutableStateOf(5)
    }
    ListItemPicker(
        value = rememberValue,
        onValueChange = { rememberValue = it },
        list = (3..8).toList(), textStyle = TextStyle(color = Color.Black.copy(alpha = 0.7f))
    )
}

@Preview
@Composable
fun TriangleInficatorPreview(){
    Row {
        TriangleIndicators(modifier = Modifier.width(20.dp),isLeftOrRight = false)
        TriangleIndicators(modifier = Modifier.width(20.dp), isLeftOrRight = true)
    }
}
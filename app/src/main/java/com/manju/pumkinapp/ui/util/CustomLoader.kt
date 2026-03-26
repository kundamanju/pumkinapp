package com.manju.pumkinapp.ui.util

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun AppLoader(
    modifier: Modifier = Modifier,
    text: String = "Please wait",
    showLogo: Boolean = false,
    logo: Painter? = null,
    color: Color = Color(0xFFFAFAFA)
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Optional Logo
        if (showLogo && logo != null) {
            Image(
                painter = logo,
                contentDescription = "App Logo",
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        // 🌀 Bubble Loader (Big → Small)
        BubbleTrailLoader(
            bubbleColor = color,
            bubbleCount = 7,
            maxBubbleSize = 10.dp,
            minBubbleSize = 3.dp,
            radius = 22.dp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // ✨ Animated Text
        AnimatedLoadingText(
            baseText = text,
            color = color
        )
    }
}

@Composable
fun AnimatedLoadingText(
    baseText: String,
    color: Color
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val alpha1 by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(600)),
        label = ""
    )

    val alpha2 by infiniteTransition.animateFloat(
        0.2f, 1f,
        infiniteRepeatable(tween(600, delayMillis = 200)),
        label = ""
    )
    val alpha3 by infiniteTransition.animateFloat(
        0.2f, 1f,
        infiniteRepeatable(tween(600, delayMillis = 400)),
        label = ""
    )

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = baseText,
            fontSize = 16.sp,
            color = color
        )
        Text(".", modifier = Modifier.alpha(alpha1), color = color)
        Text(".", modifier = Modifier.alpha(alpha2), color = color)
        Text(".", modifier = Modifier.alpha(alpha3), color = color)
    }
}

@Composable
fun BubbleTrailLoader(
    bubbleColor: Color,
    bubbleCount: Int,
    maxBubbleSize: Dp,
    minBubbleSize: Dp,
    radius: Dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val rotation by infiniteTransition.animateFloat(
        0f, 360f,
        infiniteRepeatable(tween(1400, easing = LinearEasing)),
        label = ""
    )

    Box(
        modifier = Modifier.size(radius * 2),
        contentAlignment = Alignment.Center
    ) {
        repeat(bubbleCount) { index ->

            val fraction = index.toFloat() / (bubbleCount - 1)
            val size = maxBubbleSize - (maxBubbleSize - minBubbleSize) * fraction
            val alpha = 1f - (0.7f * fraction)
            val angle = (360f / bubbleCount) * index

            Box(
                modifier = Modifier
                    .rotate(rotation)
                    .graphicsLayer {
                        val rad = Math.toRadians(angle.toDouble())
                        translationX = (radius.toPx() * cos(rad)).toFloat()
                        translationY = (radius.toPx() * sin(rad)).toFloat()
                    }
                    .size(size)
                    .clip(CircleShape)
                    .background(bubbleColor.copy(alpha = alpha))
            )
        }
    }
}
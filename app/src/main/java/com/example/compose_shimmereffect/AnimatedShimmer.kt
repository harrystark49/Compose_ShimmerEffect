package com.example.compose_shimmereffect

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedShimmer() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.Blue.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )
    var transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(initialValue =0f, targetValue =1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        ))

    val brush=Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(translateAnim.value,y = translateAnim.value)
    )
   shimmerGridItem(brush = brush) 
}

@Composable

fun shimmerGridItem(brush:Brush){
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Spacer(modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(brush))
        Spacer(modifier = Modifier.padding(10.dp))

        Column(verticalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush)
                .fillMaxSize(fraction = 0.7f))

            Spacer(modifier = Modifier.height(5.dp))

            Spacer(modifier = Modifier
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush)
                .fillMaxSize(fraction = 0.9f))
        }
    }
}
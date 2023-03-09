package com.example.scrollcompose

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scrollcompose.ui.theme.ScrollComposeTheme

@Composable
fun MoveTextView() {
    var columnHeightDp by remember {
        mutableStateOf(0.dp)
    }
    var visible by remember { mutableStateOf(true) }
    val iconOffsetAnimation: Dp by animateDpAsState(
        if (visible) 13.dp else 0.dp, tween(1000)
    )
    val textOffsetAnimation: Dp by animateDpAsState(
        if (visible) 6.dp else 0.dp, tween(1000)
    )
    val viewAlpha: Float by animateFloatAsState(
        targetValue = if (visible) 1f else 0f, animationSpec = tween(
            durationMillis = 1000,
        )
    )
    val heightInDp: Dp by animateDpAsState(
        targetValue = if (visible) {
            columnHeightDp
        } else {
            0.dp
        }, animationSpec = tween(
            durationMillis = 1000,
        )
    )

    MoveTextStateLess(
        { columnHeightDp },
        onColumnHeightDp = {
            columnHeightDp = it
        },
        { heightInDp },
        iconOffsetAnimation,
        viewAlpha,
        textOffsetAnimation,
        visible,
        onVisibleChange = {
            visible = it
        }
    )
}

@Composable
fun MoveTextStateLess(
    columnHeightDp: () -> Dp,
    onColumnHeightDp: (Dp) -> Unit,
    heightInDp: () -> Dp,
    iconOffsetAnimation: Dp,
    viewAlpha: Float,
    textOffsetAnimation: Dp,
    visible: Boolean,
    onVisibleChange: (Boolean) -> Unit,
) {
    val density = LocalDensity.current
    ScrollComposeTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp)
        ) {
            Column(
                modifier =
                if (columnHeightDp() != 0.dp) {
                    Modifier
                        .height(heightInDp())
                } else {
                    Modifier
                        .onSizeChanged {
                            with(density) {
                                onColumnHeightDp(it.height.toDp())
                            }
                        }
                        .wrapContentHeight()
                }.background(Color.LightGray)
            ) {
                Image(
                    modifier = Modifier.graphicsLayer { translationY = iconOffsetAnimation.toPx() },
                    alpha = viewAlpha,
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null,
                )
                Text(
                    modifier = Modifier.graphicsLayer { translationY = textOffsetAnimation.toPx() },
                    text = "Hello, Anna",
                    fontSize = 20.sp,
                    color = Color.Black.copy(alpha = viewAlpha),
                )
            }
            Button(
                modifier = Modifier.padding(top = 10.dp),
                onClick = {
                    onVisibleChange(!visible)
                },
            ) {
                Text(text = "Move Text")
            }
        }
    }
}
package com.example.scrollcompose

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
private fun PaddingViewAnimation() {
    var visible by remember { mutableStateOf(true) }
    val iconOffsetAnimation: Dp by animateDpAsState(
        if (visible) 13.dp else 0.dp, tween(1000)
    )
    PaddingViewAnimationStateLess(
        { iconOffsetAnimation },
        visible,
        onVisibleChange = {
            visible = it
        }
    )
}

@Composable
fun PaddingViewAnimationStateLess(
    iconOffsetAnimation: () -> Dp,
    visible: Boolean,
    onVisibleChange: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp)
    ) {
        Image(
            modifier = Modifier.graphicsLayer { translationY = iconOffsetAnimation().toPx() },
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = null,
        )
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
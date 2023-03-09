package com.example.scrollcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scrollcompose.ui.theme.ScrollComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollComposeTheme {
//                CollapsingTopAppBarStylingScreen()
//                MoveTextView()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CollapsingTopAppBarStylingScreen() {
    var isBackDropAnimationStarts by remember { mutableStateOf(false) }
    val iconOffsetAnimation: Dp by animateDpAsState(
        if (!isBackDropAnimationStarts) 13.dp else 0.dp, tween(1000)
    )
    val textOffsetAnimation: Dp by animateDpAsState(
        if (!isBackDropAnimationStarts) 6.dp else 0.dp, tween(1000)
    )
    val viewAlpha: Float by animateFloatAsState(
        targetValue = if (!isBackDropAnimationStarts) 1f else 0f, animationSpec = tween(
            durationMillis = 1000,
        )
    )
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    BackdropScaffold(
        scaffoldState = scaffoldState,
        appBar = {},
        persistentAppBar = false,
        peekHeight = 0.dp,
        backLayerContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp, top = 16.dp, bottom = 10.dp
                    ),
            ) {
                Image(
                    modifier = Modifier.padding(top = iconOffsetAnimation),
                    alpha = viewAlpha,
                    imageVector = Icons.Default.ShoppingCart,
                    colorFilter = ColorFilter.tint(color = Color.White),
                    contentDescription = null,
                )
                Text(
                    modifier = Modifier.padding(top = textOffsetAnimation),
                    text = "Hello, Anna",
                    fontSize = 20.sp,
                    color = Color.White.copy(alpha = viewAlpha),
                )
            }
        },
        backLayerBackgroundColor = Color.DarkGray,
        frontLayerBackgroundColor = Color.White,
        frontLayerScrimColor = Color.Transparent,
        frontLayerContent = {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val list = (0..40).map { it.toString() }
                items(count = list.size) {
                    Text(
                        text = list[it],
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        color = Color.Black,
                    )
                }
            }
        })
}

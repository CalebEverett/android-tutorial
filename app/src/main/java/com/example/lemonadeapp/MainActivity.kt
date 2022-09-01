package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() { LemonImageAndText(modifier = Modifier ) }


@Composable
fun LemonImageAndText(modifier: Modifier = Modifier) {
    class Resources(val text: Int, val image: Int, val content: Int) {}
    var result by remember { mutableStateOf( 1) }
    var requiredSqueezes by remember { mutableStateOf( 1) }
    var squeezes by remember { mutableStateOf( 1) }

    fun onImageClick() {
        when(result) {
            1 -> {
                result++
                requiredSqueezes = (2..4).random()
                squeezes = 1
            }
            2 -> if (squeezes == requiredSqueezes) {
                result++
            } else {
                squeezes++
            }
            3 -> result++
            else -> result = 1
        }
    }
    val resources = when(result) {
        1 -> Resources(R.string.tap, R.drawable.lemon_tree, R.string.lemon_tree)
        2 -> Resources(R.string.squeeze, R.drawable.lemon_squeeze, R.string.lemon)
        3 -> Resources(R.string.drink, R.drawable.lemon_drink, R.string.glass_of_lemonade)
        else -> Resources(R.string.restart, R.drawable.lemon_restart, R.string.empty_glass)
    }

    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = stringResource(id = resources.text))
        Image(
            painter = painterResource(id = resources.image),
            contentDescription = stringResource(id = resources.content),
            modifier = modifier.padding(16.dp).border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
            shape = RoundedCornerShape(4.dp)
        ).wrapContentSize().clickable(onClick = {onImageClick()}),

        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeAppTheme {
        LemonadeApp()
    }
}
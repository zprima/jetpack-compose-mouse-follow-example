package com.example.mousefollower

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mousefollower.ui.MouseFollowerTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MouseFollowerTheme(darkTheme = true) {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App(){
    val verticalOffset = remember { mutableStateOf(550f) }
    val horizontalOffset = remember { mutableStateOf(600f) }
    val pointerDown = remember { mutableStateOf(false) }

    val density = AmbientDensity.current

    Log.d("mijaPD", "${pointerDown.value}")
    Log.d("mijaY", "${verticalOffset.value} ${verticalOffset.value / density.density}")
    Log.d("mijaX", "${horizontalOffset.value} ${horizontalOffset.value / density.density}")

    Box(modifier = Modifier.fillMaxSize(1f).background(color = Color.LightGray)){
        
        Box(modifier = Modifier.size(100.dp).offset(
            x = ((horizontalOffset.value / density.density).dp - 50.dp),
            y = ((verticalOffset.value / density.density).dp - 100.dp)
        ).background(color = if(pointerDown.value) Color.Green else Color.Black).pointerInteropFilter {
            if(it.action == MotionEvent.ACTION_MOVE){
                horizontalOffset.value = it.rawX
                verticalOffset.value = it.rawY
                pointerDown.value = true
            }
            else{
                pointerDown.value = false
            }
            true
        })
    }
}


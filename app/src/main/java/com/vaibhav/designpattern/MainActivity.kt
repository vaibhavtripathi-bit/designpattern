package com.vaibhav.designpattern

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.vaibhav.designpattern.ui.theme.DesignPatternTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DesignPatternTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    var currentScreen by remember { mutableStateOf("home") }

    when (currentScreen) {
        "home" -> {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text("Design Patterns Demo", style = MaterialTheme.typography.headlineMedium)
                Button(onClick = { currentScreen = "mediator" }, modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
                    Text("Mediator Pattern (Profile)")
                }
            }
        }
        "mediator" -> {
            // Back button handling could be added here or inside the screen
            Column {
                Button(onClick = { currentScreen = "home" }) { Text("Back to Home") }
                com.vaibhav.designpattern.dpscreens.mediator.MediatorHomeScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // Legacy placeholder
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DesignPatternTheme {
        Greeting("Android")
    }
}
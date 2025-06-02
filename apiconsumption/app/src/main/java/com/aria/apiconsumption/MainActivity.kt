package com.aria.apiconsumption

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aria.apiconsumption.ui.screen.MainScreen
import com.aria.apiconsumption.ui.theme.ApiConsumptionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiConsumptionTheme {
                MainScreen()
            }
        }
    }
}

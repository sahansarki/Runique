package com.plcoding.wear.app.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.plcoding.wear.run.presentation.TrackerScreenRoot

class MainActivity : androidx.activity.ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            com.plcoding.core.presentation.designsystem_wear.RuniqueTheme {
                TrackerScreenRoot()
            }
        }
    }
}
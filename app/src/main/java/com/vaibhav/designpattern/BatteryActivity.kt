package com.vaibhav.designpattern

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.vaibhav.designpattern.battery.worker.BatteryWorker
import java.util.concurrent.TimeUnit
import com.vaibhav.designpattern.battery.data.repository.BatteryRepositoryImpl
import com.vaibhav.designpattern.battery.domain.usecase.GetTopBatteryUsageUseCase
import com.vaibhav.designpattern.battery.presentation.BatteryScreen
import com.vaibhav.designpattern.battery.presentation.BatteryViewModel

class BatteryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scheduleBatteryTracking()
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent()
                }
            }
        }
    }

    @Composable
    fun MainContent() {
        var hasPermission by remember { mutableStateOf(checkUsageStatsPermission()) }
        val lifecycleOwner = LocalLifecycleOwner.current

        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) {
                    hasPermission = checkUsageStatsPermission()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }

        if (hasPermission) {
            val context = this
            val viewModel: BatteryViewModel = viewModel(
                factory = viewModelFactory {
                    initializer {
                        val repository = BatteryRepositoryImpl(context)
                        val useCase = GetTopBatteryUsageUseCase(repository)
                        // BatteryTriggerManager will be created with default argument
                        BatteryViewModel(application, useCase, repository)
                    }
                }
            )
            
            LaunchedEffect(Unit) {
                viewModel.loadBatteryUsage()
            }

            BatteryScreen(
                viewModel = viewModel,
                onRefresh = { viewModel.loadBatteryUsage() }
            )
        } else {
            PermissionScreen {
                startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
            }
        }
    }

    private fun checkUsageStatsPermission(): Boolean {
        val appOps = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            android.os.Process.myUid(),
            packageName
        )
        return mode == AppOpsManager.MODE_ALLOWED
    }

    private fun scheduleBatteryTracking() {
        val workRequest = PeriodicWorkRequestBuilder<BatteryWorker>(1, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "BatteryTrackingWork",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}

@Composable
fun PermissionScreen(onGrantClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Usage Stats Permission Required")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onGrantClick) {
                Text("Grant Permission")
            }
        }
    }
}

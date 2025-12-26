package com.vaibhav.designpattern.dpscreens.mediator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.vaibhav.designpattern.dpscreens.mediator.bad.BadProfileScreen
import com.vaibhav.designpattern.dpscreens.mediator.good.GoodProfileScreen

@Composable
fun MediatorHomeScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Bad (Coupled)", "Good (Mediator)")

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title) }
                )
            }
        }

        when (selectedTab) {
            0 -> BadProfileScreen()
            1 -> GoodProfileScreen()
        }
    }
}

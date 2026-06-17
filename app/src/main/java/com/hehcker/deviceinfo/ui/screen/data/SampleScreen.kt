@file:OptIn(ExperimentalMaterial3Api::class)

package com.hehcker.deviceinfo.ui.screen.data

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.hehcker.deviceinfo.ui.component.header.DataScreenHeader
import com.hehcker.deviceinfo.ui.component.list.InfoListItem
import com.hehcker.deviceinfo.ui.theme.CustomColors.topBarColors

@Composable
fun SampleScreen(onBack: () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    LaunchedEffect(Unit) { scrollBehavior.state.heightOffset = scrollBehavior.state.heightOffsetLimit }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DataScreenHeader(
                title = ":",
                onBack = onBack,
                scrollBehavior = scrollBehavior
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) { innerPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier
                .background(topBarColors.containerColor)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            contentPadding = innerPadding
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    InfoListItem(
                        label = "This",
                        value = "is item!!",
                        items = 2, index = 0
                    )
                    InfoListItem(
                        label = "and",
                        value = "another one",
                        items = 2, index = 1
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

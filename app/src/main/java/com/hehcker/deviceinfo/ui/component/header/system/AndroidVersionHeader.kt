package com.hehcker.deviceinfo.ui.component.header.system

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hehcker.deviceinfo.R
import com.hehcker.deviceinfo.ui.theme.CustomColors.listItemColors
import com.hehcker.deviceinfo.ui.theme.WRShapeDefaults.middleListItemShape
import com.hehcker.deviceinfo.ui.theme.WRShapeDefaults.topListItemShape

@Composable
fun AndroidVersionHeader(
    version: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(topListItemShape)
            .fillMaxWidth()
            .background(listItemColors.containerColor)
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(
                    id = R.drawable.ic_android
                ),
                null,
                modifier = Modifier
                    .height(48.dp)
                    .clip(middleListItemShape)
            )
            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Android $version",
                style = typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
package com.hehcker.deviceinfo.ui.component.header.device

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
import com.hehcker.deviceinfo.ui.theme.CustomColors.listItemColors
import com.hehcker.deviceinfo.ui.theme.WRShapeDefaults.middleListItemShape
import com.hehcker.deviceinfo.ui.theme.WRShapeDefaults.topListItemShape

@Composable
fun DeviceHeader(
    model: String, marketingName: String?,
    brandIconRes: Int,
    modifier: Modifier = Modifier
) {
    val modelText = marketingName
        ?.takeIf { it.isNotBlank() }
        ?: model

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(topListItemShape)
            .fillMaxWidth()
            .background(listItemColors.containerColor)
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (brandIconRes != 0) {
                Image(
                    painterResource(
                        id = brandIconRes
                    ),
                    null,
                    modifier = Modifier
                        .height(48.dp)
                        .clip(middleListItemShape)
                )
                Spacer(modifier = Modifier.width(12.dp))
            }
            Text(
                text = modelText,
                style = typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
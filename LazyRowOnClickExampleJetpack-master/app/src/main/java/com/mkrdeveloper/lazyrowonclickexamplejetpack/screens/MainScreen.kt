package com.mkrdeveloper.lazyrowonclickexamplejetpack.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Import for navigation
import androidx.navigation.NavController

// State variable for list item background color
var listItemBackgroundColor by mutableStateOf(Color.White)

@Composable
fun MainScreen(
    imageId: Array<Int>,
    names: Array<String>,
    sinh: Array<String>,
    thongtin: Array<String>,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Box(
        modifier
    ) {
        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            val itemCount = imageId.size

            items(itemCount) {
                ColumnItem(
                    modifier = Modifier.background(listItemBackgroundColor), // Apply background color to each list item
                    painter = imageId,
                    title = names,
                    sinh = sinh,
                    thongtin = thongtin,
                    itemIndex = it,
                    navController = navController
                )
            }
        }

        Button(
            onClick = {
                // Change list item background color on button click (example)
                listItemBackgroundColor = Color.Green // Set a new color
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Green)
        ) {
            Text(text = "Change List Item Color")
        }
    }
}

@Composable
fun ColumnItem(
    modifier: Modifier,
    painter: Array<Int>,
    title: Array<String>,
    sinh: Array<String>,
    itemIndex: Int,
    navController: NavController,
    thongtin: Array<String>
) {


    Card(
        modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentSize()
            .background(Color.LightGray)
            .clickable {
                navController.navigate(route = "DetailScreen/$itemIndex")
            },
        colors = CardDefaults.cardColors(
            containerColor = listItemBackgroundColor // Use the state variable here
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(
            modifier = Modifier.background(Color.LightGray),    // chỉnh màu list        fillMaxWidth()
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Image(
                painter = painterResource(id = painter[itemIndex]),
                contentDescription = title[itemIndex],
                modifier.size(140.dp).background(Color.LightGray)
            )
            Column(modifier.padding(12.dp)) {
                Text(text = title[itemIndex], fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = sinh[itemIndex], fontSize = 18.sp)
                Text(text = thongtin[itemIndex], fontSize = 18.sp)

            }
        }
    }
}

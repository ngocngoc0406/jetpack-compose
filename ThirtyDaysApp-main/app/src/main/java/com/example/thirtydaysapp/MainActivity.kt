package com.example.thirtydaysapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirtydaysapp.model.Day
import com.example.thirtydaysapp.model.DayRepository
import com.example.thirtydaysapp.ui.theme.ThirtyDaysAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThirtyDaysAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ThirtyDaysApp()
                }
            }
        }
    }
}

@Composable
fun ThirtyDaysApp(){
      Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colors.background),
        topBar = {DayTopBar()}
    ) {
        LazyColumn(){
            items(DayRepository.days){
                DayCard(day = it)
            }
        }
    }

}

@Composable
fun DayCard(day: Day, modifier: Modifier = Modifier){
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { expanded = !expanded },
        elevation = 4.dp,
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(8.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ),
        ) {
            //Day number
            Text(
                text = "Day ${day.dayNum}", style = MaterialTheme.typography.h2)
            //Day Title
            Text(
                text = stringResource(id = day.dayTitleRes),
                style = MaterialTheme.typography.h2,
                color = Color.Black
            )
            //Day image
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = day.dayImageRes),
                contentDescription = stringResource(id = day.dayDescRes)
            )

            if (expanded){
                DayDescription(day.dayDescRes)
            }
        }

    }
}

@Composable
fun DayDescription(dayDesc: Int, modifier: Modifier = Modifier){
    Text(text = stringResource(id = dayDesc),
        style = MaterialTheme.typography.body1, fontWeight = FontWeight.Bold
    )
}

@Composable
fun DayTopBar(modifier: Modifier = Modifier){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .background(MaterialTheme.colors.onSurface)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.app_name_desc),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.background
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ThirtyDaysAppPreview() {
    ThirtyDaysAppTheme {
        ThirtyDaysApp()
    }
}
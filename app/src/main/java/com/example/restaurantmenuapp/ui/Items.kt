package com.example.restaurantmenuapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurantmenuapp.FETTUCCINE
import com.example.restaurantmenuapp.ORDERS_MENU
import com.example.restaurantmenuapp.amountOrdered
import com.example.restaurantmenuapp.amountStock


@Composable
fun Title(modifier: Modifier) {
    Text(text = ORDERS_MENU, fontSize = 48.sp, modifier = modifier)
}

@Composable
fun Food(name: String, modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {

        var counter by rememberSaveable { mutableIntStateOf(amountOrdered) }

        val color = if (counter >= amountStock) Color.Red else Color.Black

        Text(
            text = name,
            fontSize = 24.sp,
            color = color,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "+",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    if (counter < amountStock)
                        counter += 1
                }
        )
        Text(
            text = "$counter",
            fontSize = 24.sp,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "-",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    if (counter > 0)
                        counter -= 1
                }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun MyItems() {
    Food(FETTUCCINE, Modifier)
}

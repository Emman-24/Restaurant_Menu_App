package com.example.restaurantmenuapp.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurantmenuapp.R
import com.example.restaurantmenuapp.ui.model.ItemModel


@Composable
fun MenuScreen(menuViewModel: MenuViewModel) {

    val menu: List<ItemModel> = menuViewModel.menu

    Scaffold(topBar = { MenuTopAppBar() }, bottomBar = { MenuBottomAppBar(menuViewModel) }) { it ->
        LazyColumn(contentPadding = it, modifier = Modifier.padding(2.dp)) {
            items(menu) {
                MenuItem(
                    itemModel = it,
                    menuViewModel = menuViewModel
                )
            }
        }
    }
}

@Composable
fun MenuBottomAppBar(menuViewModel: MenuViewModel, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            modifier = modifier.padding(8.dp),
            colors = ButtonDefaults.buttonColors(Color.Black),
            onClick = {
                if (menuViewModel.order.isNotEmpty()) {
                    val text = menuViewModel.printOrder()
                    menuViewModel.clearOrder()
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text(
                text = "Make Order",
                fontSize = 24.sp,
                color = Color.White,
                modifier = modifier.padding(8.dp)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuTopAppBar() {
    CenterAlignedTopAppBar(title = {
        Row {
            Text(
                text = stringResource(id = R.string.orders_menu),
                fontSize = 48.sp
            )
        }
    })
}


@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    itemModel: ItemModel,
    menuViewModel: MenuViewModel,
) {
    val order = menuViewModel.order
    var count = order[itemModel] ?: 0
    val color = if (count >= itemModel.stock || itemModel.stock == 0) Color.Red else Color.Black

    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Text(
            text = itemModel.name,
            color = color,
            fontSize = 24.sp, modifier = modifier.padding(8.dp)
        )
        Text(
            text = "+",
            fontSize = 24.sp,
            modifier = modifier
                .padding(8.dp)
                .clickable {
                    if (count < itemModel.stock) {
                        count++
                        menuViewModel.addOrder(itemModel, count)
                    }
                })

        Text(text = "$count", fontSize = 24.sp, modifier = modifier.padding(8.dp))

        Text(
            text = "-",
            fontSize = 24.sp,
            modifier = modifier
                .padding(8.dp)
                .clickable {
                    if (count > 0) {
                        count--
                        menuViewModel.removeOrder(itemModel, count)
                    }
                })

    }
}
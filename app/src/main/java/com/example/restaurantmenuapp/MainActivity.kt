package com.example.restaurantmenuapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.restaurantmenuapp.ui.MenuItem
import com.example.restaurantmenuapp.ui.Title
import com.example.restaurantmenuapp.ui.theme.RestaurantMenuAppTheme

const val FETTUCCINE = "Fettuccine"
const val ORDERS_MENU = "Orders Menu"
const val amountStock = 5
const val amountOrdered = 0

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RestaurantMenuAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlayOrderMenu()
                }
            }
        }
    }
}

val recipesNameToStockAmount = mapOf(
    "Fettuccine" to 5,
    "Risotto" to 6,
    "Gnocchi" to 4,
    "Spaghetti" to 3,
    "Lasagna" to 5,
    "Steak Parmigiana" to 2
)

@Composable
fun PlayOrderMenu() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (title, order) = createRefs()

        Title(modifier = Modifier.constrainAs(title) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        })

        MenuItem(modifier = Modifier.constrainAs(order) {
            start.linkTo(parent.start)
            top.linkTo(title.bottom)
            end.linkTo(parent.end)
        })
    }
}


@Preview(showSystemUi = true)
@Composable
fun MyPreview() {
    PlayOrderMenu()
}
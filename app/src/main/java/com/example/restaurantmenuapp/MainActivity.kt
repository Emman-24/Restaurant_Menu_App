package com.example.restaurantmenuapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.restaurantmenuapp.ui.theme.RestaurantMenuAppTheme

const val FETTUCCINE = "Fettuccine"
const val ORDERS_MENU = "Orders Menu"

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

@Composable
fun PlayOrderMenu() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (title, fettuccine) = createRefs()

        Title(modifier = Modifier.constrainAs(title) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        })

        Dishes(name = FETTUCCINE, modifier = Modifier.constrainAs(fettuccine) {
            start.linkTo(parent.start)
            top.linkTo(title.bottom)
        })

    }

}

@Composable
fun Title(modifier: Modifier) {
    Text(text = ORDERS_MENU, fontSize = 48.sp, modifier = modifier)
}

@Composable
fun Dishes(name: String, modifier: Modifier) {
    Text(text = name, fontSize = 24.sp, modifier = modifier)
}


@Preview(showSystemUi = true)
@Composable
fun MyPreview() {
    PlayOrderMenu()
}
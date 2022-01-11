package com.qrolic.jetpackcomposecomponents

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.qrolic.jetpackcomposecomponents.bottomnav.CategoryScreen
import com.qrolic.jetpackcomposecomponents.bottomnav.FavouriteScreen
import com.qrolic.jetpackcomposecomponents.bottomnav.HomeScreen
import com.qrolic.jetpackcomposecomponents.bottomnav.SettingsScreen
import com.qrolic.jetpackcomposecomponents.ui.theme.JetpackComposeComponentsTheme

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            JetpackComposeComponentsTheme {
                val context = LocalContext.current
                val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            BottomAppBar(
                                modifier = Modifier
                                    .height(65.dp),
                                cutoutShape = CircleShape,
                                backgroundColor = MaterialTheme.colors.surface,
                                elevation = 10.dp
                            ) {
                                BottomNavigationBar(navController = navController)
                            }
                        }, content = { padding ->
                            NavHostContainer(navController = navController, padding = padding)
                        },
                        floatingActionButtonPosition = FabPosition.Center,
                        isFloatingActionButtonDocked = true,
                        floatingActionButton = {
                            FloatingActionButton(
                                shape = CircleShape,
                                onClick = {
                                    Toast.makeText(context, "button clicked",Toast.LENGTH_LONG).show()
                                },
                                contentColor = MaterialTheme.colors.surface
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "add",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    )

            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {

    NavHost(
        navController = navController,

        // set the start destination as home
        startDestination = "home",
        modifier = Modifier.padding(paddingValues = padding),

        builder = {

            // route : Home
            composable("home") {
                HomeScreen()
            }

            // route : furniture
            composable("category") {
                CategoryScreen()
            }


            // route : search
            composable("favourite") {
                FavouriteScreen()
            }

            // route : profile
            composable("setting") {
                SettingsScreen()
            }
        })

}

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation(

        // set background color
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    ) {

        // observe the backstack
        val navBackStackEntry  by navController.currentBackStackEntryAsState()

        // observe current route to change the icon
        // color,label color when navigated
        val currentRoute = navBackStackEntry?.destination?.route

        // Bottom nav items we declared
        BottomNavItems.forEach { navItem ->

            // Place the bottom nav items
            BottomNavigationItem(

                unselectedContentColor = MaterialTheme.colors.onSurface,
                selectedContentColor = MaterialTheme.colors.primary,
                // it currentRoute is equal then its selected route
                selected = currentRoute == navItem.route,

                // navigate on click
                onClick = {
                    navController.navigate(navItem.route){
                        popUpTo(
                            navController.graph.startDestinationId
                        )
                        launchSingleTop = true
                    }
                },

                // Icon of navItem
                icon = {
                    Icon(
                        painter = painterResource(id = navItem.icon),
                        contentDescription = navItem.label,
                        modifier = Modifier
                            .size(20.dp)
                    )
                },

                alwaysShowLabel = false
            )
        }
    }
}

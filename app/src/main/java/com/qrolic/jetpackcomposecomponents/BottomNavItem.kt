package com.qrolic.jetpackcomposecomponents


data class BottomNavItem(
    val label: String,
    val icon: Int,
    val route: String
)

val BottomNavItems = listOf(
    BottomNavItem(
        label = "Home",
        icon = R.drawable.home,
        route = "home"
    ),
    BottomNavItem(
        label = "Category",
        icon = R.drawable.category,
        route = "category"
    ),

    BottomNavItem(
        label = "Favourite",
        icon = R.drawable.ic_round_favorite_border_24,
        route = "favourite"
    ),
    BottomNavItem(
        label = "Settings",
        icon = R.drawable.setting,
        route = "setting"
    )
)

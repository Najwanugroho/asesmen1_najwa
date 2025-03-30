package com.najwashaqilaisnan607062300125.a6072300125_najwashaqilaisnan_asesment1.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
}

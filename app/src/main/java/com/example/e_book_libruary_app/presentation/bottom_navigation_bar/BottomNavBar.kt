package com.example.e_book_libruary_app.presentation.bottom_navigation_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.e_book_libruary_app.ui.theme.navBarIndicatorColor
import com.example.e_book_libruary_app.ui.theme.scaffoldBackgroundColor
import com.example.e_book_libruary_app.ui.theme.unselectedIconColor
import com.example.e_book_libruary_app.ui.theme.white
import com.example.e_book_libruary_app.util.Routes
import com.example.e_book_libruary_app.ui.theme.unselectedIconColor as unselectedIconColor1

@Composable
fun BottomNavBar(
    navController: NavHostController
){

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val items = listOf(
        BottomNavBarItem.Home,
        BottomNavBarItem.Search
    )

    if (currentDestination?.route in setOf(Routes.MAIN_SCREEN, Routes.SEARCH_SCREEN)) {
        NavigationBar(
            modifier = Modifier
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(12.dp),
                    ambientColor = Color.Black,
                    spotColor = Color.Black
                )
                .padding(bottom = 8.dp)
                .padding(horizontal = 4.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(12.dp)),
            containerColor = scaffoldBackgroundColor
        ) {
            items.forEachIndexed { index, bottomNavBarItem ->
                NavigationBarItem(
                    selected = checkCurrentDestination(currentDestination?.route) == index,
                    onClick = {
                        navController.navigate(items[index].route)
                    },
                    icon = {
                        Icon(
                            imageVector = if (index == checkCurrentDestination(currentDestination?.route)) {
                                items[index].selectedIcon
                            } else items[index].unselectedIcon,
                            contentDescription = items[index].route,
                            tint = white
                        )
                    },
                    colors = NavigationBarItemColors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = unselectedIconColor,
                        selectedTextColor = Color.Transparent,
                        unselectedTextColor = Color.Transparent,
                        disabledIconColor = Color.Transparent,
                        disabledTextColor = Color.Transparent,
                        selectedIndicatorColor = navBarIndicatorColor
                    )
                )
            }
        }
    }
}

enum class BottomNavBarItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
) {
    Home(
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        route = Routes.MAIN_SCREEN
    ),
    Search(
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
        route = Routes.SEARCH_SCREEN
    ),
}

fun checkCurrentDestination(currentScreen: String?): Int {
    return when (currentScreen){
        Routes.MAIN_SCREEN -> 0
        Routes.SEARCH_SCREEN -> 1
        else -> 0
    }
}

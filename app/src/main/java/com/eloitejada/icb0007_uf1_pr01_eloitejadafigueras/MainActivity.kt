package com.eloitejada.icb0007_uf1_pr01_eloitejadafigueras

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(mainViewModel)
        }
    }
}

@Composable
fun MyApp(viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            SplashScreen(navController, viewModel)
        }
        composable("main_screen") {
            MainScreen(navController)
        }
        composable("rocket_list_screen") {
            RLScreen(viewModel)
        }
    }
}

@Composable
fun SplashScreen(navController: NavController, viewModel: MainViewModel) {
    var splashDisplayed by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.startSplash {
            splashDisplayed = true
        }
    }

    if (splashDisplayed) {
        navController.navigate("rocket_list_screen") {
            popUpTo("splash_screen") { inclusive = true }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.splash_image),
                    contentDescription = "Splash Screen",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "SpaceX Rocket List",
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Welcome to SpaceX Rocket List!", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            androidx.compose.material3.Button(
                onClick = { navController.navigate("rocket_list_screen") }
            ) {
                Text(text = "Go to Rocket List")
            }
        }
    }
}

@Composable
fun RocketListScreen(viewModel: MainViewModel = viewModel()) {
    val rockets by viewModel.rocketList.collectAsState()

    LaunchedEffect(Unit) {
        println("Fetching rockets...") // ✅ Should print only once
        viewModel.fetchRockets()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (rockets.isEmpty()) {
            Text(text = "Loading Rockets...")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(rockets) { rocket ->
                    RocketItem(rocket)
                }
            }
        }
    }
}


@Composable
fun RocketItem(rocket: Rocket) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(text = "Rocket Name: ${rocket.name}", fontSize = 18.sp)
        Text(text = "Rocket Type: ${rocket.type}", fontSize = 18.sp)
        Text(text = "Active: ${rocket.active}", fontSize = 18.sp)
        Text(text = "Cost per Launch: ${rocket.cost_per_launch}", fontSize = 18.sp)
        Text(text = "Success Rate: ${rocket.success_rate_pct}%", fontSize = 18.sp)
        Text(text = "Country: ${rocket.country}", fontSize = 18.sp)
        Text(text = "Company: ${rocket.company}", fontSize = 18.sp)
        Text(text = "Wikipedia: ${rocket.wikipedia}", fontSize = 18.sp)
        Text(text = "Description: ${rocket.description}", fontSize = 18.sp)
        Text(text = "Height: ${rocket.height} m", fontSize = 18.sp)
        Text(text = "Diameter: ${rocket.diameter} m", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

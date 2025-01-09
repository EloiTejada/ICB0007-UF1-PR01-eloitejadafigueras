package com.eloitejada.icb0007_uf1_pr01_eloitejadafigueras

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp(mainViewModel)
        }
    }

    private fun loadLoginFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, LoginFragment(), "LoginFragment")
            .commit()
    }

    private fun loadRocketListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, RocketListFragment(), "RocketListFragment")
            .commit()
    }

    fun onMainScreenLoaded() {
        setContentView(R.layout.activity_main)
        loadLoginFragment()
    }

    fun onRocketListScreenLoaded() {
        setContentView(R.layout.activity_main)
        loadRocketListFragment()
    }
}

@Composable
fun SplashScreen(navController: NavController, viewModel: MainViewModel = viewModel()) {
    var splashDisplayed by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.startSplash {
            splashDisplayed = true
        }
    }

    if (splashDisplayed) {
        navController.navigate("main_screen") {
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
fun MyApp(viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            SplashScreen(navController = navController, viewModel = viewModel)
        }
        composable("main_screen") {
            MainScreen()
        }
        composable("rocket_list_screen") {
            RLScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        (context as? MainActivity)?.onMainScreenLoaded()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Loading Login Fragment...")
    }
}

@Composable
fun RLScreen() {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        (context as? MainActivity)?.onRocketListScreenLoaded()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Loading Rocket List Fragment...")
    }
}

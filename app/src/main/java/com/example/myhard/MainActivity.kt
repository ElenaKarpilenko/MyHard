package com.example.myhard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myhard.ui.theme.MyHardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyHardTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "start") {
        composable("start") { StartScreen(navController) }
        composable("userProfile") { UserProfileScreen(navController) }
        composable("editProfile") { EditProfileScreen(navController) }
    }
}

@Composable
fun StartScreen(navController: NavController) {
    var isLiked by remember { mutableStateOf(false) }

    // Анимация масштаба
    val scale by animateFloatAsState(
        targetValue = if (isLiked) 1.5f else 1f,
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 500)
    )

    // Анимация цвета
    val color by animateColorAsState(
        targetValue = if (isLiked) Color.Red else Color.Gray,
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 500)
    )

    // Анимация текста
    val textAlpha by animateFloatAsState(
        targetValue = if (isLiked) 1f else 0.6f,
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 500)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Приветственное сообщение с анимацией
        Text(
            text = "Добро пожаловать!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.alpha(textAlpha) // Применяем анимацию прозрачности
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Картинка с анимацией
        Icon(
            painter = painterResource(id = R.drawable.img_1), // Используйте свой ресурс изображения
            contentDescription = if (isLiked) "Liked" else "Like",
            modifier = Modifier
                .clickable { isLiked = !isLiked }
                .padding(16.dp)
                .scale(scale), // Применяем анимацию масштаба
            tint = color // Применяем анимацию цвета
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Кнопка перехода к профилю с анимацией
        Button(onClick = { navController.navigate("userProfile") }) {
            Text(text = "Перейти в профиль", fontSize = 16.sp)
        }
    }
}

@Composable
fun UserProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Заголовок профиля
        Text(text = "Профиль пользователя", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        // Изображение профиля
        Icon(
            painter = painterResource(id = R.drawable.img), // Убедитесь, что этот ресурс существует
            contentDescription = "Изображение профиля",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Имя пользователя
        Text(
            text = "Елена Карпиленко",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Статус пользователя
        Text(
            text = "Онлайн",
            fontSize = 16.sp,
            color = Color.Green
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка перехода к редактированию профиля
        Button(onClick = { navController.navigate("editProfile") }) {
            Text(text = "Редактировать профиль")
        }
    }
}

@Composable
fun EditProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Заголовок экрана редактирования
        Text(text = "Редактирование профиля", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        // Текст, что редактирование ещё не реализовано
        Text(
            text = "Функционал редактирования будет добавлен позже.",
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка возврата на экран профиля
        Button(onClick = { navController.navigate("userProfile") }) {
            Text(text = "Назад в профиль")
        }
    }
}

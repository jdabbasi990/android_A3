package com.jawwad.assignment_3_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jawwad.assignment_3_android.ui.theme.Assignment3AndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment3AndroidTheme {
                // A surface container using the 'background' color from the theme
                /// added here
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavigationHost(navController)
                }
            }
        }
    }
}

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController, startDestination = "main_menu") {
        composable("main_menu") {
            MainMenu(navController)
        }
        composable("screen1") {
            Screen1(navController)
        }
        composable("screen2") {
            Screen2()
        }
        composable("screen3") {
            Screen3()
        }
        composable("screen4") {
            Screen4()
        }
    }
}

@Composable
fun MainMenu(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(48.dp))
        Head()
        Spacer(modifier = Modifier.height(32.dp))
        FourButtons(navController)
        Spacer(modifier = Modifier.height(96.dp))
        StudentInfo()

    }
}

@Composable
fun Head() {
    Text(
        text = "Main Menu",
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun FourButtons(navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Button(
            onClick = { navController.navigate("screen1") },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(vertical = 8.dp)
        )

        {
            Text(
                text = "Button 1",
                color = Color.White,
                fontSize = 25.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("screen2") },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(vertical = 8.dp)
        )
        {
            Text(
                text = "Button 2",
                color = Color.White,
                fontSize = 25.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("screen3") },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(vertical = 8.dp)
        )
        {
            Text(
                text = "Button 3",
                color = Color.White,
                fontSize = 25.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("screen4") },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Button 4",
                color = Color.White,
                fontSize = 25.sp
            )
        }
    }
}

@Composable
fun Screen1(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    var textToShow by remember { mutableStateOf("HS") }

    val lineHeight = 32.sp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(48.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }

        Text(
            text = "Screen 1",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            modifier = Modifier.padding(top = 16.dp, bottom = 48.dp)
        )

        Spacer(modifier = Modifier.height(120.dp))

        Box(
            modifier = Modifier
                .size(if (expanded) 300.dp else 150.dp)
                .animateContentSize()
                .clickable {
                    expanded = !expanded
                    textToShow = if (expanded) "I can write any sentences I wish" else "JD"
                }
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = textToShow,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(if (expanded) Alignment.TopStart else Alignment.Center)
                    .padding(16.dp),
                color = Color.Black,
                fontSize = 32.sp,
                lineHeight = lineHeight,
                textAlign = if (expanded) TextAlign.Start else TextAlign.Center,
                maxLines = if (expanded) Int.MAX_VALUE else 1
            )
        }
    }
}

@Composable
fun Screen2() {
    var isRotating by remember { mutableStateOf(false) }

    // Track whether the button is currently being touched
    val touching = rememberUpdatedState(isRotating)

    // Animate rotation angle when the button is being touched
    val animatedRotation by animateFloatAsState(
        targetValue = if (touching.value) 360f else 0f,
        animationSpec = tween(durationMillis = 1000), label = ""
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally, )
    {
        Spacer(modifier = Modifier.height(48.dp))


        Text(
            text = "Screen 2",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
        )


        Spacer(modifier = Modifier.height(72.dp))

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                modifier = Modifier
                    .size(130.dp)
                    .rotate(animatedRotation)
                    .clickable {
                        // Toggle the rotating state on button click
                        isRotating = !isRotating
                    }
            )
        }
    }
}

@Composable
fun Screen3() {
    var value by remember { mutableFloatStateOf(1f) }
    var cycleCount by remember { mutableIntStateOf(0) }
    val backgroundColor by remember(cycleCount) {
        mutableStateOf(
            when ((cycleCount % 4)) {
                0 -> Color.Yellow
                1 -> Color.Blue
                2 -> Color.Cyan
                else -> Color.Green
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor),
        ) {
            Column(Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(48.dp))
                Text("This is Screen 3",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(240.dp))

                Text(

                    text = "Value: ${value.toInt()}",
                    modifier = Modifier.padding(top = 16.dp),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
    LaunchedEffect(Unit) {
        while (true) {
            animateValue(1f, 10f, 500) { newValue ->
                value = newValue
            }
            cycleCount++
        }
    }
}

private suspend fun animateValue(
    start: Float,
    end: Float,
    duration: Int,
    onUpdate: (Float) -> Unit
) {
    tween<Float>(duration)
    for (i in 0..duration) {
        val progress = i.toFloat() / duration
        val newValue = if (progress <= 0.5f) {
            start + (end - start) * (progress )
        } else {
            end - (end - start) * ((1 - progress))
        }
        onUpdate(newValue)
        kotlinx.coroutines.delay(1)
    }
}

@Composable
fun Screen4() {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    var scale by remember { mutableFloatStateOf(1f) }
    var rotation by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, rotate ->
                    offsetX += pan.x
                    offsetY += pan.y
                    scale *= zoom
                    rotation += rotate
                    rotation %= 360f
                }
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ControllerSlider(
                value = scale,
                onValueChange = { scale = it },
                valueRange = 0.1f..5f,
                steps = 100,
                label = "Zoom"
            )
            ControllerSlider(
                value = rotation,
                onValueChange = { rotation = it },
                valueRange = 0f..360f,
                steps = 360,
                label = "Rotation"
            )
        }

        Text(
            text = "This is Screen 4",
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer(
                    translationX = offsetX,
                    translationY = offsetY,
                    scaleX = scale,
                    scaleY = scale,
                    rotationZ = rotation
                )
        )
    }
}

@Composable
fun ControllerSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
    label: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(label)
        Spacer(modifier = Modifier.height(8.dp))
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            steps = steps,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun StudentInfo(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Jawwad Abbasi",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp) // Add bottom padding for spacing
        )
        Text(
            text = "    301298052",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp) // Add
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AssignmentPreview() {
    Assignment3AndroidTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            FourButtons(rememberNavController())
            Spacer(modifier = Modifier.height(32.dp))
            StudentInfo()
        }
    }
}

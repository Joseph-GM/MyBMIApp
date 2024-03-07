package com.example.mybmiapp

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp


@Composable
fun HomeScreen(){
    var heightString by remember{ mutableStateOf("")}
    var weightString by remember { mutableStateOf("") }
    var bmiValue by remember { mutableStateOf("") }
    var obesityLevel by remember { mutableStateOf("") }
    var faceStatus by remember { mutableStateOf("😃") }


    // Keyboard 숨기는 것을 구현하기 위해 LocalFocusManager 인스턴스를 가져옵니다.
    val focusManager = LocalFocusManager.current


    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 32.sp,
        color = Color.Red
    )

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "BMI Calculator", style = customTextStyle)
        Text(text = "Your Level : $obesityLevel")
        OutlinedTextField(value = heightString, 
            onValueChange = {
                heightString = it
                Log.d("joseph", "Height input : $heightString")},
            label = { Text(text = "Height(m)")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(value = weightString,
            onValueChange = {
                weightString = it
                Log.d("joseph", "Weight input : $weightString")},
            label = { Text(text = "Weight(kg)")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = {
            bmiValue = bmiCalculator(heightString,weightString)
            obesityLevel = calculateObesityLevel(bmiValue.toDouble())
            faceStatus = outFaceStatus(obesityLevel)
            // 키보드를 사라지게 하기
            focusManager.clearFocus()
        }) {
            Text(text = "Calculation")
        }
        Text(text = "Your BMI : $bmiValue", style = TextStyle(fontSize = 30.sp))
        Text(text = faceStatus, style = TextStyle(fontSize = 150.sp))

        
    }

}

fun bmiCalculator(height: String, weight: String): String {
    var myBmi : Double
    myBmi = weight.toDouble() / (height.toDouble() * height.toDouble() )
    return String.format("%.1f", myBmi)
}

fun calculateObesityLevel(bmi: Double): String {
    return when {
        bmi < 18.5 -> "Underweight"
        bmi < 25 -> "Normal weight"
        bmi < 30 -> "Overweight"
        bmi < 35 -> "Obesity Class 1"
        bmi < 40 -> "Obesity Class 2"
        else -> "Severe Obesity Class 3"
    }
}

fun outFaceStatus(obesityString: String): String {
     return when {
        obesityString ==  "Underweight" -> "😂"
        obesityString == "Normal weight" -> "😄"
        obesityString == "Overweight" -> "🤪"
        obesityString == "Obesity Class 1" -> "😓"
        obesityString == "Obesity Class 2" -> "😭"
        obesityString == "Severe Obesity Class 3" -> "😱"
        else -> ""
    }
}


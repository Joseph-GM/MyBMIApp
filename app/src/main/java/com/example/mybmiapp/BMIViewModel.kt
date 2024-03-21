package com.example.mybmiapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BMIViewModel: ViewModel() {
    private val _repository: BMIRepository = BMIRepository()

//    var heightString by remember{ mutableStateOf("")}
//    var weightString by remember { mutableStateOf("") }

    private val _bmiValue = mutableStateOf(_repository.getBmiResults().bmiValue)
    private val _obsessiveLevel = mutableStateOf(_repository.getBmiResults().obsessiveLevel)
    private val _obsessiveEmoji = mutableStateOf(_repository.getBmiResults().obsessiveEmoji)

    var heightString by mutableStateOf("")
    var weightString by mutableStateOf("")

    val bmiValue: MutableState<Double> = _bmiValue
    val obsessiveLevel: MutableState<String> = _obsessiveLevel
    val obsessiveEmoji : MutableState<String> = _obsessiveEmoji


    fun onHeightChanged(newString:String) {
        heightString = newString
    }

    fun onWeightChanged(newString: String){
        weightString = newString
    }


    fun setBmiValue(height: Double, weight: Double) {
        _repository.setBMIResult(height, weight)
        _bmiValue.value = _repository.getBmiResults().bmiValue
        _obsessiveLevel.value = _repository.getBmiResults().obsessiveLevel
        _obsessiveEmoji.value = _repository.getBmiResults().obsessiveEmoji
    }


}
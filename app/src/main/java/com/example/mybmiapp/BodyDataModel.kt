package com.example.mybmiapp

data class BmiResult(var bmiValue: Double, var obsessiveLevel: String, var obsessiveEmoji: String)

class BMIRepository{
    private var _bmiResult = BmiResult(17.0,"Normal weight", "😀")
//    private var _resultOutput = ResultOutput(0.0, "정상", "😀")

    fun getBmiResults() = _bmiResult
//    fun getResultOutput = _resultOutput

    fun setBMIResult(inputHeight: Double, inputWeight: Double) {
        _bmiResult.bmiValue = bmiValueCal(inputHeight, inputWeight)
        _bmiResult.obsessiveLevel = obsessiveLevelCal(_bmiResult.bmiValue)
        _bmiResult.obsessiveEmoji = outFaceStatus(_bmiResult.obsessiveLevel)
    }

    private fun bmiValueCal(height: Double, weight: Double): Double {
        var myBmi : Double = weight / (height * height )
        return myBmi
    }

    private fun obsessiveLevelCal(bmiString: Double) : String{
        return when {
            bmiString < 18.5 -> "Underweight"
            bmiString < 25 -> "Normal weight"
            bmiString < 30 -> "Overweight"
            bmiString < 35 -> "Obesity Class 1"
            bmiString < 40 -> "Obesity Class 2"
            else -> "Severe Obesity Class 3"
        }
    }

    private fun outFaceStatus(obesityString: String): String {
        return when {
            obesityString == "Underweight" -> "😂"
            obesityString == "Normal weight" -> "😄"
            obesityString == "Overweight" -> "🤪"
            obesityString == "Obesity Class 1" -> "😓"
            obesityString == "Obesity Class 2" -> "😭"
            obesityString == "Severe Obesity Class 3" -> "😱"
            else -> ""
        }
    }

}
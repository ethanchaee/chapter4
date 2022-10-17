package fastcampus.aop.part1.chapter4

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fastcampus.aop.part1.chapter4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val firstNumberText = java.lang.StringBuilder("")
    private val secondNumberText = java.lang.StringBuilder("")
    private var operatorText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun updateEquationTextView() {
        binding.equationTextView.text = "$firstNumberText $operatorText $secondNumberText"
    }

    fun numberButtonClicked(view: View) {
        val numberString = (view as? Button)?.text.toString()
        val numberText = if (operatorText.isEmpty()) firstNumberText else secondNumberText

        if (numberText.length >= 10) {
            Toast.makeText(this, "숫자는 10자리 까지 입력이 가능합니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (numberText.toString() == "0") {
            if (numberString == "0") {
                return
            }
            numberText.clear()
        }

        numberText.append(numberString)
        updateEquationTextView()
    }

    fun operatorButtonClicked(view: View) {
        val operatorString = (view as? Button)?.text.toString()

        if (firstNumberText.isEmpty()) {
            Toast.makeText(this, "먼저 연산하려는 숫자를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        if (secondNumberText.isNotEmpty()) {
            Toast.makeText(this, "1개의 연산자에 대해서만 연산이 가능합니다.", Toast.LENGTH_SHORT).show()
            return
        }

        operatorText = operatorString
        updateEquationTextView()
    }

    fun clearButtonClicked(view: View) {
        firstNumberText.clear()
        secondNumberText.clear()
        operatorText = ""

        updateEquationTextView()
        binding.resultTextView.text = ""
    }

    fun equalButtonClicked(view: View) {
        if (firstNumberText.isNotEmpty() && operatorText.isNotEmpty() && secondNumberText.isNotEmpty()) {
            val firstNumber = firstNumberText.toString().toInt()
            val secondNumber = secondNumberText.toString().toInt()

            val result = when (operatorText) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                else -> {
                    Toast.makeText(this, "입력한 수식을 다시 한 번 확인해주세요.", Toast.LENGTH_SHORT).show()
                    "수식이 잘못됐습니다."
                }
            }
            binding.resultTextView.text = result.toString()
        }
    }
}
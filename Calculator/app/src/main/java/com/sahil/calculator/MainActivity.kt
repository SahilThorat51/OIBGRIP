package com.phani.calculator

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.phani.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mInputTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mInputTextView = mBinding.inputTextView
        var isOpen = true

        mBinding.one.setOnClickListener { mInputTextView.append("1") }
        mBinding.two.setOnClickListener { mInputTextView.append("2") }
        mBinding.three.setOnClickListener { mInputTextView.append("3") }
        mBinding.four.setOnClickListener { mInputTextView.append("4") }
        mBinding.five.setOnClickListener { mInputTextView.append("5") }
        mBinding.six.setOnClickListener { mInputTextView.append("6") }
        mBinding.seven.setOnClickListener { mInputTextView.append("7") }
        mBinding.eight.setOnClickListener { mInputTextView.append("8") }
        mBinding.nine.setOnClickListener { mInputTextView.append("9") }
        mBinding.zero.setOnClickListener { mInputTextView.append("0") }
        mBinding.backspace.setOnClickListener {
            val inputString = mInputTextView.text.toString()
            if (inputString.isNotEmpty()) {
                mInputTextView.text = inputString.substring(0, inputString.length - 1)
            }
        }
        mBinding.clear.setOnClickListener {
            mInputTextView.text = ""
            mBinding.outputTextView.text = ""
        }
        mBinding.braces.setOnClickListener {
            if (isOpen) mInputTextView.append("(")
            else mInputTextView.append(")")
            isOpen = !isOpen
        }
        mBinding.divide.setOnClickListener { mInputTextView.append("/") }
        mBinding.multiply.setOnClickListener { mInputTextView.append("*") }
        mBinding.minus.setOnClickListener { mInputTextView.append("-") }
        mBinding.plus.setOnClickListener { mInputTextView.append("+") }
        mBinding.point.setOnClickListener { mInputTextView.append(".") }
        mBinding.equal.setOnClickListener {
            try {
                val expression = ExpressionBuilder(mInputTextView.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    Toast.makeText(this, "Double", Toast.LENGTH_SHORT).show()
                    mBinding.outputTextView.text = longResult.toString()
                } else
                    mBinding.outputTextView.text = result.toString()

            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
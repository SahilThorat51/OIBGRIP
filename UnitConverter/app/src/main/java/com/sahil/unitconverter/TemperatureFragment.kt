package com.phani.unitconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.phani.unitconverter.databinding.FragmentTemperatureBinding

class TemperatureFragment : Fragment() {

    private lateinit var mBinding: FragmentTemperatureBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentTemperatureBinding.inflate(inflater)

        val adapter = context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.temperature,
                android.R.layout.simple_spinner_item
            )
        }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mBinding.temperatureFromSpinner.adapter = adapter
        mBinding.temperatureToSpinner.adapter = adapter

        mBinding.fromTemperatureET.setOnClickListener {
            mBinding.outputTemperatureTV.text = ""
            mBinding.view.visibility = View.INVISIBLE
        }

        mBinding.temperatureCalculateBtn.setOnClickListener {
            val string = mBinding.fromTemperatureET.text.toString()
            if (string.isEmpty()) return@setOnClickListener
            val fromValue = string.toDouble()
            val fromUnit = mBinding.temperatureFromSpinner.selectedItem.toString()
            val toUnit = mBinding.temperatureToSpinner.selectedItem.toString()
            val toValue: Double

            if (fromUnit != toUnit) {
                when (fromUnit) {
                    "Celsius" -> {
                        toValue = when (toUnit) {
                            "Fahrenheit" -> (9 / 5) * fromValue + 32
                            "Kelvin" -> fromValue + 273
                            else -> fromValue
                        }
                    }
                    "Fahrenheit" -> {
                        toValue = when (toUnit) {
                            "Celsius" -> 5 / 9 * (fromValue - 32)
                            "Kelvin" -> 5 / 9 * (fromValue - 32) + 273
                            else -> fromValue
                        }
                    }
                    "Kelvin" -> {
                        toValue = when (toUnit) {
                            "Celsius" -> fromValue - 273
                            "Fahrenheit" -> 9 / 5 * (fromValue - 273) + 32
                            else -> fromValue
                        }
                    }
                    else -> toValue = fromValue
                }
            } else toValue = fromValue

            val outputText = "$fromValue $fromUnit = $toValue $toUnit"
            mBinding.outputTemperatureTV.text = outputText
            mBinding.view.visibility = View.VISIBLE
            mBinding.fromTemperatureET.text.clear()

            Toast.makeText(
                context,
                "Calculated successfully",
                Toast.LENGTH_SHORT
            ).show()

        }
        return mBinding.root
    }
}
package com.phani.unitconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.phani.unitconverter.databinding.FragmentLengthBinding

class LengthFragment : Fragment() {

    private lateinit var mBinding: FragmentLengthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentLengthBinding.inflate(inflater)

        val adapter = context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.length,
                android.R.layout.simple_spinner_item
            )
        }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mBinding.lenFromSpinner.adapter = adapter
        mBinding.lenToSpinner.adapter = adapter

        mBinding.fromLenET.setOnClickListener {
            mBinding.outputLenTV.text = ""
            mBinding.view.visibility = View.INVISIBLE
        }

        mBinding.temperatureCalculateBtn.setOnClickListener {
            val string = mBinding.fromLenET.text.toString()
            if (string.isEmpty()) return@setOnClickListener
            val fromValue = string.toDouble()
            val toValue: Double
            val fromUnit = mBinding.lenFromSpinner.selectedItem.toString()
            val toUnit = mBinding.lenToSpinner.selectedItem.toString()

            if (fromUnit != toUnit) {
                when (fromUnit) {
                    "Kilometers" -> {
                        toValue = when (toUnit) {
                            "Foot" -> fromValue * 3280.84
                            "Centimeters" -> fromValue * 100000
                            "Inches" -> fromValue * 39370
                            "Mile" -> fromValue / 1.609
                            "Meters" -> fromValue * 1000
                            else -> fromValue
                        }
                    }
                    "Centimeters" -> {
                        toValue = when (toUnit) {
                            "Mile" -> fromValue / 160934
                            "Kilometers" -> fromValue / 100000
                            "Foot" -> fromValue / 30.48
                            "Inches" -> fromValue / 2.54
                            "Meters" -> fromValue * 100
                            else -> fromValue
                        }
                    }
                    "Foot" -> {
                        toValue = when (toUnit) {
                            "Mile" -> fromValue / 5280
                            "Kilometers" -> fromValue / 3281
                            "Meters" -> fromValue / 3.281
                            "Centimeters" -> fromValue * 30.48
                            "Inches" -> fromValue * 12
                            else -> fromValue
                        }
                    }
                    "Inches" -> {
                        toValue = when (toUnit) {
                            "Mile" -> fromValue / 63360
                            "Kilometers" -> fromValue / 39370
                            "Foot" -> fromValue / 12
                            "Meters" -> fromValue / 39.37
                            "Centimeters" -> fromValue * 2.54
                            else -> fromValue
                        }
                    }
                    "Meters" ->
                        toValue = when (toUnit) {
                            "Mile" -> fromValue / 1609
                            "Kilometers" -> fromValue / 1000
                            "Foot" -> fromValue * 3.281
                            "Inches" -> fromValue * 39.37
                            "Centimeters" -> fromValue * 100
                            else -> fromValue
                        }
                    "Mile" ->
                        toValue = when (toUnit) {
                            "Meters" -> fromValue * 1609
                            "Kilometers" -> fromValue * 1.609
                            "Foot" -> fromValue * 5280
                            "Inches" -> fromValue * 63360
                            "Centimeters" -> fromValue * 160934
                            else -> fromValue
                        }
                    else -> toValue = fromValue
                }
            } else toValue = fromValue

            val outputText = "$fromValue $fromUnit = $toValue $toUnit"
            mBinding.outputLenTV.text = outputText
            mBinding.view.visibility = View.VISIBLE
            mBinding.fromLenET.text.clear()

            Toast.makeText(
                context,
                "Calculated successfully",
                Toast.LENGTH_SHORT
            ).show()

        }

        return mBinding.root
    }
}
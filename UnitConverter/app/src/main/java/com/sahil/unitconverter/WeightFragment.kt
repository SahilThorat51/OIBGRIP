package com.phani.unitconverter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.phani.unitconverter.databinding.FragmentWeightBinding

class WeightFragment : Fragment() {

    private lateinit var mBinding: FragmentWeightBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentWeightBinding.inflate(inflater)

        val adapter = context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.weight,
                android.R.layout.simple_spinner_item
            )
        }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mBinding.weightFromSpinner.adapter = adapter
        mBinding.weightToSpinner.adapter = adapter

        mBinding.fromWeightET.setOnClickListener {
            mBinding.outputWeightTV.text = ""
            mBinding.view.visibility = View.INVISIBLE
        }

        mBinding.weightCalculateBtn.setOnClickListener {
            val string = mBinding.fromWeightET.text.toString()
            if (string.isEmpty()) return@setOnClickListener
            val fromValue: Double = string.toDouble()

            val fromUnit = mBinding.weightFromSpinner.selectedItem.toString()
            val toUnit = mBinding.weightToSpinner.selectedItem.toString()
            val toValue: Double

            if (fromUnit != toUnit) {
                when (fromUnit) {
                    "Kilogram" -> {
                        toValue = when (toUnit) {
                            "US Ton" -> fromValue / 907
                            "Gram" -> fromValue * 1000
                            "Pound" -> fromValue * 2.205
                            "Ounce" -> fromValue * 35.274
                            "Milligram" -> fromValue * 1e+6
                            else -> fromValue
                        }
                    }
                    "US Ton" -> {
                        toValue = when (toUnit) {
                            "Kilogram" -> fromValue * 907
                            "Gram" -> fromValue * 907185
                            "Pound" -> fromValue * 2000
                            "Ounce" -> fromValue * 32000
                            "Milligram" -> fromValue * 9.072e+8
                            else -> fromValue
                        }
                    }
                    "Gram" -> {
                        toValue = when (toUnit) {
                            "Kilogram" -> fromValue / 1000
                            "US Ton" -> fromValue / 907185
                            "Pound" -> fromValue / 454
                            "Ounce" -> fromValue / 28.35
                            "Milligram" -> fromValue * 1000
                            else -> fromValue
                        }
                    }
                    "Pound" -> {
                        toValue = when (toUnit) {
                            "Kilogram" -> fromValue / 2.205
                            "US Ton" -> fromValue / 2000
                            "Gram" -> fromValue * 454
                            "Ounce" -> fromValue * 16
                            "Milligram" -> fromValue * 453592
                            else -> fromValue
                        }
                    }
                    "Ounce" -> {
                        toValue = when (toUnit) {
                            "Kilogram" -> fromValue / 35.274
                            "US Ton" -> fromValue / 32000
                            "Gram" -> fromValue * 38.35
                            "Pound" -> fromValue / 16
                            "Milligram" -> fromValue * 28350
                            else -> fromValue
                        }
                    }
                    "Milligram" -> {
                        toValue = when (toUnit) {
                            "Kilogram" -> fromValue / 1e+6
                            "US Ton" -> fromValue / 9.072e+8
                            "Gram" -> fromValue / 1000
                            "Pound" -> fromValue / 453592
                            "Ounce" -> fromValue / 28350
                            else -> fromValue
                        }
                    }
                    else -> toValue = fromValue
                }
            } else toValue = fromValue

            val outputText = "$fromValue $fromUnit = $toValue $toUnit"
            mBinding.outputWeightTV.text = outputText
            mBinding.view.visibility = View.VISIBLE
            mBinding.fromWeightET.text.clear()

            Toast.makeText(
                context,
                "Calculated successfully",
                Toast.LENGTH_SHORT
            ).show()

        }

        return mBinding.root
    }

}
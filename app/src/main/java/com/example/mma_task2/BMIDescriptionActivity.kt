package com.example.mma_task2

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat


class BMIDescriptionActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_description)
        val bmi_textView = findViewById<TextView>(R.id.act_2_bmi)
        val bmi_value = intent.getDoubleExtra("bmi_value",0.0)
        val bmiDescription = findViewById<TextView>(R.id.BMIDescriptionTextView)
        bmi_textView.text = bmi_value.toString()
        if(bmi_value<16.0){
            bmiDescription.text = getString(R.string.Very_severely_underweight)
            bmiDescription.setTextColor(getColor(R.color.Very_severely_underweight))
        }
        else if(bmi_value in 16.0..16.99){
            bmiDescription.text = getString(R.string.Severely_underweight)
            bmiDescription.setTextColor(getColor(R.color.Severely_underweight))
        }
        else if(bmi_value in 17.0..18.49){
            bmiDescription.text = getString(R.string.Underweight)
            bmiDescription.setTextColor(getColor(R.color.Underweight))
        }
        else if(bmi_value in 18.5..24.99){
            bmiDescription.text = getString(R.string.Normal_healthy_weight)
            bmiDescription.setTextColor(getColor(R.color.Normal_healthy_weight))
        }
        else if(bmi_value in 25.0..29.99){
            bmiDescription.text = getString(R.string.Overweight)
            bmiDescription.setTextColor(getColor(R.color.Overweight))
        }
        else if(bmi_value in 30.0..34.99){
            bmiDescription.text = getString(R.string.Obese_Class_I_Moderately_obese)
            bmiDescription.setTextColor(getColor(R.color.Obese_Class_I_Moderately_obese))
        }
        else if(bmi_value in 35.0..39.99){
            bmiDescription.text = getString(R.string.Obese_Class_II_Severely_obese)
            bmiDescription.setTextColor(getColor(R.color.Obese_Class_II_Severely_obese))
        }
        else if(bmi_value >=40.0){
            bmiDescription.text = getString(R.string.Obese_Class_III_Very_Severely_obese)
            bmiDescription.setTextColor(getColor(R.color.Obese_Class_III_Very_Severely_obese))
        }
        val bckbtn = findViewById<Button>(R.id.aboutTheAuthorBckBtn)
        bckbtn.setOnClickListener {
            finish()
        }


    }
}





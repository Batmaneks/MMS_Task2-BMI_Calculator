package com.example.mma_task2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*

fun Activity.hideKeyboard() {
    val view = this.currentFocus
    if (view != null) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}


class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val resultWinTitle = findViewById<TextView>(R.id.resultWindowTitle)
        resultWinTitle.visibility = View.INVISIBLE
        val aboutTheAuthorBtn = findViewById<Button>(R.id.aboutTheAuthorBckBtn)
        val massEditText = findViewById<EditText>(R.id.enterMassBox)
        val heightEditText = findViewById<EditText>(R.id.enterHeightBox)
        val finalResWindow = findViewById<TextView>(R.id.finalResultWindow)
        val sw1 = findViewById<Switch>(R.id.metric_imperial_switch)
        val massLid = findViewById<TextView>(R.id.massLid)
        val heightLid = findViewById<TextView>(R.id.heightLid)
        val cntBtn = findViewById<Button>(R.id.countButton)
        var bmiReady = false
        var bmi = 0.0
        sw1?.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) sw1.textOn else sw1.textOff
            Toast.makeText(
                this@MainActivity, message,
                Toast.LENGTH_SHORT
            ).show()
            if (isChecked) {
                massEditText.hint = getString(R.string.enterMass_default_val_lb)
                heightEditText.hint = getString(R.string.enterHeight_default_val_in)
                massLid.text = getString(R.string.Mass_window_description_lb)
                heightLid.text = getString(R.string.Height_window_description_in)
            }
            if (!isChecked) {
                massEditText.hint = getString(R.string.enterMass_default_val)
                heightEditText.hint = getString(R.string.enterHeight_default_val)
                massLid.text = getString(R.string.Mass_window_description_kg)
                heightLid.text = getString(R.string.Height_window_description_cm)
            }
        }
        aboutTheAuthorBtn.setOnClickListener {
            val intent = Intent(this,AboutAuthorActivity::class.java)
            startActivity(intent)
        }
        if (bmi == 0.0)
            cntBtn.setOnClickListener {
                hideKeyboard()
                if (massEditText.text.toString() == "" || heightEditText.text.toString() == "" || massEditText.text.toString()
                        .toDouble() <= 0 || heightEditText.text.toString().toDouble() <= 0
                ) {
                    Toast.makeText(
                        this,
                        "Please enter correctly all the required numbers",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    resultWinTitle.visibility = View.VISIBLE
                    val weight: Double =
                        findViewById<EditText>(R.id.enterMassBox).text.toString().toDouble()
                    val height: Double =
                        findViewById<EditText>(R.id.enterHeightBox).text.toString().toDouble()
                    if (sw1.isChecked) {
                        bmi = (weight * 703) / ((height) * (height))
                    } else {
                        bmi = weight / ((height / 100) * (height / 100))
                    }
                    bmi = String.format("%.2f", bmi).toDouble()
                    finalResWindow.text = bmi.toString()
                    bmiReady = true
                }
                val BMI_INTENT_KEY = "bmi_value"
                if (bmiReady) {
                    finalResWindow.setOnClickListener {
                        val intent = Intent(this, BMIDescriptionActivity::class.java)
                        intent.putExtra(BMI_INTENT_KEY, bmi)
                        startActivity(intent)
                    }
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.units_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val sw1 = findViewById<Switch>(R.id.metric_imperial_switch)
        val massEditText = findViewById<EditText>(R.id.enterMassBox)
        val heightEditText = findViewById<EditText>(R.id.enterHeightBox)
        val massLid = findViewById<TextView>(R.id.massLid)
        val heightLid = findViewById<TextView>(R.id.heightLid)
        when(item.itemId){
            R.id.subItemMetric -> {
                sw1.isChecked = false
                massEditText.hint = getString(R.string.enterMass_default_val)
                heightEditText.hint = getString(R.string.enterHeight_default_val)
                massLid.text = getString(R.string.Mass_window_description_kg)
                heightLid.text = getString(R.string.Height_window_description_cm)
            }
            R.id.subItemImperial -> {
                sw1.isChecked = true
                massEditText.hint = getString(R.string.enterMass_default_val_lb)
                heightEditText.hint = getString(R.string.enterHeight_default_val_in)
                massLid.text = getString(R.string.Mass_window_description_lb)
                heightLid.text = getString(R.string.Height_window_description_in)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
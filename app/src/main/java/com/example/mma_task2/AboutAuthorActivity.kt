package com.example.mma_task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AboutAuthorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_author)
        val bckBtn = findViewById<Button>(R.id.aboutTheAuthorBckBtn)
        bckBtn.setOnClickListener {
            finish()
        }
    }
}
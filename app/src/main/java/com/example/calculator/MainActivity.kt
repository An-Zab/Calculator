package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var workingTV : TextView
    lateinit var resultTV : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun backspacefunction(view: View) {}
    fun clearallfunction(view: View) {
        workingTV = findViewById(R.id.workingTV)
        workingTV.text = ""
        resultTV = findViewById(R.id.resultTV)
        resultTV.text = ""
    }
    fun equalsfunction(view: View) {}
}
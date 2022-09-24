package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.*
import java.lang.Exception
import androidx.appcompat.app.AlertDialog



class MainActivity : AppCompatActivity() {
    private var strNumber = StringBuilder()
    private lateinit var workingTV: TextView
    private var resultTV: String = ""
    var a = 1
    private lateinit var numberButtons : Array<Button>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CreateSimpleDialog()

        workingTV = findViewById(R.id.workingTV)

        numberButtons = arrayOf(id0, id1, id2, id3, id4, id5, id6, id7, id8, id9)
        for (i in numberButtons) { i.setOnClickListener { numberButtonclick(i) } }


        idplus.setOnClickListener {
            if (strNumber.length == 0) {
                workingTV.text = strNumber
            } else  {
                if (strNumber[strNumber.length-2] == '/' || strNumber[strNumber.length-2] == '*') {
                    strNumber.deleteCharAt(strNumber.length-2)
                    strNumber.deleteCharAt(strNumber.length-1)
                    strNumber.append('+')
                    workingTV.text = strNumber
                }   else    {
                    if(strNumber.last() == '+' || strNumber.last() == '-'
                        || strNumber.last() == '*' || strNumber.last() == '/'
                    ) {
                        strNumber.delete(strNumber.length-1, strNumber.length)
                        strNumber.append('+')
                        workingTV.text = strNumber
                    } else {
                    strNumber.append("+")
                    workingTV.text = strNumber}
                }
            }
        }

        idminus.setOnClickListener {
            if (strNumber.length == 0) {
                strNumber.append("-")
                workingTV.text = strNumber
            } else  {
                if (strNumber.last() == '+' || strNumber.last() == '-') {
                    strNumber.delete(strNumber.length-1, strNumber.length)
                    strNumber.append('-')
                    workingTV.text = strNumber
                }   else    {
                    strNumber.append("-")
                    workingTV.text = strNumber
                }
            }
        }

        idmultiply.setOnClickListener {
            if (strNumber.length == 0 || strNumber.last() == '(') {
                workingTV.text = strNumber
            } else { if (strNumber.last() == '*' || strNumber.last()=='/'
                || strNumber.last()=='.' || strNumber.last()=='+'
                || strNumber.last()=='-'){
                strNumber.delete(strNumber.length-1, strNumber.length)
                strNumber.append("*")
                workingTV.text = strNumber
            } else {
                strNumber.append("*")
                workingTV.text = strNumber
                }
            }
            try{
                if (strNumber.length >=2 && strNumber[strNumber.length-2] == '/' || strNumber[strNumber.length-2] == '*'
                    || strNumber[strNumber.length-2] == '+' || strNumber[strNumber.length-2] == '-'){
                    strNumber.deleteCharAt(strNumber.length-2)
                    workingTV.text = strNumber
                }} catch (e: Exception) {
                strNumber.delete(0, strNumber.length)
                workingTV.text = ""
            }
        }


        iddivide.setOnClickListener {
            if (strNumber.length == 0 || strNumber.last() == '(') {
                workingTV.text = strNumber
            } else { if (strNumber.last() == '*' || strNumber.last()=='/'
                || strNumber.last()=='.' || strNumber.last()=='+'
                || strNumber.last()=='-'){
                strNumber.delete(strNumber.length-1, strNumber.length)
                strNumber.append("/")
                workingTV.text = strNumber
            } else {
                strNumber.append("/")
                workingTV.text = strNumber
                }
            }
            try{
            if (strNumber.length >=2 && strNumber[strNumber.length-2] == '/' || strNumber[strNumber.length-2] == '*'
                || strNumber[strNumber.length-2] == '+' || strNumber[strNumber.length-2] == '-'){
                strNumber.deleteCharAt(strNumber.length-2)
                workingTV.text = strNumber
            }} catch (e: Exception) {
                strNumber.delete(0, strNumber.length)
                workingTV.text = ""
            }
        }

        idbrackets.setOnClickListener {
            try {if (a % 2 != 0) {
                strNumber.append(idbrackets.text[0])
                workingTV.text = strNumber
                a++
            } else {
                if (a % 2 == 0 && strNumber.last() != '(') {
                    strNumber.append(idbrackets.text[3])
                    workingTV.text = strNumber
                    a++
                } else {
                    workingTV.text = strNumber
                }
            }
            } catch (e: Exception){
                strNumber.append(idbrackets.text[0])
                workingTV.text = strNumber
                a++
            }
        }

        AC.setOnClickListener {
            strNumber.delete(0,strNumber.length)
            resultTV = ""
            workingTV.text = strNumber
            a = 1
        }
        iddelete.setOnClickListener{
            try {
                if (strNumber.last() == ')' || strNumber.last() == '(') {
                    strNumber.setLength(strNumber.length - 1)
                    workingTV.text = strNumber
                    a--
                } else {
                    strNumber.setLength(strNumber.length - 1)
                    workingTV.text = strNumber
                    }
                }
            catch (e: Exception){
                workingTV.text = ""}
        }
        idequal.setOnClickListener {
            if (strNumber.length == 0){
                workingTV.text = ""
            }
            if (a % 2 == 0 && strNumber.length != 1) {
                strNumber.append(')')
            }
            if (strNumber.lastOrNull() == '*' || strNumber.lastOrNull() == '/'
                || strNumber.lastOrNull() == '.') {
                strNumber.delete(strNumber.length-1, strNumber.length)
            }
            if(strNumber.length >= 1) {
                val e = Expression(strNumber.toString())
                resultTV = e.calculate().toString()
                strNumber.delete(0, strNumber.length)
                workingTV.text = resultTV
                strNumber.append(resultTV)
                a=1 }
            else { if(workingTV.text == "Error in expression") {
                resultTV = ""
                workingTV.text = ""
                strNumber.delete(0, strNumber.length)
            } else {
                strNumber.append(workingTV.text)
                workingTV.text = resultTV
                resultTV = ""
                a=1 }
            }
            if (workingTV.text == "NaN") {
                workingTV.text = "Error in expression"
                strNumber.delete(0, strNumber.length)
            }

        }
        idpercents.setOnClickListener {
            if (a % 2 == 0 && strNumber.length != 1) {
                strNumber.append(')')
            }
            if (strNumber.length>=1) {
                val e = Expression(strNumber.toString())
                var a = e.calculate()/100
                strNumber.delete(0, strNumber.length)
                strNumber.append(a.toString())
                workingTV.text = "$a"
            } else {
                strNumber.delete(0, strNumber.length)
                workingTV.text = "Nothing to count"
            }
            if (workingTV.text == "NaN") {
                workingTV.text = "Error in expression"
                strNumber.delete(0, strNumber.length)
            }
        }
        iddot.setOnClickListener {
            var index1 : Int = strNumber.length-1
            var index2 : Int = strNumber.length-1
            if (strNumber.length == 0) {
                workingTV.text = strNumber
            } else {
            while (strNumber[index2] != '*' && strNumber[index2] != '/' && strNumber[index2] != '('
                && strNumber[index2] != '(' && strNumber[index2] != '+' && strNumber[index2] != '-' && index2!=0)
            {
                index2--
            }
            while (strNumber.toString()[index1] != '.' && index2 < index1 && index1!=0) {
                index1--
            }
            if (index1 == index2 && strNumber[0] != '.') {
                strNumber.append(".")
                workingTV.text = strNumber
            } else {
                workingTV.text = strNumber
                }
            }
            if (strNumber[strNumber.length-2] == '+' || strNumber[strNumber.length-2] == '-'
                || strNumber[strNumber.length-2] == '*' || strNumber[strNumber.length-2] == '/'
                || strNumber[strNumber.length-2] == ')' || strNumber[strNumber.length-2] == '('
            ){
                strNumber.delete(strNumber.length-1, strNumber.length)
                workingTV.text = strNumber
            }
        }


        idsin.setOnClickListener {
            mXparser.setDegreesMode()
            var e = Expression("sin(90)")
            Log.d("MyLog", "sin90 v gradusah raven: " + e.calculate())
            mXparser.setRadiansMode()
            e = Expression("sin(90)")
        }









    }

    fun CreateSimpleDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Вітаю дарагі карыстальнік! \nHello dear user!")
        builder.setMessage(" Перад табой бэта-версія праграмы калькулятар. Спадзяюся што ў цябе застануцца выключна прыемныя ўраджанні." +
        "\n This is the beta version of the calculator program. I hope that you will have good experiences with it.")
        builder.setIcon(R.drawable.imagetitle)
        builder.setPositiveButton("Continue", { dialog, which ->
        })

        builder.show()
    }

    fun numberButtonclick(btn : Button) {
        strNumber.append(btn.text)
        workingTV.text = strNumber
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()

    }
}


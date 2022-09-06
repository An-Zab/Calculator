package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.*
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private var strNumber = StringBuilder()
    private lateinit var workingTV: TextView
    private lateinit var resultTV: TextView
    var a = 1
    private lateinit var numberButtons : Array<Button>
    private lateinit var actionButtons : Array<Button>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workingTV = findViewById(R.id.workingTV)
        resultTV = findViewById(R.id.resultTV)

        numberButtons = arrayOf(id0, id1, id2, id3, id4, id5, id6, id7, id8, id9)
        for (i in numberButtons) { i.setOnClickListener { numberButtonclick(i) } }

        actionButtons = arrayOf(idplus, idminus, idbrackets)
        for (i in actionButtons) { i.setOnClickListener { actionButtonclick(i) } }

        idmultiply.setOnClickListener {
            var index : Int = strNumber.length-1

            if (strNumber.length == 0) {
                workingTV.text = strNumber
            }

            if (strNumber.toString()[index]=='*' || strNumber.toString()[index]=='/' || strNumber.toString()[index]=='('
                || strNumber.toString()[index]==')' || strNumber.toString()[index]=='.'){
                strNumber.delete(strNumber.length-1, strNumber.length)
                strNumber.append("*")
                workingTV.text = strNumber
            }
            else { strNumber.append("*")
                workingTV.text = strNumber }
            index++
        }


        iddivide.setOnClickListener {
            var index : Int = strNumber.length-1
            if (strNumber.length == 0) {
                workingTV.text = strNumber
            }
            if (strNumber.toString()[index]=='*' || strNumber.toString()[index]=='/' || strNumber.toString()[index]=='('
                || strNumber.toString()[index]==')' || strNumber.toString()[index]=='.'){
                strNumber.delete(strNumber.length-1, strNumber.length)
                strNumber.append("/")
                workingTV.text = strNumber
            }
            else { strNumber.append("/")
                workingTV.text = strNumber }
            index++
        }

        idbrackets.setOnClickListener {
            if (a % 2 != 0) {
            strNumber.append(idbrackets.text[0])
            workingTV.text = strNumber
            }
            else  {strNumber.append(idbrackets.text[3])
            workingTV.text = strNumber}
            a++
        }
        AC.setOnClickListener {
            strNumber.delete(0,strNumber.length)
            resultTV.text = ""
            workingTV.text = strNumber
            a = 1
        }
        iddelete.setOnClickListener{
            try {
                strNumber.setLength(strNumber.length-1)
                workingTV.text = strNumber
            } catch (e: Exception){
                workingTV.text = ""
            }
        }
        idequal.setOnClickListener {
            if(strNumber.length >= 1) {
                val e = Expression(strNumber.toString())
                resultTV.text = e.calculate().toString()
                strNumber.delete(0, strNumber.length)
                a=1 }
            else {
                strNumber.append(resultTV.text)
                workingTV.text = strNumber
                resultTV.text = ""
                a=1 }
        }
        idpercents.setOnClickListener {
            if (strNumber.length>=1) {
                val e = Expression(strNumber.toString())
                var a = e.calculate()/100
                strNumber.delete(0, strNumber.length)
                strNumber.append(a.toString())
                resultTV.text = "$a"
            } else {
                strNumber.delete(0, strNumber.length)
                resultTV.text = "Format error"
            }
        }
        iddot.setOnClickListener {
            var index1 : Int = strNumber.length-1
            var index2 : Int = strNumber.length-1
            if (strNumber.length == 0) {
                strNumber.append(".")
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
        }



    }
    fun actionButtonclick(btn: Button) {
        strNumber.append(btn.text)
        workingTV.text = strNumber
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


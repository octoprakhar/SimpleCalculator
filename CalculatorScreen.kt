package com.example.flowrowpractice

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CalculatorScreen(){

    var show by remember {
        mutableStateOf("")
    }

    var result by remember {
        mutableStateOf(0.0)
    }
    var operation by remember {
        mutableStateOf("0")
    }

    var answer by remember {
        mutableStateOf("0")
    }

    var newTerm by remember {
        mutableStateOf<String?>(null)
    }
    val row = 4
    val column = 4

    val buttonList = listOf<String>(
        "C","X","%","/","7","8","9","x","4","5","6","-","1","2","3","+"
    )

    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = show,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            fontSize = 80.sp,
            fontWeight = FontWeight.ExtraBold,
            fontStyle = FontStyle.Italic
        )
        FlowRow (
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Bottom,
            maxItemsInEachRow = column
        ){

            val itemModifier = Modifier
                .padding(4.dp)
                .height(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.DarkGray)



            buttonList.forEach {
                    buttonText->
                ButtonLayout(
                    text = buttonText,
                    onClick = {
                              if (buttonText == "1" || buttonText == "2" || buttonText == "3" || buttonText == "4" || buttonText == "5" || buttonText == "6" || buttonText == "7" || buttonText == "8" || buttonText == "9" ){
                                  if (newTerm == null){
                                      newTerm = buttonText
                                      show += buttonText
                                      Log.d("CalculatorScreenResponse","value of newTerm : $newTerm , answer : $answer")
                                  }
                                  else{
                                      newTerm += buttonText
                                      show += buttonText

                                      Log.d("CalculatorScreenResponse","value of newTerm : $newTerm , answer : $answer")

                                  }

                              }else if ((buttonText == "+" ||buttonText == "x" ||buttonText == "/" ||buttonText == "-" ||buttonText == "%") && newTerm != null ){
                                  show += buttonText

                                  answer = newTerm ?: "0"
                                  operation = buttonText
                                  newTerm = null
                                  Log.d("CalculatorScreenResponse","value of newTerm : $newTerm , answer : $answer")

                              }else if (buttonText == "C"){
                                  show = ""
                                  answer = "0"
                                  newTerm = null
                                  Log.d("CalculatorScreenResponse","value of newTerm : $newTerm , answer : $answer")

                              }else if (buttonText == "X" && newTerm != null){
                                  show = show.dropLast(1)
                                  newTerm = newTerm?.dropLast(1)
                                  Log.d("CalculatorScreenResponse","value of newTerm : $newTerm , answer : $answer")

                              }
                    },
                    modifier = itemModifier.weight(1f))

            }
            // Manually add items to the last row
            ButtonLayout(
                text = "0",
                onClick = {
                          newTerm += "0"
                    show += "0"
                    Log.d("CalculatorScreenResponse","value of newTerm : $newTerm , answer : $answer")

                          },
                modifier = itemModifier.weight(2f))
            ButtonLayout(
                text = ".",
                onClick = {
                          if (!newTerm!!.contains(".") && newTerm != null){
                              newTerm += "."
                              show += "."
                              Log.d("CalculatorScreenResponse","value of newTerm : $newTerm , answer : $answer")

                          }else if (newTerm == null){
                              show += "."

                              newTerm = "."
                              Log.d("CalculatorScreenResponse","value of newTerm : $newTerm , answer : $answer")

                          }

                          },
                modifier = itemModifier.weight(1f))
            ButtonLayout(
                text = "=",
                onClick = {
                    if ( newTerm != null){
                        when(operation){
                            "+" -> {
                                result = answer.toDouble() + newTerm!!.toDouble()
                                show = result.toString()
                                Log.d("CalculatorScreenResponse","value of newTerm : $newTerm , answer : $answer, result : $result")

                            }
                            "-" ->{
                                result = answer.toDouble() - newTerm!!.toDouble()
                                show = result.toString()

                                Log.d("CalculatorScreenResponse","value of newTerm : $newTerm , answer : $answer, result : $result")


                            }
                            "x" ->{
                                result = answer.toDouble() * newTerm!!.toDouble()
                                show = result.toString()

                                Log.d("CalculatorScreenResponse","value of newTerm : $newTerm , answer : $answer, result : $result")


                            }
                            "/" ->{
                                result = answer.toDouble() / newTerm!!.toDouble()
                                show = result.toString()

                                Log.d("CalculatorScreenResponse","value of newTerm : $newTerm , answer : $answer, result : $result")


                            }
                            "%" -> {
                                result = answer.toDouble()% newTerm!!.toDouble()
                                show = result.toString()

                                Log.d("CalculatorScreenResponse","value of newTerm : $newTerm , answer : $answer, result : $result")


                            }
                        }

                    }
                },
                modifier = itemModifier.weight(1f)
            )

        }
    }
}

@Composable
fun ButtonLayout(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .padding(4.dp)
        .height(80.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(Color.DarkGray)
){
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)

    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}


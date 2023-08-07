package com.app.calculadoradeimccompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.calculadoradeimccompose.calculo.CalcularImc
import com.app.calculadoradeimccompose.ui.theme.CalculadoraDeIMCComposeTheme
import com.app.calculadoradeimccompose.ui.theme.DARK_BLUE
import com.app.calculadoradeimccompose.ui.theme.LIGHT_BLUE
import com.app.calculadoradeimccompose.ui.theme.WHITE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraDeIMCComposeTheme {
                CalculadoraImc()

            }
        }
    }

}

@Composable
fun CalculadoraImc() {
    val context = LocalContext.current
    val calcularImc = CalcularImc()
    var peso by remember {
        mutableStateOf("")
    }

    var altura by remember {
        mutableStateOf("")
    }

    var textoResultado by remember {
        mutableStateOf(value = "")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = LIGHT_BLUE,
                contentColor = WHITE,
                title = {
                    Text(text = "Calculadora de IMC")
                },
            actions = {
                IconButton(onClick = {
                    peso = ""
                    altura = ""
                    textoResultado = ""
                }) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_refresh),
                        contentDescription = "Icone de resetar todos os campos"
                    )
                }

            }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(50.dp),
                text = "Calculadora de IMC",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = LIGHT_BLUE

            )

            OutlinedTextField(
                value = peso,
                onValueChange = {
                    peso = it
                },
                label = {
                    Text("Peso (kg)")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = LIGHT_BLUE,
                    focusedBorderColor = LIGHT_BLUE,
                    focusedLabelColor = DARK_BLUE,
                    textColor = DARK_BLUE
                ),
                textStyle = TextStyle(
                    color = DARK_BLUE,
                    fontSize = 18.sp
                ),
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = altura,
                onValueChange = {
                    altura = it
                },
                label = {
                    Text("Altura (cm)")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = LIGHT_BLUE,
                    focusedBorderColor = LIGHT_BLUE,
                    focusedLabelColor = DARK_BLUE,
                    textColor = DARK_BLUE
                ),
                textStyle = TextStyle(
                    color = DARK_BLUE,
                    fontSize = 18.sp
                ),
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Button(
                onClick = {
                    if (peso.isEmpty() || altura.isEmpty()) {
                        Toast.makeText(context,
                            "Preencha todos o campos",
                        Toast.LENGTH_SHORT
                            ).show()
                    }else{
                        calcularImc.calcularIMC(
                            peso, altura)

                        textoResultado = calcularImc.resultadoImc()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LIGHT_BLUE,
                    contentColor = WHITE
                )
            ) {
                Text(
                    text = "Calcular IMC",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                textoResultado,
                fontSize = 18.sp,
                color = DARK_BLUE,
                fontWeight = FontWeight.Bold,


                )

        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculadoraDeIMCComposeTheme {
        CalculadoraImc()
    }
}
package com.app.calculadoradeimccompose.calculo

import java.text.DecimalFormat

class CalcularImc {

    private var resultadoImc: String = ""

    fun calcularIMC(peso: String, altura: String) {
        val pesoConvertido = peso.toDouble()

        val alturaConvertido = altura.toDouble()

        val resultado: String

        val imc = pesoConvertido / (alturaConvertido * alturaConvertido)
        val decimalFormat = DecimalFormat("0.00")
        when (imc) {
            in 0.0..18.4 ->resultado = "Peso Baixo \n IMC: ${decimalFormat.format(imc)}"
            in 18.4..24.9 -> resultado = "Peso Normal \n IMC: ${decimalFormat.format(imc)}"
            in 25.0..29.9 ->resultado =  "Sobrepeso \n IMC: ${decimalFormat.format(imc)}"
            in 30.0..34.9 -> resultado = "Obesidade (Grau 1) \n IMC: ${decimalFormat.format(imc)}"
            in 35.0..39.9 ->resultado =  "Obesidade Severa (Grau 2) \n IMC: ${decimalFormat.format(imc)}"
            else ->resultado =  "Obesidade Mórbida (Grau 3) \n IMC: ${decimalFormat.format(imc)}"
        }
        resultadoImc = resultado
    }

    fun resultadoImc (): String{
        return resultadoImc;
    }
}
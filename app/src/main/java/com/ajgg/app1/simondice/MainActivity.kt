package com.ajgg.app1.simondice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bAzul = findViewById<Button>(R.id.b1)
        val bAmarillo = findViewById<Button>(R.id.b2)
        val bRojo = findViewById<Button>(R.id.b3)
        val bVerde = findViewById<Button>(R.id.b4)
        val texto = findViewById<TextView>(R.id.texto)
        val bStart = findViewById<Button>(R.id.bStart)
        val botones: ArrayList<Button> = ArrayList()

        botones.add(bAzul)
        botones.add(bAmarillo)
        botones.add(bRojo)
        botones.add(bVerde)

        comienzo(texto, botones)



        bAzul.setOnClickListener() {


        }
        bAmarillo.setOnClickListener() {


        }
        bRojo.setOnClickListener() {


        }
        bVerde.setOnClickListener() {


        }

        bStart.setOnClickListener() {
            comienzo(texto, botones)

        }

    }


    fun comienzo(texto: TextView, botones: ArrayList<Button>) {
        var secuencia: ArrayList<Int> = ArrayList()
        var presionado = true
        juego(secuencia, botones)

    }

    fun random(): Int {
        val random: Int = Random.nextInt(0, 3)
        return random
    }


    fun bEnable(botones: ArrayList<Button>, bol: Boolean) {
        botones[0].isEnabled = bol
        botones[1].isEnabled = bol
        botones[2].isEnabled = bol
        botones[3].isEnabled = bol
    }

    fun background(idBoton: Int, botones: ArrayList<Button>, presionado: Boolean) {
        if (presionado) {

            when (idBoton) {

                0 -> botones[idBoton].setBackgroundColor(resources.getColor(R.color.azul2))
                1 -> botones[idBoton].setBackgroundColor(resources.getColor(R.color.amarillo2))
                2 -> botones[idBoton].setBackgroundColor(resources.getColor(R.color.rojo2))
                3 -> botones[idBoton].setBackgroundColor(resources.getColor(R.color.verde2))

            }
        } else {
            when (idBoton) {
                0 -> {
                    botones[idBoton].setBackgroundColor(resources.getColor(R.color.azul))

                }
                1 -> {
                    botones[idBoton].setBackgroundColor(resources.getColor(R.color.amarillo))

                }
                2 -> {
                    botones[idBoton].setBackgroundColor(resources.getColor(R.color.rojo))

                }
                3 -> {
                    botones[idBoton].setBackgroundColor(resources.getColor(R.color.verde))

                }

            }
        }

    }

    fun juego(secuencia: ArrayList<Int>, botones: ArrayList<Button>) {
        var continuar: Boolean = true
        var ronda: Int = 0
        var random: Int=0
        GlobalScope.launch {
            runOnUiThread {
                run() {
                    random = random()
                    ronda = ronda + 1
                    for (i in 0..ronda step 1) {
                        background(random, botones, true)
                        GlobalScope.launch {
                            delay(500L)
                        }


                    }


                }

            }
            secuencia.add(random)
            if (continuar == true)
                juego(secuencia, botones)
        }

    }
}



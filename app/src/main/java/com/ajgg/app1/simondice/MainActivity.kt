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

    var continuar: Boolean = true
    var posicion:Int=0
    lateinit var secuencia : java.util.ArrayList<Int>
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Botones y text view
        val bAzul = findViewById<Button>(R.id.b1)
        val bAmarillo = findViewById<Button>(R.id.b2)
        val bRojo = findViewById<Button>(R.id.b3)
        val bVerde = findViewById<Button>(R.id.b4)
        val texto = findViewById<TextView>(R.id.texto)
        val bStart = findViewById<Button>(R.id.bStart)
        val botones: ArrayList<Button> = ArrayList()


        //añadimos los botones a un array
        botones.add(bAzul)
        botones.add(bAmarillo)
        botones.add(bRojo)
        botones.add(bVerde)

        //Comenzamos el juego
        comienzo(texto, botones)


        //si pulsas el boton azul entonces envias un 0 para comparar a la secuencia
        bAzul.setOnClickListener() {
        comparacion(secuencia,0, posicion)

        }
        //si pulsas el boton amarillo entonces envias un 1 para comparar a la secuencia
        bAmarillo.setOnClickListener() {
            comparacion(secuencia,1, posicion)

        }
        //si pulsas el boton rojo entonces envias un 2 para comparar a la secuencia
        bRojo.setOnClickListener() {
            comparacion(secuencia,2, posicion)

        }
        //si pulsas el boton verde entonces envias un 3 para comparar a la secuencia
        bVerde.setOnClickListener() {
            comparacion(secuencia,3, posicion)

        }

        bStart.setOnClickListener() {
            comienzo(texto, botones)

        }

    }


    fun comienzo(texto: TextView, botones: ArrayList<Button>) {
        //Inicializamos la secuencia
        var secuencia: ArrayList<Int> = ArrayList()
        var presionado = true
        juego(secuencia, botones)

    }
    fun comparacion(secuencia: ArrayList<Int>, x:Int, posicion:Int): Boolean{
        if(secuencia[posicion]==x){
            continuar=true
            return continuar
        }else{
            continuar=false
            return continuar
        }
    }

    //Funcion que devuelve un int random entre 0 y 3
    fun random(): Int {
        val random: Int = Random.nextInt(0, 3)
        return random
    }

    //Funcion que deja usar o no los botones dependiendo del boolean bol
    fun bEnable(botones: ArrayList<Button>, bol: Boolean) {
        botones[0].isEnabled = bol
        botones[1].isEnabled = bol
        botones[2].isEnabled = bol
        botones[3].isEnabled = bol
    }

    //Funcion que cambia de color a mas claro o al normal dependiendo del boolean presionado
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
        //posicion del array para la comparacion
        posicion=0
        var random: Int = 0
        GlobalScope.launch {
            runOnUiThread {
                run() {
                    //Generamos y añadimos un random a la secuencia
                    random = random()
                    secuencia.add(random)
                    //for que cambia de color el boton dependiendo del random y pausa 500 milis

                    for (i in 0..secuencia.size step 1) {
                        background(random, botones, true)
                        GlobalScope.launch {
                            delay(500L)
                        }
                    }
                }
            }

            //Mientras sea true se llama de nuevo al metodo juego
            //pasandole por parametros el array de botones
            //y la secuencia con el random añadido en la funcion juego anterior asi siempre sera la misma mas un random mas
            if (continuar == true)
                juego(secuencia, botones)
        }

    }
}



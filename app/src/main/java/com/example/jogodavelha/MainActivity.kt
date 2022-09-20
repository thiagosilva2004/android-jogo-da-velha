package com.example.jogodavelha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var isPlayer1 = true
    var isGameEnd = false;

    private lateinit var top: ImageView
    private lateinit var topStart: ImageView
    private lateinit var topEnd: ImageView

    private lateinit var center: ImageView
    private lateinit var centerStart: ImageView
    private lateinit var centerEnd: ImageView

    private lateinit var bottom: ImageView
    private lateinit var bottomStart: ImageView
    private lateinit var bottomEnd: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        top = findViewById(R.id.top)
        topStart = findViewById(R.id.topStart)
        topEnd = findViewById(R.id.topEnd)

        center= findViewById(R.id.center)
        centerStart = findViewById(R.id.centerStart)
        centerEnd = findViewById(R.id.centerEnd)

        bottom = findViewById(R.id.bottom)
        bottomStart = findViewById(R.id.bottomStart)
        bottomEnd = findViewById(R.id.bottomEnd)

        val reset: Button = findViewById(R.id.btnReiniciar)
        reset.setOnClickListener {
            resetar(topStart)
            resetar(top)
            resetar(topEnd)

            resetar(center)
            resetar(centerStart)
            resetar(centerEnd)

            resetar(bottom)
            resetar(bottomStart)
            resetar(bottomEnd)
        }

        configureBox(top)
        configureBox(topStart)
        configureBox(topEnd)

        configureBox(center)
        configureBox(centerStart)
        configureBox(centerEnd)

        configureBox(bottom)
        configureBox(bottomStart)
        configureBox(bottomEnd)
    }

    private fun resetar(box: ImageView){
        box.setImageDrawable(null)
        box.tag = null
        isPlayer1 = true
        isGameEnd = false
    }

    private fun configureBox(box: ImageView){
        box.setOnClickListener {
            if (box.tag == null && !isGameEnd){
                if (isPlayer1){
                    box.setImageResource(R.drawable.ic_baseline_remove_circle_24)
                    isPlayer1 = false
                    box.tag = 1
                }else{
                    box.setImageResource(R.drawable.ic_baseline_close_24)
                    isPlayer1 = true
                    box.tag = 2
                }

                if (playerWin(1)){
                    Toast.makeText(this@MainActivity, "Player 1 Venceu", Toast.LENGTH_SHORT).show()
                    isGameEnd = true
                }  else if (playerWin(2)){
                    Toast.makeText(this@MainActivity, "Player 2 Venceu", Toast.LENGTH_SHORT).show()
                    isGameEnd = true
                }else if (gameEmpatado()){
                    Toast.makeText(this@MainActivity, "Empate", Toast.LENGTH_SHORT).show()
                    isGameEnd = true
                }
            }
        }
    }

    private fun gameEmpatado(): Boolean {
        return top.tag != null && topEnd.tag != null && topStart.tag != null &&
                center.tag != null && centerEnd.tag != null && centerStart.tag != null &&
                bottom.tag != null && bottomEnd.tag != null && bottomStart.tag != null
    }

    private fun playerWin(value: Int): Boolean {
        if( (top.tag == value && center.tag == value && bottom.tag == value) ||
            (topStart.tag == value && centerStart.tag == value && bottomStart.tag == value) ||
            (topEnd.tag == value && centerEnd.tag == value && bottomEnd.tag == value) ||

            (topStart.tag == value && top.tag == value && topEnd.tag == value) ||
            (centerStart.tag == value && center.tag == value && centerEnd.tag == value) ||
            (bottomStart.tag == value && bottom.tag == value && bottomEnd.tag == value) ||

            (topStart.tag == value && center.tag == value && bottomEnd.tag == value) ||
            (topEnd.tag == value && center.tag == value && bottomStart.tag == value)
          )
        {
            return true
        }
        return false
    }
}
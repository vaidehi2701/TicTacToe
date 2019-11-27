package com.example.tictactoe

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun buttonClick(view: View) {

        val buttonSelected = view as Button
        var cellId=0

        when(buttonSelected.id){
            R.id.b1 -> cellId = 1
            R.id.b2 -> cellId = 2
            R.id.b3 -> cellId = 3
            R.id.b4 -> cellId = 4
            R.id.b5 -> cellId = 5
            R.id.b6 -> cellId = 6
            R.id.b7 -> cellId = 7
            R.id.b8 -> cellId = 8
            R.id.b9 -> cellId = 9

        }
        playGame(cellId,buttonSelected)
    }
    var player1=ArrayList<Int>()
    var player2=ArrayList<Int>()

    var activePlayer=1

    private fun playGame(cellId: Int, buttonSelected: Button) {

        if (activePlayer == 1){
            buttonSelected.text="X"
            buttonSelected.setBackgroundColor(Color.parseColor("#FF9193"))
            player1.add(cellId)
            activePlayer=2
            AutoPlay()
        }
        else{
            buttonSelected.text="O"
            buttonSelected.setBackgroundColor(Color.parseColor("#009193"))
            player2.add(cellId)
            activePlayer=1
        }
        buttonSelected.isEnabled=false
        checkWinner()
    }


    private fun checkWinner() {
        var winner = -1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3))
        { winner=1 }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3))
        { winner=2 }

        if (player1.contains(4) && player1.contains(5) && player1.contains(6))
        { winner=1 }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6))
        { winner=2 }

        if (player1.contains(7) && player1.contains(8) && player1.contains(9))
        { winner=1 }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9))
        { winner=2 }

        if (player1.contains(1) && player1.contains(4) && player1.contains(7))
        { winner=1 }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7))
        { winner=2 }

        if (player1.contains(2) && player1.contains(5) && player1.contains(8))
        { winner=1 }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8))
        { winner=2 }

        if (player1.contains(3) && player1.contains(6) && player1.contains(9))
        { winner=1 }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9))
        { winner=2 }

        if (player1.contains(1) && player1.contains(5) && player1.contains(9))
        { winner=1 }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9))
        { winner=2 }

        if (player1.contains(3) && player1.contains(5) && player1.contains(7))
        { winner=1 }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7))
        { winner=2 }

        if (winner != -1){
            if (winner==1){
                Toast.makeText(this,"Player1 won Match", Toast.LENGTH_LONG).show()
                stopTouch()
            }
            else{
                Toast.makeText(this,"Player2 won Match", Toast.LENGTH_LONG).show()
                stopTouch()
            }
        }
    }

    fun stopTouch()
    {
        b1.isEnabled = false
        b2.isEnabled = false
        b3.isEnabled = false
        b4.isEnabled = false
        b5.isEnabled = false
        b6.isEnabled = false
        b7.isEnabled = false
        b8.isEnabled = false
        b9.isEnabled = false
    }
    private fun AutoPlay(){
        var emptyCell=ArrayList<Int>()
        for (cellId in 1..9){
            if (!(player1.contains(cellId)) || (player2.contains(cellId))){
                emptyCell.add(cellId)
            }
        }
        val r= java.util.Random()
        val randIndex = r.nextInt(emptyCell.size-0)+0
        val cellId = emptyCell[randIndex]

        val buttonSelected: Button
        when(cellId){
            1 -> buttonSelected = b1
            2 -> buttonSelected = b2
            3 -> buttonSelected = b3
            4 -> buttonSelected = b4
            5 -> buttonSelected = b5
            6 -> buttonSelected = b6
            7 -> buttonSelected = b7
            8 -> buttonSelected = b8
            9 -> buttonSelected = b9
            else -> buttonSelected = b1
        }
        playGame(cellId,buttonSelected)
    }

    @SuppressLint("ResourceAsColor")
    fun restartGame(view: View)
    {
        b1.setBackgroundColor(R.color.white)
        b2.setBackgroundColor(R.color.white)
        b3.setBackgroundColor(R.color.white)
        b4.setBackgroundColor(R.color.white)
        b5.setBackgroundColor(R.color.white)
        b6.setBackgroundColor(R.color.white)
        b7.setBackgroundColor(R.color.white)
        b8.setBackgroundColor(R.color.white)
        b9.setBackgroundColor(R.color.white)


        b1.text = ""
        b2.text = ""
        b3.text = ""
        b4.text = ""
        b5.text = ""
        b6.text = ""
        b7.text = ""
        b8.text = ""
        b9.text = ""

        player1.clear()
        player2.clear()
        activePlayer = 1

        b1.isEnabled = true
        b2.isEnabled = true
        b3.isEnabled = true
        b4.isEnabled = true
        b5.isEnabled = true
        b6.isEnabled = true
        b7.isEnabled = true
        b8.isEnabled = true
        b9.isEnabled = true

    }
}

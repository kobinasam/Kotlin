package com.example.tictactoe

import android.graphics.Color.blue
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun buClick(view:View){
        val buSelected = view as Button
        var cellId = 0
            when(buSelected.id){
                R.id.bu1 ->  cellId = 1
                R.id.bu2 ->  cellId = 2
                R.id.bu3 ->  cellId = 3
                R.id.bu4 ->  cellId = 4
                R.id.bu5 ->  cellId = 5
                R.id.bu6 ->  cellId = 6
                R.id.bu7 ->  cellId = 7
                R.id.bu8 ->  cellId = 8
                R.id.bu9 ->  cellId = 9

            }
        playGame(cellId, buSelected)
    }
    var activePlayer = 1
    var playerOne = ArrayList<Int>()
    var playerTwo = ArrayList<Int>()

     fun playGame(cellId:Int, buSelected:Button){
        if(activePlayer == 1){
            buSelected.text = "X"
            buSelected.setBackgroundColor(getResources().getColor(R.color.red))
            playerOne.add(cellId)
            activePlayer = 2
            autoPlay()
        }else{
            buSelected.text = "O"
            buSelected.setBackgroundColor(getResources().getColor(R.color.green))
            playerTwo.add(cellId)
            activePlayer = 1

        }
         buSelected.isEnabled = false
         checkWinner()

//         if(Toast.toString().contains("Player One is the winner")){
//             buSelected.isEnabled = false
//         }
    }





    fun checkWinner(){
        var winner = 0

        //row

        if(playerOne.contains(1) && playerOne.contains(2) && playerOne.contains(3)){
            winner = 1
        }
        if(playerTwo.contains(1) && playerTwo.contains(2) && playerTwo.contains(3)){
            winner = 2
        }

        if(playerOne.contains(4) && playerOne.contains(5) && playerOne.contains(6)){
            winner = 1
        }
        if(playerTwo.contains(4) && playerTwo.contains(5) && playerTwo.contains(6)){
            winner = 2
        }
        if(playerOne.contains(7) && playerOne.contains(8) && playerOne.contains(9)){
            winner = 1
        }
        if(playerTwo.contains(7) && playerTwo.contains(8) && playerTwo.contains(9)){
            winner = 2
        }
        //column

        if(playerOne.contains(1) && playerOne.contains(4) && playerOne.contains(7)){
            winner = 1
        }
        if(playerTwo.contains(1) && playerTwo.contains(4) && playerTwo.contains(7)){
            winner = 2
        }
        if(playerOne.contains(2) && playerOne.contains(5) && playerOne.contains(8)){
            winner = 1
        }
        if(playerTwo.contains(2) && playerTwo.contains(4) && playerTwo.contains(8)){
            winner = 2
        }
        if(playerOne.contains(3) && playerOne.contains(6) && playerOne.contains(9)){
            winner = 1
        }
        if(playerTwo.contains(3) && playerTwo.contains(6) && playerTwo.contains(9)){
            winner = 2
        }
            //diagonal
        if(playerOne.contains(1) && playerOne.contains(5) && playerOne.contains(9)){
            winner = 1
        }
        if(playerTwo.contains(1) && playerTwo.contains(5) && playerTwo.contains(9)){
            winner = 2
        }
        if(playerOne.contains(3) && playerOne.contains(5) && playerOne.contains(7)){
            winner = 1
        }
        if(playerTwo.contains(3) && playerTwo.contains(5) && playerTwo.contains(7)){
            winner = 2
        }

        if(winner == 1){
            playerOneWinCount = playerTwoWinCount + 1
            Toast.makeText(this, "Player One is the Winner", Toast.LENGTH_LONG).show()
            restartGame()
        }else if(winner == 2){
            playerTwoWinCount = playerTwoWinCount + 1
            Toast.makeText(this, "Player Two is the Winner", Toast.LENGTH_LONG).show()
            restartGame()
        }
    }
    fun autoPlay(){
        var emptyCell = ArrayList<Int>()
        for(cellId in 1..9) {
            if (!(playerOne.contains(cellId) || playerTwo.contains(cellId))) {
                emptyCell.add(cellId)
            }
        }
        if(emptyCell.size == 0){
            restartGame()
        }
        val r = Random.Default
        val randIndex = r.nextInt(emptyCell.size)
        val cellId = emptyCell[randIndex]

        var buSelected:Button?
        buSelected = when(cellId){
            1->bu1
            2->bu2
            3->bu3
            4->bu4
            5->bu5
            6->bu6
            7->bu7
            8->bu8
            9->bu9
            else->{bu1}
        }
        playGame(cellId, buSelected)
    }
    var playerOneWinCount = 0
    var playerTwoWinCount = 0
    fun restartGame(){
        activePlayer = 1
        playerOne.clear()
        playerTwo.clear()

        for(cellId in 1..9){
            val buSelected:Button?
            buSelected = when(cellId){
                1->bu1
                2->bu2
                3->bu3
                4->bu4
                5->bu5
                6->bu6
                7->bu7
                8->bu8
                9->bu9
                else->{bu1}
            }
            buSelected.text = " "
            buSelected.setBackgroundColor(getResources().getColor(R.color.whitecolor))
            buSelected.isEnabled = true
            Toast.makeText(this, "Player One: $playerOneWinCount, Player Two: $playerTwoWinCount", Toast.LENGTH_LONG).show()
        }
    }
}
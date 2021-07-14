package com.example.android.connect4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int scoreCounter1 = 1;
    int scoreCounter2 = 1;

    boolean gameIsActive = true;

    // 0 for red 1 for blue
  int activePlayer = 0;
  //2 means unplayed field & getting a tag reference for each field
  int [] gameState = {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};

  int [][] winningPosition = {{0,1,2,3},{4,5,6, 7},{8,9,10,11},{12,13,14,15},// rows
          {0,4,8,12},{1,5,9,13}, {2,6,10,14},{3,7,11,15}//columns
    ,{0,5,10,15},{3,6,9,12}// diagram
     };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    TextView player1 = (TextView)findViewById(R.id.player1Score);
    TextView player2 = (TextView)findViewById(R.id.player2Score);

    // this misspelled dropIn

    public void drpoIn(View view){


        ImageView chips = (ImageView)view;


        // getting info from the tag if the filed is tapped or not
        int tappedField = Integer.parseInt(chips.getTag().toString());

        if (gameState[tappedField] == 2 && gameIsActive) {


            gameState[tappedField]= activePlayer;

            // remove image & set the image(by players turn) & bring it back again

            chips.animate().translationY(-1000);
            if (activePlayer == 0) {

                chips.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else {
                chips.setImageResource(R.drawable.blue);
                activePlayer = 0;
            }
            chips.animate().translationYBy(1000f).setDuration(300);

            checkForWins ();
        }
    }

    public void checkForWins (){

        for (int [] wins :winningPosition ){

            if (gameState[wins[0]] == gameState[wins[1]]
                    && gameState[wins[1]] ==gameState[wins[2]]
                    && gameState[wins[2]]==gameState[wins[3]]
                    &&gameState[wins[1]] != 2){

                gameIsActive=false;

                if (gameState[wins[0]]==0){

                    player1.setText("player1: " + scoreCounter1);
                    scoreCounter1++;

                }else if (gameState[wins[0]]==1) {
                    player2.setText("player2: "+scoreCounter2);
                    scoreCounter2++;
                }else{
                    // playagain
                }

            }
        }
    }

    public void playAgain(View view){
        activePlayer = 0;
        gameIsActive =true;

        for (int i = 0; i < gameState.length; i++){

            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gameBoard);
        for (int i = 0; i < gridLayout.getChildCount(); i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }
}

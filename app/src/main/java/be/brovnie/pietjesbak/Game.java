package be.brovnie.pietjesbak;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.graphics.Color;


public class Game extends Activity {

    public static final String PLAYER1_NAME = "player1";
    public static final String PLAYER2_NAME = "player2";
    private  TextView player1Score;
    private TextView player2Score;
    private TextView playerName1;
    private TextView playerName2;
    private TextView results;
    private TextView bestScore;
    private TextView resultsSum;
    private Button rollDices;
    private boolean firstGame;
    private String playerText1;
    private String playerText2;
    boolean player1;
    int counter = 0;
    int totalScorePlayer1 = 0;
    int totalScorePlayer2 = 0;
    private int sum;
    private int player1Chances = 6;
    private int player2Chances = 6;
    private int rounds = 0;
    //final Handler handler = new Handler();

    @Override

        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game);

            Intent intent = getIntent();
            playerName1 = (TextView) findViewById(R.id.player1);
            playerText1 = intent.getStringExtra(PLAYER1_NAME);
            playerName1.setText(playerText1);
            playerName2 = (TextView) findViewById(R.id.player2);
            playerText2 = intent.getStringExtra(PLAYER2_NAME);
            playerName2.setText(playerText2);
            player1Score = (TextView) findViewById(R.id.player1Score);
            player2Score = (TextView) findViewById(R.id.player2Score);
            player1Score.setText(Integer.toString(player1Chances));
            player2Score.setText(Integer.toString(player2Chances));
            results = (TextView)findViewById(R.id.roll_results);
            bestScore = (TextView)findViewById(R.id.roll_bestScore);
            player2Score = (TextView)findViewById(R.id.player2Score);
            resultsSum = (TextView)findViewById(R.id.roll_sum);
            rollDices = (Button) findViewById(R.id.btn_roll);
            player1 = true;
            firstGame = true;
        }

        //Steps:
        //KO 1. rzucic raz kostka kto wyrzuci wiecej zaczyna
        //KO 2. p1 rzuca 3 kostkami
        // KO     i. generuj 3 numery - random
        // KO     ii.podlicz numery
        // OK 3. p1 moze zdecydowac zeby rzucic jeszcze raz albo odpuscic
        // KO     i. pojawia sie przycisk "stoefen" po kliknieciu tura p1 sie konczy
        // 4. p1 wybiera iloma kostkami chce rzucic wszystkimi(3), 2, 1
        //      i.
        // OK 5. p1 rzuca kostkami
        // OK 6. powtorzyc step 3,4,5 , p1 moze max 3 razy w ciagu tury rwucic kostkami
        // OK 7. ostatni rezultat zostale
        // OK 8. p1 to samo co p2 i moze tyle razy rwucic jak p1 (jesli on zatrzymuje sie na pierwszym razie p2 moze tlko 1 raz rzucic)
        // OK 10. wygrywa ten co nie ma juz dostepnych kresek
        // extra buttons automatycznie wyrzuci zand, 3 appen

    // method ocClick

public void onClickRollTheDice(View view){
   // TextView playerName1 = (TextView)findViewById(R.id.player1);
    //playerName2 = (TextView)findViewById(R.id.player2);
    //player1Score = (TextView)findViewById(R.id.player1Score);

    if(counter == 3){
        counter = 0;
        //who won
        rounds++;
                if(rounds%2 == 0) {
                    if (totalScorePlayer1 > totalScorePlayer2) {
                        player1Chances--;
                    } else if (totalScorePlayer1 < totalScorePlayer2) {
                        player2Chances--;
                    }
                    player1Score.setText(Integer.toString(player1Chances));
                    player2Score.setText(Integer.toString(player2Chances));
                }
        if(player1 == true){
            player1 = false;
        } else {
            player1 = true;
        }
        changeColors();
    }

    //
    //First game
    if(firstGame == true){

        int[] arrNumbers = giveNumbers();
        String n = arrNumbers[0] + " " + arrNumbers[1] + " " + arrNumbers[2];
        results.setText(n);
        if(player1==true){
            totalScorePlayer1 = countResult(arrNumbers);
        } else {
            totalScorePlayer2 = countResult(arrNumbers);
        }
        //change player
        if(player1 == true){
            player1 = false;
        }
        counter++;
        if(counter == 2){
            if(totalScorePlayer1 > totalScorePlayer2){
                bestScore.setText(playerText1 +" begins!");
                firstGame = false;
                player1 = true;
                changeColors();
            } else if (totalScorePlayer1 < totalScorePlayer2){
                bestScore.setText(playerText2 + " begins!");
                firstGame = false;
                player1 = false;
                changeColors();
            } else{
                bestScore.setText("Draw! Let's try again");
                firstGame = true;
            }

            counter = 0;
            totalScorePlayer2 = 0;
            totalScorePlayer2 = 0;
        }
        //
        //ROLL DICE
    } else {
        //clean screen
        /*if(player1 == true){
            bestScore.setText("It's your turn!");
        }
*/      bestScore.setText("");
        //generate numbers
        int[] arrNumbers = giveNumbers();
        String n = arrNumbers[0] + " " + arrNumbers[1] + " " + arrNumbers[2];
        results.setText(n);
        sum = countResult(arrNumbers);
        resultsSum.setText(Integer.toString(sum));
        counter++;
        //results
            if(player1 == true) {
                totalScorePlayer1 = sum;
            } else {
                totalScorePlayer2 = sum;
            }
     }
    if(player1Chances == 0 || player2Chances == 0 ){
        weHaveAWinner();
    }
} // end onClickRollTheDice
    public void onClickStop(View view){
        counter = 3;
        bestScore.setText("Stopped!");
        if(player1Chances == 0 || player2Chances == 0 ){
            weHaveAWinner();
        }
    }



public static int[] giveNumbers(){
    int[] dices = new int[3];
    for(int i = 0; i<3; i++) {
        int random = (int) (Math.random() * 6 + 1);
        dices[i] = random;
    }
    return dices;
}
public static int countResult(int[] arr){
        int sum = 0;

        for(int i = 0; i<3; i++) {
            int value =arr[i] ;
            switch (value) {
                case 1:
                    sum += 100;
                    break;
                case 6:
                    sum += 60;
                    break;
                default:
                    sum += value;
                    break;
            }
        }
      return sum;
}
public void changeColors(){
    if(player1){
        playerName1.setBackgroundColor(Color.GREEN);
        playerName2.setBackgroundColor(Color.TRANSPARENT);

    } else {
        playerName2.setBackgroundColor(Color.GREEN);
        playerName1.setBackgroundColor(Color.TRANSPARENT);
    }
}
public void  weHaveAWinner(){
    bestScore.setText("We have a winner");
    rollDices.setVisibility(View.GONE);
}
}

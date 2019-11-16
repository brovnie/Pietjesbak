package be.brovnie.pietjesbak;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;
import android.graphics.Color;

public class Game extends Activity {

    public static final String PLAYER1_NAME = "player1";
    public static final String PLAYER2_NAME = "player2";

    boolean player1;
    boolean player2;
    int counter = 0;
    int max = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        TextView playerName1 = (TextView)findViewById(R.id.player1);
        String playerText1 = intent.getStringExtra(PLAYER1_NAME);
        playerName1.setText(playerText1);
        TextView playerName2 = (TextView)findViewById(R.id.player2);
        String playerText2 = intent.getStringExtra(PLAYER2_NAME);

        playerName2.setText(playerText2);
        player1 = true;
        player2 = false;
    }
    // method ocClick

public void onClickRollTheDice(View view){
    TextView playerName1 = (TextView)findViewById(R.id.player1);
    TextView playerName2 = (TextView)findViewById(R.id.player2);
    if(counter == 3){
        counter = 0;

        if(player1 == true){
            player1 = false;
            player2 = true;
        } else {
            player1 = true;
            player2 = false;
        }
    }
       if(player1){
            playerName1.setBackgroundColor(Color.GREEN);
            playerName2.setBackgroundColor(Color.TRANSPARENT);
        } else {
            playerName2.setBackgroundColor(Color.GREEN);
            playerName1.setBackgroundColor(Color.TRANSPARENT);
        }
    TextView results = (TextView)findViewById(R.id.roll_results);
    int[] arrNumbers = giveNumbers();
    String n = arrNumbers[0] + " " + arrNumbers[1] + " " + arrNumbers[2];
    results.setText(n);
    int sum = countResult(arrNumbers);
    TextView resultsSum = (TextView)findViewById(R.id.roll_sum);
    resultsSum.setText(Integer.toString(sum));
    int best = findBestResult(sum);
    TextView bestScore = (TextView)findViewById(R.id.roll_bestScore);
    bestScore.setText("Best score so far " + Integer.toString(best));

    counter++;
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

public int findBestResult(int a){
        if(counter == 0){
            max = 0;
        }
        if( a > max ){
            max = a;
        }
        return max;
}
}

package be.brovnie.pietjesbak;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;

public class Game extends Activity {

    public static final String PLAYER1_NAME = "player1";
    public static final String PLAYER2_NAME = "player2";

    int sum;
    int bestResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();

        String playerText1 = intent.getStringExtra(PLAYER1_NAME);
        TextView playerName1 = (TextView)findViewById(R.id.player1);
        playerName1.setText(playerText1);

        String playerText2 = intent.getStringExtra(PLAYER2_NAME);
        TextView playerName2 = (TextView)findViewById(R.id.player2);
        playerName2.setText(playerText2);
    }
    // method ocClick
public void onClickRollTheDice(View view){
    TextView results = (TextView)findViewById(R.id.roll_results);
    int[] arrNumbers = giveNumbers();
    String n = arrNumbers[0] + " " + arrNumbers[1] + " " + arrNumbers[2];
    results.setText(n);
    countResult(arrNumbers);
    //TextView resultsSum = (TextView)findViewById(R.id.roll_sum);
    //resultsSum.setText(sum);*/
}

public static int[] giveNumbers(){
    int[] dices = new int[3];
    for(int i = 0; i<3; i++) {
        int random = (int) (Math.random() * 6 + 1);
        dices[i] = random;
    }
    return dices;
}
public void countResult(int[] arr){
      sum = arr[0] + arr[1] + arr[2];
}
}

package be.brovnie.pietjesbak;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;

public class Game extends Activity {

    public static final String PLAYER1_NAME = "message";
    public static final String PLAYER2_NAME = "message";

    int[] dices = new int[3];

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
    results.setText(giveNumbers());


}
public String giveNumbers(){
        String numbers;
        for(int i = 0; i<3; i++){
            int random = (int)(Math.random()*6+1);
        dices[i] = random;
        }
        numbers = dices[0] + " " + dices[1] + " " + dices[2];
        return numbers;
}
}

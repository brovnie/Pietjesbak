package be.brovnie.pietjesbak;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    private EditText playerView1;
    private EditText playerView2;
    private String playername1;
    private String playername2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

public void onClickPlayersNames (View view){
        //Get data from Edit text field
         playerView1 = (EditText)findViewById(R.id.player1_name);
         playerView2 = (EditText)findViewById(R.id.player2_name);

        //Convert to string
    playername1 = playerView1.getText().toString();
    playername2 = playerView2.getText().toString();
    //Connect activities
    Intent intent = new Intent(this, Game.class);
    if(playername1.length() != 0 || playername2.length() != 0 ) {
        //Using constants so both activities use the same name
        intent.putExtra(Game.PLAYER1_NAME,playername1);
        intent.putExtra(Game.PLAYER2_NAME,playername2);
        //Start with the intent
        startActivity(intent);
    } else {
        Toast.makeText(MainActivity.this, "Please enter player name", Toast.LENGTH_LONG).show();
    }

}
} //end
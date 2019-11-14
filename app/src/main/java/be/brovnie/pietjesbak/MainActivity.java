package be.brovnie.pietjesbak;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

public void onClickPlayersNames (View view){
        //Get data from Edit text field
    EditText playerView1 = (EditText)findViewById(R.id.player1_name);
    EditText playerView2 = (EditText)findViewById(R.id.player2_name);
        //Convert to string
    String playername1 = playerView1.getText().toString();
    String playername2 = playerView2.getText().toString();

    //Connect activities
    Intent intent = new Intent(this, Game.class);
    //Using constants so both activities use the same name
    intent.putExtra(Game.PLAYER1_NAME,playername1);
    intent.putExtra(Game.PLAYER2_NAME,playername2);
    //Start with the intent
    startActivity(intent);
}
} //end
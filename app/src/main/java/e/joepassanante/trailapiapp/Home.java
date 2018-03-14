package e.joepassanante.trailapiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

/**
 * Author: Joe Passanante
 * Date: 3/5/18
 * Description of Class: Handles button clicks for front page.
 */
public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    //call when search is pressed
    public void onClickSearch(View view) {
        //create a new search menu screen for the user
        Intent search = new Intent(this, e.joepassanante.trailapiapp.Search.class);
        startActivity(search);
    }

    //call when about is clicked
    public void onClickAbout(View view) {
        //create a new intent that brings user to about screen.
        Intent about = new Intent(this, e.joepassanante.trailapiapp.AboutActivity.class);
        startActivity(about);
    }
}

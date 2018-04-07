package e.joepassanante.trailapiapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SearchHolderActivity extends AppCompatActivity
        implements SearchFragment.SearchFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_holder);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        Log.i("Orientation","We have detected a change!");
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_search_holder);
    }
    @Override
    public void callBackMethod(String JSONSRESULT) {
        Intent intent = new Intent(this, ResultsHolder.class);
        intent.putExtra(ResultsHolder.RESULT_KEY, JSONSRESULT);
        startActivity(intent);
    }
}

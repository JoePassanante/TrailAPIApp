package e.joepassanante.trailapiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchHolderActivity extends AppCompatActivity
        implements SearchFragment.SearchFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_holder);
    }

    @Override
    public void callBackMethod(String JSONSRESULT) {
        Intent intent = new Intent(this, e.joepassanante.trailapiapp.SiteResults.class);
        intent.putExtra(SiteResults.RESULT_KEY, JSONSRESULT);
        startActivity(intent);
    }
}

package e.joepassanante.trailapiapp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Author: Joe Passanante
 * Date: 3/5/18
 * Description of Class: Handles button clicks for front page.
 */
public class HomeHolder extends AppCompatActivity
        implements HomeFragment.HomeFragmentListener, SearchFragment.SearchFragmentListener,ResultFragment.ResultFragmentItemListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void searchButtonListener() {
        View fc = findViewById(R.id.searchFragmentContainer);
        if (fc != null) {
            //that means we are in the big mode!
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            SearchFragment search = new SearchFragment();
            ft.replace(R.id.searchFragmentContainer,search);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
            Log.i("File","We loaded the larger file!");
        }else{
            Log.i("File","We loaded the smaller file!");
            //We are in regular mode :D
            Intent intent = new Intent(this, e.joepassanante.trailapiapp.SearchHolderActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void favoriteButtonListener() {

    }

    @Override
    public void aboutButtonListener() {

    }

    @Override
    public void callBackMethod(String JSONSRESULT) {
        //if this is being called, that means the results were passed back to us!
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ResultFragment fragment = new ResultFragment();
        fragment.setJSONString(JSONSRESULT);
        ft.replace(R.id.ResultFragmentContainer, fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        Log.i("File","We loaded the larger file!");
    }

    @Override
    public void ResultFragmentItemClicked(int position) {
        Site s = ResultFragment.sites.get(position);
        Intent intent = new Intent(this, SiteViewHolder.class);
        intent.putExtra(SiteViewHolder.SiteIDTag,position);
        startActivity(intent);
    }
}

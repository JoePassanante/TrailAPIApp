package e.joepassanante.trailapiapp;

import android.app.*;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ShareActionProvider;

/**
 * Author: Joe Passanante
 * Date: 3/5/18
 * Description of Class: Handles button clicks for front page.
 */
public class HomeHolder extends AppCompatActivity
        implements SearchFragment.SearchFragmentListener,ResultFragment.ResultFragmentItemListener, SiteViewFragment.SiteViewDataHolder, RequestHandler.RequestHandlerCallback {

    private Site mySite;
    private ShareActionProvider shareActionProvider;
    private RequestHandler h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Menu Loading","Started");
        setTheme(SettingsHolder.CURRENT_THEME);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("Menu Loading","Loading");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onAttachedToWindow() {
        Log.i("Menu Loading","Attached");
        super.openOptionsMenu();
    };
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent settings = new Intent(this, e.joepassanante.trailapiapp.SettingsActivity.class);
                startActivity(settings);
                return true;
            case R.id.qusearch:
                //if we are already looking... no need to restart the search
                if(h!=null && h.getStatus().equals(AsyncTask.Status.RUNNING.toString()))
                    return true;

                h = new RequestHandler(
                        "United States",
                        "Connecticut",
                        "Hamden",
                        200,
                        this
                );
                h.execute();
                return true;
            case R.id.gotofavorites:
                Intent fav = new Intent(this, e.joepassanante.trailapiapp.FavoritesHolder.class);
                startActivity(fav);
                return true;
            case R.id.share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "TrailAPI Invite");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Search for great outdoor activities in your area using the TrailAPI App!");
                startActivity(Intent.createChooser(sharingIntent, "Share invite via"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public Site getSite() {
        return this.mySite;
    }

    @Override
    public void ResultFragmentItemClicked(int position, Site site) {
        this.mySite = site;
        if(site==null)
            return;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        SiteViewFragment fragment = new SiteViewFragment();
        ft.replace(R.id.SiteFragmentContainer, fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
    //This is for SearchFragment
    @Override
    public void searchCallBackMethod(String JSONSRESULT) {
        View fc = findViewById(R.id.ResultFragmentContainer);
        if (fc != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ResultFragment fragment = new ResultFragment();
            fragment.setJSONString(JSONSRESULT);
            ft.replace(R.id.ResultFragmentContainer, fragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }else{
            //We are in small mode :/
            Intent intent = new Intent(this, e.joepassanante.trailapiapp.ResultsHolder.class);
            intent.putExtra(ResultsHolder.RESULT_KEY,JSONSRESULT);
            startActivity(intent);
        }
    }
    //This is for RequestHandler
    @Override
    public void Callback(String JSONResult) {
        this.searchCallBackMethod(JSONResult);
    }
}

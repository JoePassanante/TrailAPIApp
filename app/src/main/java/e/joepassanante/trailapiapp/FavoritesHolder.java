package e.joepassanante.trailapiapp;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.sql.SQLException;
import java.util.ArrayList;

public class FavoritesHolder extends AppCompatActivity
        implements FavoritesHomeMenuFragment.FavoritesHomeListener, RequestHandler.RequestHandlerCallback, SiteViewFragment.SiteViewDataHolder, FavoritesListFragment.FavoritesListener,ResultFragment.ResultFragmentItemListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsHolder.CURRENT_THEME);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_main);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        FavoritesHomeMenuFragment home = new FavoritesHomeMenuFragment();
        ft.replace(R.id.menufrag, home);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

    }

    @Override
    public void onRemoveClick(Search search) {
        Log.i("onRemoveClick","Removing and Spawning new Fragment");
        try{
            SearchDatabaseSource db = new SearchDatabaseSource(this);
            db.open();
            db.removeSearch(search.getCountry(),search.getState(),search.getCity(),search.getName());
        }catch(SQLException e){
            e.printStackTrace();
        }
        //now we need to refresh the fragment
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        FavoritesHomeMenuFragment home = new FavoritesHomeMenuFragment();
        ft.replace(R.id.menufrag, home);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    @Override
    public void onResultClick(Search search) {
        RequestHandler h = new RequestHandler(search.getCountry(), search.getState(), search.getCity(), 200, this);
        h.execute();
    }

    @Override
    public void Callback(String JSONResult) {

        View fc = findViewById(R.id.FavResultFragmentContainer);
        if (fc != null) {
            //that means we are in the big mode!
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ResultFragment result = new ResultFragment();
            result.setJSONString(JSONResult);
            ft.replace(R.id.FavResultFragmentContainer, result);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
            Log.i("File", "We loaded the larger file!");
        } else {
            //We are in small mode :/
            Intent intent = new Intent(this, e.joepassanante.trailapiapp.ResultsHolder.class);
            intent.putExtra(ResultsHolder.RESULT_KEY, JSONResult);
            startActivity(intent);

        }
    }
    private Site s;
    @Override
    public Site getSite() {
        return s;
    }

    @Override
    public void FavoriteItemClicked(int position, final Search search) {
        Log.i("Clicked", String.valueOf(position));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("What would you like to do?")
                .setCancelable(false)
                .setPositiveButton("Go to Results", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onResultClick(search);
                    }
                })
                .setNegativeButton("Remove", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onRemoveClick(search);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void ResultFragmentItemClicked(int position, Site site) {
        this.s = site;
        View fc = findViewById(R.id.FavResultSiteFragmentContainer);
        if(fc!=null){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            SiteViewFragment siteview = new SiteViewFragment();
            ft.replace(R.id.FavResultSiteFragmentContainer, siteview);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
        }else{
            //This is such a terrible work around. Needs to be cleaned up in the future, if a clear solution is available.
            Intent intent = new Intent(this, e.joepassanante.trailapiapp.SiteViewHolder.class);
            ResultFragment.sites = new ArrayList<Site>();
            ResultFragment.sites.add(this.s);
            intent.putExtra(SiteViewHolder.SiteIDTag,0);
            startActivity(intent);
        }

    }
}


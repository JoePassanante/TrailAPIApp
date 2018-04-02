package e.joepassanante.trailapiapp;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FavoritesHolder extends AppCompatActivity
        implements FavoritesHomeMenuFragment.FavoritesHomeListener, RequestHandler.RequestHandlerCallback, SiteViewFragment.SiteViewDataHolder, FavoritesListFragment.FavoritesListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_main);
    }

    @Override
    public void onRemoveClick(Search search) {

    }

    @Override
    public void onResultClick(Search search) {
        RequestHandler h = new RequestHandler(search.getCountry(), search.getState(), search.getCity(), 200, this);
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
            Log.i("File", "We loaded the smaller file!");

        }
    }

    @Override
    public Site getSite() {
        return null;
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
}


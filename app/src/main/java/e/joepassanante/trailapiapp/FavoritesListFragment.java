package e.joepassanante.trailapiapp;


import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesListFragment extends ListFragment {
    static interface FavoritesListener{
        public void FavoriteItemClicked(int position, Search search);
    }
    private ArrayList<Search> searches;
    private FavoritesListener listener;

    public FavoritesListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final Context context = inflater.getContext();
        //pull searches from DB
        ArrayList<Search> s = new ArrayList<Search>();
        try{
            SearchDatabaseSource db = new SearchDatabaseSource(getActivity());
            db.open();
            s = db.getAllSearchs();
            this.searches = s;
            db.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

        // Inflate the layout for this fragment
        Log.i("Searches Count",""+this.searches.size());
        ArrayAdapter<Search> adapt = new ArrayAdapter<Search>(context, R.layout.support_simple_spinner_dropdown_item, this.searches);;
        setListAdapter(adapt);
        Log.i("Adapter Count",""+adapt.isEmpty());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(android.app.Activity activity){
        super.onAttach(activity);
        this.listener = (FavoritesListener) activity;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(listener!=null){
            listener.FavoriteItemClicked(position, this.searches.get(position));
        }

    }
}

package e.joepassanante.trailapiapp;


import android.app.*;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesHomeMenuFragment extends Fragment implements FavoritesListFragment.FavoritesListener {
    static interface FavoritesHomeListener{
        void onResultClick(Search search);
        void onRemoveClick(Search search);
    }
    private FavoritesHomeListener listener;
    public FavoritesHomeMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (FavoritesHomeListener)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("SpawnList","Spawned.");
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        FavoritesListFragment results = new FavoritesListFragment();
        ft.replace(R.id.SearchesListContainer,results);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

        return inflater.inflate(R.layout.fragment_favorites_home, container, false);
    }

    @Override
    public void FavoriteItemClicked(int position, final Search search) {
        Log.i("Clicked",String.valueOf(position));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("What would you like to do?")
                .setCancelable(false)
                .setPositiveButton("Go to Results", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onResultClick(search);
                    }
                })
                .setNegativeButton("Remove", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onRemoveClick(search);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}

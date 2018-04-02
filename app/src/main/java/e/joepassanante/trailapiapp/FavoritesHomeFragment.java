package e.joepassanante.trailapiapp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesHomeFragment extends Fragment implements FavoritesFragment.FavoritesListener {


    public FavoritesHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_home, container, false);
    }

    @Override
    public void FavoriteItemClicked(int position) {

    }
}

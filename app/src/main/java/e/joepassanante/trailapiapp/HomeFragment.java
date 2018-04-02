package e.joepassanante.trailapiapp;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {
    static interface HomeFragmentListener{
        void searchButtonListener();
        void favoriteButtonListener();
        void aboutButtonListener();
    }
    private HomeFragmentListener listener;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (HomeFragmentListener)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_home, container, false);

        ((Button)layout.findViewById(R.id.search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener==null)
                    return;
                listener.searchButtonListener();
            }
        });
        ((Button)layout.findViewById(R.id.about)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener==null)
                    return;
                listener.aboutButtonListener();
            }
        });
        ((Button)layout.findViewById(R.id.favButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener==null)
                    return;
                listener.favoriteButtonListener();
            }
        });

        // Inflate the layout for this fragment
        return layout;
    }

}

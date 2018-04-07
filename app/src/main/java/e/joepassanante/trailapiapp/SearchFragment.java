package e.joepassanante.trailapiapp;

import android.app.*;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import java.sql.SQLException;


public class SearchFragment extends Fragment
        implements RequestHandler.RequestHandlerCallback {
    //implement requestHandler callback, so we can get results from the Request Handler pulling data.
    static interface SearchFragmentListener{
        void callBackMethod(String JSONSRESULT);
    }
    private SearchFragmentListener listener;
    private RequestHandler h;
    private String json;
    private boolean addFavorites = false;
    private ProgressBar progressBar;
    private SearchDatabaseSource db;

    public SearchFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (SearchFragmentListener) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.db = new SearchDatabaseSource(getActivity());
        final View layout = inflater.inflate(R.layout.fragment_search,container,false);

        this.progressBar = (ProgressBar)layout.findViewById(R.id.progressbar);

        //This is apart of the fragments core-functionality and therefore does not need to be passed to listener.
        ((Button)layout.findViewById(R.id.search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //WARNING: View view is null because of this not being an activity!!!!!!
                onClickSearchButton(layout); // We need to pass layout - as this is the actual view that we get from the inflater!
            }
        });
        ((Switch)layout.findViewById(R.id.FavoritesSwitch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                addFavorites = b;
                if(b==true){
                    layout.findViewById(R.id.favnameinput).setVisibility(View.VISIBLE);
                    layout.findViewById(R.id.favnamelabel).setVisibility(View.VISIBLE);
                }else{
                    layout.findViewById(R.id.favnameinput).setVisibility(View.INVISIBLE);
                    layout.findViewById(R.id.favnamelabel).setVisibility(View.INVISIBLE);
                }
            }
        });
        return layout;
    }
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
    }

    //we dont need to pass this off to the listener because this is the fragments core-functionality
    public void onClickSearchButton(View view) {
        //create a RequestHandler, which will get the data from the Server and send it to the Results.java activity.
        if (h != null && h.getStatus().equals(AsyncTask.Status.RUNNING)) //if a search is already in progress, we shouldn't trigger another one.
            return;
        if(this.progressBar!=null){
            progressBar.setVisibility(View.VISIBLE);
        }
        if(this.addFavorites){
            try {
                db.open();
                db.addSearch(
                        ((TextView) view.findViewById(R.id.country)).getText().toString(),
                        ((TextView) view.findViewById(R.id.state)).getText().toString(),
                        ((TextView) view.findViewById(R.id.city)).getText().toString(),
                        ((TextView) view.findViewById(R.id.favnameinput)).getText().toString()
                );
                db.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        h = new RequestHandler(
                ((TextView) view.findViewById(R.id.country)).getText().toString(),
                ((TextView) view.findViewById(R.id.state)).getText().toString(),
                ((TextView) view.findViewById(R.id.city)).getText().toString(),
                200,
                this);
        h.execute();
    }



    @Override
    public void Callback(String JSONResult) {
        if(this.progressBar!=null){
            progressBar.setVisibility(View.INVISIBLE);
        }
        if(listener!=null) { //if we have a listener it means that there is a god like creator that wants us to do whatever they want!
            listener.callBackMethod(JSONResult);
        }
    }

}

package e.joepassanante.trailapiapp;

import android.app.*;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class SearchFragment extends Fragment
        implements RequestHandler.RequestHandlerCallback {
    static interface SearchFragmentListener{
        void callBackMethod(String JSONSRESULT);
    }
    private SearchFragmentListener listener;
    private RequestHandler h;
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

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
        final View layout = inflater.inflate(R.layout.fragment_search,container,false);
        //This is apart of the fragments core-functionality and therefore does not need to be passed to listener.
        ((Button)layout.findViewById(R.id.search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //WARNING: View view is null because of this not being an activity!!!!!!
                onClickSearchButton(layout); // We need to pass layout - as this is the actual view that we get from the inflater!
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
        if(listener!=null) { //if we have a listener it means that there is a god like creator that wants us to do whatever they want!
            listener.callBackMethod(JSONResult);
        }
    }

}

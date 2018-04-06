package e.joepassanante.trailapiapp;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SiteViewFragment extends Fragment {
    static interface SiteViewDataHolder{
        public Site getSite();
    }
    private Site mySite;
    private View layout;
    private TextView sitename, country, state, city, id, directions, activities;

    public SiteViewFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(android.app.Activity activity) {
        super.onAttach(activity);
        this.mySite = ((SiteViewDataHolder)activity).getSite();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Step","One");
        this.setRetainInstance(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        View layout = getView();
        this.sitename = (TextView) layout.findViewById(R.id.sitename);
        this.country = (TextView) layout.findViewById(R.id.country);
        this.state = (TextView) layout.findViewById(R.id.state);
        this.city = (TextView) layout.findViewById(R.id.city);
        this.id = (TextView) layout.findViewById(R.id.idNum);
        this.directions = (TextView) layout.findViewById(R.id.directions);
        this.activities = (TextView) layout.findViewById(R.id.activies);

        //set the text views their corresponding data representation.
        if(this.mySite==null)
            return;

        this.sitename.setText(mySite.getName());
        this.country.setText("Country: " + mySite.getCountry());
        this.state.setText("State: " + mySite.getState());
        this.city.setText("City: " + mySite.getCity());
        this.id.setText("ID: " + String.valueOf(mySite.getId()));
        this.directions.setText("Directions: " + mySite.getDirections());
        this.activities.setText(this.formatStringFromActivities(mySite.getActivities()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.layout = inflater.inflate(R.layout.fragment_site_view,container,false);
        //get all the textviews we will be editing
        Log.i("Step","Two");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_site_view, container, false);
    }
    //we are not allowed to put a listview inside of a scroll view, as scrolling objects cannot be nested inside of eachother.
    //because of this, we create a formatted string and apply it to a TextView inside of ScrollView. "Fake" List of activities.
    private String formatStringFromActivities(ArrayList<Activity> list) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("List of Activities to Do!\n");
        buffer.append("-------------------------------------\n");
        for (Activity a : list) {
            buffer.append("Activity: " + a.getName() + "\n");
            buffer.append("Type: " + a.getType() + " | " + a.getLength() + " Miles!" + "\n");
            buffer.append("ID: " + a.getId() + "\n");
            buffer.append("Description: " + a.getDescription() + "\n");
            buffer.append("-------------------------------------\n");
        }
        return buffer.toString();
    }
}

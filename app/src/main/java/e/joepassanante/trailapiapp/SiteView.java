package e.joepassanante.trailapiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Author: Joe Passanante
 * Date: 3/5/18
 * Description of Class: Gets the data from the Site Class, and updates the layout to display that data to the user.
 */
public class SiteView extends AppCompatActivity {
    public static final String SITE_VIEW_KEY = "PassedSiteData";
    private Site mySite;
    private TextView sitename,country,state,city,id,directions,activities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_view);
        mySite = Results.sites.get(((int)getIntent().getExtras().getInt(this.SITE_VIEW_KEY)));

        //get all the textviews we will be editing
        this.sitename = (TextView)findViewById(R.id.sitename);
        this.country = (TextView)findViewById(R.id.country);
        this.state = (TextView)findViewById(R.id.state);
        this.city = (TextView)findViewById(R.id.city);
        this.id = (TextView)findViewById(R.id.idNum);
        this.directions = (TextView)findViewById(R.id.directions);
        this.activities = (TextView)findViewById(R.id.activies);

        //set the text views their corresponding data representation.
        this.sitename.setText(mySite.getName());
        this.country.setText("Country: " + mySite.getCountry());
        this.state.setText("State: " +mySite.getState());
        this.city.setText("City: " +mySite.getCity());
        this.id.setText("ID: " +String.valueOf(mySite.getId()));
        this.directions.setText("Directions: " +mySite.getDirections());
        this.activities.setText(this.formatStringFromActivities(mySite.getActivities()));
    }
    //we are not allowed to put a listview inside of a scroll view, as scrolling objects cannot be nested inside of eachother.
    //because of this, we create a formatted string and apply it to a TextView inside of ScrollView. "Fake" List of activities.
    private String formatStringFromActivities(ArrayList<Activity>list ){
        StringBuffer buffer = new StringBuffer();
        buffer.append("List of Activities to Do!\n");
        buffer.append("-------------------------------------\n");
        for(Activity a : list){
            buffer.append("Activity: " + a.getName() + "\n");
            buffer.append("Type: " + a.getType() + " | "+a.getLength()+" Miles!" + "\n");
            buffer.append("ID: " + a.getId() + "\n");
            buffer.append("Description: " + a.getDescription() + "\n");
            buffer.append("-------------------------------------\n");
        }
        return buffer.toString();
    }
}

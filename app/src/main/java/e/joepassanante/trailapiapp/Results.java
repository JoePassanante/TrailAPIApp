package e.joepassanante.trailapiapp;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Author: Joe Passanante
 * Date: 3/5/18
 * Description of Class: Results is created after RequestHandler.java gets a string of data. This class than coverts it to JSON, parses the JSON, and
 * eventually turns it into an array of various sites given the search parameters.
 * The class then displays the results in a list.
 */
public class Results extends ListActivity{
    static public final String RESULT_KEY = "SEARCH_RESULTS";
    private JSONObject json;
    protected static ArrayList<Site>sites = new ArrayList<Site>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //now that we have all this data... we need to figure out how to display it.
        try {
            this.json = new JSONObject((String) getIntent().getStringExtra(Results.RESULT_KEY));
            this.sites = getSitesFromJSON(this.json.getJSONArray("places"));
        }catch(org.json.JSONException e){
            e.printStackTrace();
        }
        //now we need to upload list to user
        ListView list = getListView();
        ArrayAdapter<Site>adapt = new ArrayAdapter<Site>(this, R.layout.support_simple_spinner_dropdown_item,this.sites);
        list.setAdapter(adapt);
        //instead of displaying an empty screen, prompt user if there are no results to go back and search for something else
        if(adapt.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Oops! Looks like there are no outdoor activities for the parameters you've entered!")
                    .setPositiveButton("Return to Search Screen", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id) {
                            finish(); //ends the current activity
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent siteDetails = new Intent(this, e.joepassanante.trailapiapp.SiteView.class);
        siteDetails.putExtra(SiteView.SITE_VIEW_KEY,(int)id); //give the siteView the site array id(not site id), so that it call pull its data.
        startActivity(siteDetails);
    }

    /**
     * @param places JSONArray of places to be parsed.
     * @return returns ArrayList of sites.
     * @throws org.json.JSONException
     */
    private ArrayList<Site> getSitesFromJSON(JSONArray places)throws org.json.JSONException{
        ArrayList<Site>returnSites = new ArrayList<Site>();
        final int length = places.length();
        //loop through the JSON Array of all the sites.
        for(int i=0;i<length;i++){
            returnSites.add(this.getSite(places.getJSONObject(i)));
        }
        return returnSites;
    }

    /**
     * @param j - Requried a JSONObject of a site, this data will be parsed. Please see the example:
     *          https://trailapi-trailapi.p.mashape.com/?q[state_cont]=Rhode+Island&radius=20&mashape-key=lDpTXjgi8zmsh6XeS27UrbpugjJ3p1jKo0Njsn1bsU9Va5P4T4
     *          for more details on how the JSON is structured.
     * @return - returns the Site Object with all the corresponding properties filled out.
     * @throws org.json.JSONException
     */
    private Site getSite(JSONObject j)throws org.json.JSONException{
        Site mySite = new Site();
        //get data from JSON and set properties of Site accordingly
        mySite.setCountry(j.getString("country"));
        mySite.setState(j.getString("state"));
        mySite.setCity(j.getString("city"));
        mySite.setName(j.getString("name"));
        mySite.setDirections(j.getString("directions"));
        mySite.setId(j.getInt("unique_id"));

        mySite.setActivities(getActivitiesFromJSONArray(j.getJSONArray("activities"))); //get all the listed activities at a site.

        return mySite;
    }

    /**
     * @param j Accepts JSONArray which is the list of actitivies under a given site.
     * @return returns an ArrayList<Activity> from JSON data.
     * @throws org.json.JSONException
     */
    private ArrayList<Activity> getActivitiesFromJSONArray(JSONArray j)throws org.json.JSONException{
        ArrayList<Activity> activities = new ArrayList<Activity>();
        final int length = j.length();
        //loop through the JSONArray
        for(int i=0;i<length;i++){
            Activity a = new Activity();
            //assign data to properties of the activity accordingly.
            a.setName(j.getJSONObject(i).getString("name"));
            a.setId(j.getJSONObject(i).getString("unique_id"));
            a.setType(j.getJSONObject(i).getString("activity_type_name"));
            a.setUrl(j.getJSONObject(i).getString("url"));
            a.setDescription(j.getJSONObject(i).getString("description"));
            a.setLength(j.getJSONObject(i).getString("length"));
            a.setRating(j.getJSONObject(i).getInt("rating"));
            activities.add(a);
        }
        return activities;
    }
}


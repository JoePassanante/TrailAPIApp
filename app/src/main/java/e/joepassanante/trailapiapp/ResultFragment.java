package e.joepassanante.trailapiapp;


import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends ListFragment {
    static interface ResultFragmentItemListener{
        void ResultFragmentItemClicked(int position, Site site);
    }
    private ResultFragmentItemListener listener;
    private String JSONResult = "";
    private JSONObject json;
    protected static  ArrayList<Site> sites;

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //now that we have all this data... we need to figure out how to display it.
        try {
            this.json = new JSONObject(this.JSONResult);
            this.sites = getSitesFromJSON(this.json.getJSONArray("places"));
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
        //now we need to upload list to user

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final Context context = inflater.getContext();

        // Inflate the layout for this fragment
        ArrayAdapter<Site> adapt = new ArrayAdapter<Site>(context, R.layout.support_simple_spinner_dropdown_item, this.sites);
        setListAdapter(adapt);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(android.app.Activity activity){
        super.onAttach(activity);
        this.listener = (ResultFragmentItemListener) activity;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(listener!=null){
            listener.ResultFragmentItemClicked(position, sites.get(position));
        }

    }

    public void setJSONString(String JSONString) {
        this.JSONResult = JSONString;
    }


    /**
     * @param places JSONArray of places to be parsed.
     * @return returns ArrayList of sites.
     * @throws org.json.JSONException
     */
    private ArrayList<Site> getSitesFromJSON(JSONArray places) throws org.json.JSONException {
        ArrayList<Site> returnSites = new ArrayList<Site>();
        final int length = places.length();
        //loop through the JSON Array of all the sites.
        for (int i = 0; i < length; i++) {
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
    private Site getSite(JSONObject j) throws org.json.JSONException {
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
    private ArrayList<Activity> getActivitiesFromJSONArray(JSONArray j) throws org.json.JSONException {
        ArrayList<Activity> activities = new ArrayList<Activity>();
        final int length = j.length();
        //loop through the JSONArray
        for (int i = 0; i < length; i++) {
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

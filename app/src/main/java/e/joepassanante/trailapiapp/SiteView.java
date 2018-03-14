package e.joepassanante.trailapiapp;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.Serializable;

public class SiteView extends AppCompatActivity {
    public static String SiteIDTag = "SITEIDTAG";
    public static Site mySite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_view);
        Log.i("Index Clicked", String.valueOf(getIntent().getExtras().getInt(SiteView.SiteIDTag)));

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        this.mySite = ResultFragment.sites.get(getIntent().getExtras().getInt(this.SiteIDTag));
        SiteViewFragment fragment = new SiteViewFragment();
        fragment.setSite();
        ft.replace(R.id.siteView_contain, fragment);
        //ft.addToBackStack(null); // We actually don't want this fragment on the backStack... when the user clicks back the entire Fragment should be destroyed.
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}

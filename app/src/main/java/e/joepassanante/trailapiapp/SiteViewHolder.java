package e.joepassanante.trailapiapp;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SiteViewHolder extends AppCompatActivity implements SiteViewFragment.SiteViewDataHolder {
    public static String SiteIDTag = "SiteIdentifyingTag";
    public static Site mySite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsHolder.CURRENT_THEME);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_view);
        Log.i("Index Clicked", String.valueOf(getIntent().getExtras().getInt(SiteViewHolder.SiteIDTag)));

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        this.mySite = ResultFragment.sites.get(getIntent().getExtras().getInt(this.SiteIDTag));
        SiteViewFragment fragment = new SiteViewFragment();
        ft.replace(R.id.siteView_contain, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    @Override
    public Site getSite() {
        return mySite;
    }
}

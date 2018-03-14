package e.joepassanante.trailapiapp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SiteResults extends AppCompatActivity implements ResultFragment.ResultFragmentItemListener{
    static public final String RESULT_KEY = "SEARCH_RESULTS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_results);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ResultFragment fragment = new ResultFragment();
        fragment.setJSONString(getIntent().getExtras().getString(this.RESULT_KEY));
        ft.replace(R.id.result_contain, fragment);
        //ft.addToBackStack(null); // We actually don't want this fragment on the backStack... when the user clicks back the entire Fragment should be destroyed along with the activity.
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    @Override
    public void ResultFragmentItemClicked(int position) {
        Site s = ResultFragment.sites.get(position);
        Intent intent = new Intent(this, SiteView.class);
        intent.putExtra(SiteView.SiteIDTag,position);
        startActivity(intent);
    }
}

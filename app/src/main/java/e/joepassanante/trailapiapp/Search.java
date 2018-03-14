package e.joepassanante.trailapiapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Author: Joe Passanante
 * Date: 3/5/18
 * Description of Class: Handles the creation of the request handler.
 */
public class Search extends AppCompatActivity
        implements RequestHandler.RequestHandlerCallback {
    private RequestHandler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void onClickSearchButton(View view) {
        //create a RequestHandler, which will get the data from the Server and send it to the Results.java activity.
        if (h != null && h.getStatus().equals(AsyncTask.Status.RUNNING)) //if a search is already in progress, we shouldn't trigger another one.
            return;
        h = new RequestHandler(
                ((TextView) findViewById(R.id.country)).getText().toString(),
                ((TextView) findViewById(R.id.state)).getText().toString(),
                ((TextView) findViewById(R.id.city)).getText().toString(),
                200,
                this);
        h.execute();
    }

    @Override
    public void Callback(String JSONResult) {
        Intent intent = new Intent(this, e.joepassanante.trailapiapp.SiteResults.class);
        intent.putExtra(SiteResults.RESULT_KEY, JSONResult);
        startActivity(intent);
    }
}

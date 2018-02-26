package ht.mbds.samuel.tweet.activities;

import android.app.ProgressDialog;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import ht.mbds.saul.tweet.R;
import ht.mbds.saul.tweet.adapters.TweetAdapter;
import ht.mbds.saul.tweet.models.Tweet;
import ht.mbds.saul.tweet.serviceApi.TwitterApp;
import ht.mbds.saul.tweet.serviceApi.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {
    TwitterClient client;
    TweetAdapter adapter;
    ArrayList<Tweet> tweets;
    RecyclerView rvTweet;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        client= TwitterApp.getRestClient();
        rvTweet=(RecyclerView)findViewById(R.id.rcTweet);
        tweets=new ArrayList<>();
        adapter=new TweetAdapter(this,tweets);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        rvTweet.setLayoutManager(layoutManager);

        rvTweet.addItemDecoration( new DividerItemDecoration(this, layoutManager.getOrientation()));
        adapter.notifyDataSetChanged();
        rvTweet.setAdapter(adapter);




        populate();
    }
    public  void populate(){
        pd = new ProgressDialog(this);
        pd.setMessage("Please wait.");
        pd.setCancelable(false);
        pd.show();
        client.getTimeLime(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("DEBUG", response.toString());
                pd.dismiss();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d("DEBUG", response.toString());

                pd.dismiss();
                for (int i=0;i<response.length();i++){
                    try{
                        Tweet tw=Tweet.fromJSON(response.getJSONObject(i));
                        tweets.add(tw);
                        adapter.notifyItemInserted(tweets.size()-1);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                pd.dismiss();
                Log.d("DEBUG", responseString);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
                pd.dismiss();
                // super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}

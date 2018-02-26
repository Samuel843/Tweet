package ht.mbds.samuel.tweet.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Tweet {
    private String body;
    private long uid;
    private User user;
    private String createdAt;
    private String imTweet;



    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getImTweet() {
        return imTweet;
    }

    public void setImTweet(String imTweet) {
        this.imTweet = imTweet;
    }
    public Tweet(){

    }
    public Tweet(JSONObject jsonObject) throws JSONException {
        this.uid=jsonObject.getLong("id");
        this.body=jsonObject.getString("text");
        this.createdAt=jsonObject.getString("created_at");
        this.user=User.fromJsonObject(jsonObject.getJSONObject("user"));
        try{
            JSONArray _multimedia=jsonObject.getJSONObject("entities").getJSONArray("media");
            this.imTweet= _multimedia.getJSONObject(0).getString("media_url");
        }catch (JSONException ex){
            ex.printStackTrace();
        }

    }

    public static Tweet fromJSON(JSONObject json) throws JSONException{
        Tweet tweet=new Tweet();
        try{
            JSONArray _multimedia=json.getJSONObject("entities").getJSONArray("media");
            tweet.imTweet= _multimedia.getJSONObject(0).getString("media_url");
        }catch (JSONException ex){
            ex.printStackTrace();
        }



        tweet.uid=json.getLong("id");
        tweet.body=json.getString("text");
        tweet.createdAt=json.getString("created_at");
        tweet.user=User.fromJsonObject(json.getJSONObject("user"));
        return tweet;
    }

    public static ArrayList<Tweet> fromJSONArray(JSONArray array){
        ArrayList<Tweet> results=new ArrayList<>();

        for(int i=0;i<array.length();i++){
            try{
                results.add(new Tweet(array.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }


        }
        return results;
    }
}

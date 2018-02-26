package ht.mbds.samuel.tweet.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ht.mbds.saul.tweet.R;


public class ViewHolderImage extends RecyclerView.ViewHolder {

    TextView tvBody;
    TextView tvTitle;
    ImageView imProfile;
    ImageView imTweet;

    public TextView getTvBody() {
        return tvBody;
    }

    public void setTvBody(TextView tvBody) {
        this.tvBody = tvBody;
    }

    public ImageView getImProfile() {
        return imProfile;
    }

    public void setImProfile(ImageView imProfile) {
        this.imProfile = imProfile;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public ViewHolderImage(View view) {
        super(view);
        imProfile=(ImageView) view.findViewById(R.id.imProfile);
        imTweet=(ImageView) view.findViewById(R.id.imTweet);
        tvTitle=(TextView) view.findViewById(R.id.tvTitle);
        tvBody=(TextView) view.findViewById(R.id.tvBody);

    }
}

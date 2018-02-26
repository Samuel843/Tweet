package ht.mbds.samuel.tweet.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.squareup.picasso.Picasso;
//import com.squareup.picasso.Picasso;

import java.util.List;

import ht.mbds.saul.tweet.R;
import ht.mbds.saul.tweet.models.Tweet;
//import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

//import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
//import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


public class TweetAdapter  extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<Tweet> tweets;
    Context context;
    ImageView imProfile;
    private final int WITH_IMAGE = 0, WITHOUT_IMAGE = 1;

    public TweetAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }
    public TweetAdapter( List<Tweet> tweets) {

        this.tweets = tweets;
    }
    public Context getContext() {
        return context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case WITH_IMAGE:
                View v1 = inflater.inflate(R.layout.item_tweet_image, parent, false);
                viewHolder = new ViewHolderImage(v1);
                break;
            case WITHOUT_IMAGE:
                View v2 = inflater.inflate(R.layout.item_tweet_no_image, parent, false);
                viewHolder = new ViewHolderNoImage(v2);
                break;
            default:
                View v3 = inflater.inflate(R.layout.item_tweet_no_image, parent, false);
                viewHolder = new ViewHolderNoImage(v3);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);

        switch (holder.getItemViewType()) {
            case WITH_IMAGE:
                ViewHolderImage viewHolderImage = (ViewHolderImage) holder;
                viewHolderImage.getTvTitle().setText(tweet.getUser().getName());
                viewHolderImage.getTvBody().setText(tweet.getBody());
                String _thumball=tweet.getUser().getProfileImageUrl();
                 imProfile=viewHolderImage.imProfile;
                ImageView imTweet=viewHolderImage.imTweet;
                if(!TextUtils.isEmpty(_thumball)){
                    //    Picasso.with(getContext()).load(_thumball).into(imageView);
                    //  imageView.getLayoutParams().height = 150;

               //     Glide.with(context).load(_thumball).apply(RequestOptions.circleCropTransform()).into(imProfile);

               /*     Glide.with(context).load(_thumball).asBitmap().centerCrop().into(new BitmapImageViewTarget(imProfile) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            imProfile.setImageDrawable(circularBitmapDrawable);
                        }
                    });

*/
                 /*     Glide.with(context).load(_thumball)
                            .apply(bitmapTransform(new BlurTransformation(25)))
                            .into(imProfile);*/
                  Picasso.with(getContext())
                            .load(_thumball)
                            .transform(new RoundedCornersTransformation(5, 5))
                            .into(imProfile, new com.squareup.picasso.Callback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onError() {

                                }
                            });


                }
                String _thumballmedia=tweet.getImTweet();

                if(!TextUtils.isEmpty(_thumballmedia)){
                    //    Picasso.with(getContext()).load(_thumball).into(imageView);
                    //  imageView.getLayoutParams().height = 150;
                /*      Glide.with(context).load(_thumballmedia)
                            .apply(bitmapTransform(new BlurTransformation(25)))
                            .into(imTweet);

*/
                  Picasso.with(getContext())
                            .load(_thumballmedia)
                          .transform(new RoundedCornersTransformation(15, 15))
                            .into(imTweet, new com.squareup.picasso.Callback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onError() {

                                }
                            });


                }


                break;
            case WITHOUT_IMAGE:
                ViewHolderNoImage viewHolderNoImage = (ViewHolderNoImage) holder;
                viewHolderNoImage.getTvTitle().setText(tweet.getUser().getName());
                viewHolderNoImage.getTvBody().setText(tweet.getBody());
                ImageView noImProfile=viewHolderNoImage.imProfile;
                String _thumballProf=tweet.getUser().getProfileImageUrl();
                if(!TextUtils.isEmpty(_thumballProf)){
                    //    Picasso.with(getContext()).load(_thumball).into(imageView);
                    //  imageView.getLayoutParams().height = 150;


                 /*    Glide.with(context).load(_thumballProf)
                            .apply(bitmapTransform(new BlurTransformation(25)))
                            .into(imProfile);*/
                   Picasso.with(getContext())
                            .load(_thumballProf)
                       .transform(new RoundedCornersTransformation(5, 5))
                            .into(noImProfile, new com.squareup.picasso.Callback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onError() {

                                }
                            });


                }
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
      //  return super.getItemViewType(position);
        if (getTweet(position).getImTweet() != null)
            return WITH_IMAGE;
        else
            return WITHOUT_IMAGE;
    }
    @Override
    public int getItemCount() {
        return tweets.size();
    }
    public Tweet getTweet(int position) {
        return tweets.get(position);
    }
}

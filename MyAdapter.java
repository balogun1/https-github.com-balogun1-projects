package com.example.balogunal_amin.rrsfeeds;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by balogunal-amin on 2016-04-02.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
 ArrayList<FeedItem>feeditems;
    Context context;
    public MyAdapter(Context context, ArrayList<FeedItem>feeditems){
        this.feeditems=feeditems;
        this.context=context;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custum_row_news_item,parent,false);
        MyViewHolder holder  = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        YoYo.with(Techniques.BounceIn).duration(500).playOn(holder.cardView);
        final FeedItem current = feeditems.get(position);
        holder.Title.setText(current.getTitle());
        holder.Description.setText(current.getDescription());
        holder.Date.setText(current.getPubDate());
        Picasso.with(context).load(current.getThumbnailUrl()).into(holder.Thumbnail);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,NewDetails.class);
                intent.putExtra("Link", current.getLink());
                context.startActivity(intent);
            }
        });


    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {

        return feeditems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Title, Description, Date;
        ImageView Thumbnail;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            Title = (TextView)itemView.findViewById(R.id.title_view);
            Description = (TextView)itemView.findViewById(R.id.description_text);
            Date = (TextView)itemView.findViewById(R.id.date_view);
            Thumbnail = (ImageView)itemView.findViewById(R.id.thumb_img);
            cardView = (CardView)itemView.findViewById(R.id.card_view);

        }
    }
}

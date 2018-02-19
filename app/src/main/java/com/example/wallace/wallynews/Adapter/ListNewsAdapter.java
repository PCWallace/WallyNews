package com.example.wallace.wallynews.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wallace.wallynews.Common.ISO8601Parse;
import com.example.wallace.wallynews.Interface.ItemClickListener;
import com.example.wallace.wallynews.Model.Article;
import com.example.wallace.wallynews.R;
import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by wallace on 3/2/18.
 */


class ListNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    ItemClickListener itemClickListner;

    TextView article_title;
    RelativeTimeTextView article_time;
    CircleImageView article_image;

    public ListNewsViewHolder(View itemView) {
        super(itemView);
        article_image=(CircleImageView) itemView.findViewById(R.id.article_image);
        article_title=(TextView) itemView.findViewById(R.id.article_title);
        article_time=(RelativeTimeTextView) itemView.findViewById(R.id.article_time);

        itemView.setOnClickListener(this);
    }


    public void setItemClickListner(ItemClickListener itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    @Override
    public void onClick(View view) {

        itemClickListner.onClick(view,getAdapterPosition(),false);

    }
}


public class ListNewsAdapter extends RecyclerView.Adapter<ListNewsViewHolder> {
    private List<Article> articleList;
    private Context context;

    public ListNewsAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;
    }

    @Override
    public ListNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.news_layout,parent,false);
        return new ListNewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListNewsViewHolder holder, int position) {

        Picasso.with(context)
                .load(articleList.get(position).getUrlToimage())
                .into(holder.article_image);

        if (articleList.get(position).getTitle().length() > 65)
        {
            holder.article_title.setText(articleList.get(position).getTitle().substring(0,65)+"....");
        }
        else
            holder.article_title.setText(articleList.get(position).getTitle());

        Date date=null;
        try {
            date= (Date) ISO8601Parse.parse(articleList.get(position).getPublishedAt());
        }catch (ParseException ex)
        {
            ex.printStackTrace();
        }

        holder.article_time.setReferenceTime(date.getTime());

        //set Event Click
        holder.setItemClickListner(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                //code late

            }
        });

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}

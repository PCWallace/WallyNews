package com.example.wallace.wallynews;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.wallace.wallynews.Adapter.ListNewsAdapter;
import com.example.wallace.wallynews.Common.Common;
import com.example.wallace.wallynews.Interface.NewsService;
import com.example.wallace.wallynews.Model.Article;
import com.example.wallace.wallynews.Model.News;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.diagonallayout.DiagonalLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListNews extends AppCompatActivity {

    KenBurnsView kbv;
    DiagonalLayout diagonalLayout;
    SpotsDialog dialog;
    NewsService mService;
    TextView top_author,top_title;
    SwipeRefreshLayout swipeRefreshLayout;
    String source="",sortBy="",webHotURL;
    ListNewsAdapter adapter;
    RecyclerView lstNews;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);

        //service
        mService= Common.getNewsService();
        dialog =new SpotsDialog(this);

        //view
        swipeRefreshLayout =(SwipeRefreshLayout)findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNews(source,true);
            }
        });


        diagonalLayout=(DiagonalLayout)findViewById(R.id.diagonalLayout);
        diagonalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent details=new Intent(getBaseContext(),DetailArticles.class);
                details.putExtra("webUrl",webHotURL);
                startActivity(details);
            }
        });
        kbv=(KenBurnsView)findViewById(R.id.top_image);
        top_author=(TextView)findViewById(R.id.top_Author);
        top_title=(TextView)findViewById(R.id.top_Title);

        lstNews=(RecyclerView)findViewById(R.id.ListNews);
        lstNews.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        lstNews.setLayoutManager(layoutManager);


        //Intent
        if(getIntent()!=null)
        {
            source=getIntent().getStringExtra("source");
            sortBy=getIntent().getStringExtra("sortBy");
            if(!source.isEmpty() && !sortBy.isEmpty())
            {
                loadNews(source,false);
            }
        }
    }

    private void loadNews(String source, boolean isRefreshed) {
        if(!isRefreshed)
        {
            dialog.show();
            mService.getNewestArticles(Common.getAPIUrl(source,sortBy,Common.API_key))
                    .enqueue(new Callback<News>() {
                        @Override
                        public void onResponse(Call<News> call, Response<News> response) {
                            dialog.dismiss();
                            //Get first article
                            Picasso.with(getBaseContext())
                                    .load(response.body().getArticles().get(0).getUrlToimage())
                            .into(kbv);

                            top_title.setText(response.body().getArticles().get(0).getTitle());
                            top_author.setText(response.body().getArticles().get(0).getAuthor());
                            webHotURL=response.body().getArticles().get(0).getUrl();

                            //load remaining articles
                            List<Article> removeFirstItem=response.body().getArticles();
                            //as we had loaded the first article first
                            //so we need to remove it
                            removeFirstItem.remove(0);
                            adapter=new ListNewsAdapter(removeFirstItem,getBaseContext());
                            adapter.notifyDataSetChanged();
                            lstNews.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<News> call, Throwable t) {

                        }
                    });
        }
        else
        {
            dialog.show();
            mService.getNewestArticles(Common.getAPIUrl(source,sortBy,Common.API_key))
                    .enqueue(new Callback<News>()
                    {
                        @Override
                        public void onResponse(Call<News> call, Response<News> response) {
                            dialog.dismiss();
                            //Get first article
                            Picasso.with(getBaseContext())
                                    .load(response.body().getArticles().get(0).getUrlToimage())
                                    .into(kbv);

                            top_title.setText(response.body().getArticles().get(0).getTitle());
                            top_author.setText(response.body().getArticles().get(0).getAuthor());
                            webHotURL=response.body().getArticles().get(0).getUrl();

                            //load remaining articles
                            List<Article> removeFirstItem=response.body().getArticles();
                            //as we had loaded the first article first
                            //so we need to remove it
                            removeFirstItem.remove(0);
                            adapter=new ListNewsAdapter(removeFirstItem,getBaseContext());
                            adapter.notifyDataSetChanged();
                            lstNews.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<News> call, Throwable t) {

                        }
                    });
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}

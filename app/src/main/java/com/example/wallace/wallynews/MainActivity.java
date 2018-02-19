package com.example.wallace.wallynews;

import android.app.AlertDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wallace.wallynews.Adapter.ListSourceAdapter;
import com.example.wallace.wallynews.Common.Common;
import com.example.wallace.wallynews.Interface.NewsService;
import com.example.wallace.wallynews.Model.WebSite;
import com.google.gson.Gson;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView listWebsites;
    RecyclerView.LayoutManager layoutManager;
    NewsService mService;
    ListSourceAdapter adapter;
    AlertDialog dialog;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for cache
        Paper.init(this);
        mService= Common.getNewsService();
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadWebsiteSource(true);
            }
        });


        listWebsites=(RecyclerView)findViewById(R.id.list_source);
        listWebsites.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this);
        listWebsites.setLayoutManager(layoutManager);
        dialog=new SpotsDialog(this);
        loadWebsiteSource(false);
    }

    private void loadWebsiteSource(boolean isRefreshed) {

        if(!isRefreshed)
        {
            String cache=Paper.book().read("cache");
            if(cache!=null && cache.isEmpty())
            {
                WebSite website=new Gson().fromJson(cache,WebSite.class);
                adapter =new ListSourceAdapter(getBaseContext(),website);
                adapter.notifyDataSetChanged();
                listWebsites.setAdapter(adapter);
            }
            else
            {
                dialog.show();

                //fetching new data

                mService.getSources().enqueue(new Callback<WebSite>() {
                    @Override
                    public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                        adapter =new ListSourceAdapter(getBaseContext(),response.body());
                        adapter.notifyDataSetChanged();
                        listWebsites.setAdapter(adapter);
                        //saving to cache for further use

                        Paper.book().write("cache",new Gson().toJson(response.body()));
                    }

                    @Override
                    public void onFailure(Call<WebSite> call, Throwable t) {

                    }
                });
            }
        }
        // when it is refreshed
        else
        {
            dialog.show();

            //fetching new data

            mService.getSources().enqueue(new Callback<WebSite>() {
                @Override
                public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                    adapter =new ListSourceAdapter(getBaseContext(),response.body());
                    adapter.notifyDataSetChanged();
                    listWebsites.setAdapter(adapter);
                    //saving to cache for further use

                    Paper.book().write("cache",new Gson().toJson(response.body()));

                    //dismissing the refreshing
                    swipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<WebSite> call, Throwable t) {

                }
            });
        }
    }


}

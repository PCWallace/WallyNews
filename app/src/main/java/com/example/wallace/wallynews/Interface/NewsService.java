package com.example.wallace.wallynews.Interface;

import com.example.wallace.wallynews.Model.News;
import com.example.wallace.wallynews.Model.WebSite;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by wallace on 28/1/18.
 */

public interface NewsService {
    @GET("v1/sources?language=en")
    Call<WebSite> getSources();

    @GET
    Call<News> getNewestArticles(@Url String url);
}

package com.example.wallace.wallynews.Common;

import com.example.wallace.wallynews.Interface.IconBetterIdeaService;
import com.example.wallace.wallynews.Interface.NewsService;
import com.example.wallace.wallynews.Remote.IconeBetterIdeaClient;
import com.example.wallace.wallynews.Remote.RetrofitClient;

/**
 * Created by wallace on 28/1/18.
 */

public class Common {

    private static final String BASE_URL="https://newsapi.org/";
    public static final String API_key="cd36f16ce224465384d0df7eb44d9fcd";

    public static NewsService getNewsService()
    {
        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }

    public static IconBetterIdeaService getIconService()
    {
        return IconeBetterIdeaClient.getClient().create(IconBetterIdeaService.class);
    }

    //https://newsapi.org/v1/articles?source=the-verge&apiKey=cd36f16ce224465384d0df7eb44d9fcd
    public static String getAPIUrl(String source,String sortBy,String apiKEY)
    {
        StringBuilder apiUrl=new StringBuilder("https://newsapi.org/v1/articles?source=");

        return apiUrl.append(source)
                .append("&sortBy=")
                .append(sortBy)
                .append("&apiKey=")
                .append(apiKEY)
                .toString();
    }


}

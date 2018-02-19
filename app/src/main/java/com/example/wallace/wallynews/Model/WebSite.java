package com.example.wallace.wallynews.Model;

import java.util.List;

/**
 * Created by wallace on 27/1/18.
 */

public class WebSite {
    private String status;
    private List<Source> sources;

    public WebSite()
    {

    }

    public WebSite(String status,List<Source> sources)
    {
        this.status=status;
        this.sources=sources;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status=status;
    }

    public List<Source> getSources()
    {
        return sources;
    }
}

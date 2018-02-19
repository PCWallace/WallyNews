package com.example.wallace.wallynews.Model;

import java.util.List;

/**
 * Created by wallace on 27/1/18.
 */
class UrlsToLogos
{
    private String small,meduim,large;

    public String getSmall()
    {
        return small;
    }
    public void setSmall(String small)
    {
        this.small=small;
    }

    public String getMeduim()
    {
        return meduim;
    }
    public void setMeduim(String meduim)
    {
        this.meduim=meduim;
    }

    public String getLarge()
    {
        return large;
    }
    public void setLarge(String large)
    {
        this.large=large;
    }
}
public class Source {

    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String country;
    private String language;
    private UrlsToLogos urlsToLogos;
    private List<String> sortByAvailable;

    public Source()
    {

    }

    public Source(String id,String name,String description,String url,String category,String country,String language,UrlsToLogos urlsToLogos,List<String> sortByAvailable)
    {
        this.id=id;
        this.name=name;
        this.description=description;
        this.url=url;
        this.category=category;
        this.country=country;
        this.urlsToLogos=urlsToLogos;
        this.sortByAvailable=sortByAvailable;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public UrlsToLogos getUrlsToLogos() {
        return urlsToLogos;
    }

    public void setUrlsToLogos(UrlsToLogos urlsToLogos) {
        this.urlsToLogos = urlsToLogos;
    }

    public List<String> getSortByAvailable() {
        return sortByAvailable;
    }

    public void setSortByAvailable(List<String> sortByAvailable) {
        this.sortByAvailable = sortByAvailable;
    }
}

package com.example.dylan.premierleague;

/**
 * Created by dylan on 20/11/2017.
 */

public class Stadiums {

    private String teamName;
    private String imgURL;
    private String teamWebsite;

    public Stadiums(String teamName, String imgURL, String teamWebsite) {
        this.teamName = teamName;
        this.imgURL = imgURL;
        this.teamWebsite = teamWebsite;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTeamWebsite() {
        return teamWebsite;
    }

    public void setTeamWebsite(String teamWebsite) {
        this.teamWebsite = teamWebsite;
    }
}

package com.example.dylan.premierleague;

public class Fixture
{
    public int fixtureid;
    public int hometeamID;
    public int awayteamID;
    private String HomeTeam;
    private String AwayTeam;
    private String Fixture;
    private String Date;
    private String Time;

    public Fixture(int fixtureid, int hometeamID, int awayteamID) {
        this.fixtureid = fixtureid;
        this.hometeamID = hometeamID;
        this.awayteamID = awayteamID;
    }

    public Fixture() {

    }

    public int getFixtureid() {
        return fixtureid;
    }

    public void setFixtureid(int fixtureid) {
        this.fixtureid = fixtureid;
    }

    public int getHometeamID() {
        return hometeamID;
    }

    public void setHometeamID(int hometeamID) {
        this.hometeamID = hometeamID;
    }

    public int getAwayteamID() {
        return awayteamID;
    }

    public void setAwayteamID(int awayteamID) {
        this.awayteamID = awayteamID;
    }

    public String getHomeTeam() {
        return HomeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        HomeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return AwayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        AwayTeam = awayTeam;
    }

    public String getFixture() {
        return Fixture;
    }

    public void setFixture(String fixture) {
        Fixture = fixture;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}

package com.example.dylan.premierleague;

public class Team{

    public  int teamID;
    public String teamName;
    public String teamManager;
    public String teamStadium;
    public String teamLogo;
    private Integer Games = 0;
    private Integer Losses = 0;
    private Integer Draws = 0;
    private Integer goalsFor = 0;
    private Integer goalsAgainst = 0;
    private Integer goalDifference = 0;
    private Integer pointTotal = 0;

    //Constructors
    public Team(int teamID, String teamName, String teamManager, String teamStadium, String teamLogo) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.teamManager = teamManager;
        this.teamStadium = teamStadium;
        this.teamLogo = teamLogo;
    }

    public Team() {

    }

    //Getters and Setters

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamManager() {
        return teamManager;
    }

    public void setTeamManager(String teamManager) {
        this.teamManager = teamManager;
    }

    public String getTeamStadium() {
        return teamStadium;
    }

    public void setTeamStadium(String teamStadium) {
        this.teamStadium = teamStadium;
    }

    public Integer getGames() {
        return Games;
    }

    public void setGames(Integer games) {
        Games = games;
    }

    public Integer getLosses() {
        return Losses;
    }

    public void setLosses(Integer losses) {
        Losses = losses;
    }

    public Integer getDraws() {
        return Draws;
    }

    public void setDraws(Integer draws) {
        Draws = draws;
    }

    public Integer getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(Integer goalsFor) {
        this.goalsFor = goalsFor;
    }

    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public Integer getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(Integer goalDifference) {
        this.goalDifference = goalDifference;
    }

    public Integer getPointTotal() {
        return pointTotal;
    }

    public void setPointTotal(Integer pointTotal) {
        this.pointTotal += pointTotal;
    }

}

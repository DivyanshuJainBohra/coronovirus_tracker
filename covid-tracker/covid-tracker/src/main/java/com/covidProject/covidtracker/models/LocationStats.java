package com.covidProject.covidtracker.models;

public class LocationStats {

    private String state;
    private String recovered;
    private String dead;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDead() {
        return dead;
    }

    public void setDead(String dead) {
        this.dead = dead;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", recovered='" + recovered + '\'' +
                ", dead='" + dead + '\'' +
                '}';
    }
}

package e.joepassanante.trailapiapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Author: Joe Passanante
 * Date: 3/5/18
 * Description of Class: Site class that acts as a holder of various properties.
 */

public class Site {
    private String name,description,city,state,country,directions;
    private int length,id;
    private ArrayList<Activity> activities = new ArrayList<Activity>();


    //setters and getters.
    public String getName() {
        return name;
    }
    public String toString(){
        return this.getName();
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }
}

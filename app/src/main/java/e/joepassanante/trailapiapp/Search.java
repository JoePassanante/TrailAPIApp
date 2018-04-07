package e.joepassanante.trailapiapp;

/**
 * Created by Joe Passanante on 4/2/2018.
 */

public class Search {
    private String name;
    private String country;
    private String state;
    private String city;
    public Search(){
        //empty constructor option
    }
    public Search(String country, String state, String city){
        this.country = country;
        this.state = state;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String toString(){
        return this.getName();
    }
}

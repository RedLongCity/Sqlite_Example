package com.smitsworks.redlo.sqlite_example.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by redlongcity on 09.07.2017.
 */

@DatabaseTable(tableName = "Cities_has_countries")
public class CitiesHasCountries implements Serializable{

    public static final String CITIES_ID_FIELD_NAME = "city_id";
    public static final String COUNTRIES_ID_FIELD_NAME = "coutry_id";

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(foreign = true, columnName = CITIES_ID_FIELD_NAME)
    private City city;

    @DatabaseField(foreign = true, columnName = COUNTRIES_ID_FIELD_NAME)
    private Country country;

    public CitiesHasCountries() {
    }

    public CitiesHasCountries(City city, Country country) {
        this.city = city;
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CitiesHasCountries that = (CitiesHasCountries) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        return country != null ? country.equals(that.country) : that.country == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CitiesHasCountries{" +
                "id=" + id +
                ", city=" + city +
                ", country=" + country +
                '}';
    }
}

package com.smitsworks.redlo.sqlite_example.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by redlongcity on 19.06.2017.
 */
@DatabaseTable(tableName = "Cities")
public class City implements Serializable {

    private static final long serialVersionUID = -7874823823497497357L;

    public static final String COLUMN_ID = "idCities";
    public static final String TITLE = "title";


    @DatabaseField(generatedId = true, columnName = COLUMN_ID)
    private Integer id;

    @DatabaseField(columnName = TITLE, width = 64)
    private String title;

    @ForeignCollectionField
    private Collection<Country> countries;

    public City() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Country> getCountries() {
        return countries;
    }

    public void setCountries(Collection<Country> countries) {
        this.countries = countries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (!id.equals(city.id)) return false;
        return title.equals(city.title);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}

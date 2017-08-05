package com.smitsworks.redlo.sqlite_example.model;

import java.io.Serializable;
import java.util.Collection;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
/**
 * Created by redlongcity on 19.06.2017.
 */

@DatabaseTable(tableName = "Countries")
public class Country implements Serializable,DataPoint {

    private static final long serialVersionUID = -6582623980712135028L;

    public static final String COLUMN_ID = "idCountries";
    public static final String TITLE = "title";

    @DatabaseField(generatedId = true, columnName = COLUMN_ID)
    private Integer id;

    @DatabaseField(columnName = TITLE, width = 64)
    private String title;

    public Country() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (!id.equals(country.id)) return false;
        return title.equals(country.title);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}

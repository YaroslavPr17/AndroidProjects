package com.example.bookadviser.Entities;

import androidx.room.Entity;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.Year;

@Entity(primaryKeys = {"name", "surname"})
public class Author implements Serializable {
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String year;
    private String nativeCountry;
    private String language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNativeCountry() {
        return nativeCountry;
    }

    public void setNativeCountry(String nativeCountry) {
        this.nativeCountry = nativeCountry;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

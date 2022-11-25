package com.example.bookadviser.Entities;

import androidx.room.Entity;

import org.jetbrains.annotations.NotNull;

@Entity(primaryKeys = {"name"})
public class Publisher {
    @NotNull
    private String name;
    private String year;
    private String directorName;
    private boolean isOnlineShoppingAvailable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public boolean isOnlineShoppingAvailable() {
        return isOnlineShoppingAvailable;
    }

    public void setOnlineShoppingAvailable(boolean onlineShoppingAvailable) {
        isOnlineShoppingAvailable = onlineShoppingAvailable;
    }
}

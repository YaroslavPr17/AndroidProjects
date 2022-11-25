package com.example.bookadviser.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(primaryKeys = {"title", "publicationYear"})
public class Book implements Serializable {
    @NotNull
    private String title;
    @NotNull
    private String publicationYear;
    private String publisher;
    private String creationYear;
    private String authorName;
    private String authorSurname;
    private String language;


    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(@NonNull String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(String creationYear) {
        this.creationYear = creationYear;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

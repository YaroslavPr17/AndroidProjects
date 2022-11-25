package com.example.bookadviser;

import android.content.Context;

import com.example.bookadviser.Entities.Author;
import com.example.bookadviser.Entities.Book;
import com.example.bookadviser.Entities.Publisher;

public class Initializer {

    public static void initializeAuthors(Context context) {
        AppDatabase db = SafeFunctions.createDatabase(context, false);

        Author a = new Author();
        a.setName("Stephen");
        a.setSurname("King");
        a.setNativeCountry("The USA");
        a.setLanguage("English");
        a.setYear("1952");
        SafeFunctions.insertAuthor(context, a, db, false);

        a = new Author();
        a.setName("Fyodor");
        a.setSurname("Dostoevskiy");
        a.setNativeCountry("The Russian Empire");
        a.setLanguage("Russian");
        a.setYear("1821");
        SafeFunctions.insertAuthor(context, a, db, false);

        a = new Author();
        a.setName("Konstantin");
        a.setSurname("Paustovsky");
        a.setNativeCountry("The Russian Empire");
        a.setLanguage("Russian");
        a.setYear("1892");
        SafeFunctions.insertAuthor(context, a, db, false);

        a = new Author();
        a.setName("Nikolay");
        a.setSurname("Gogol");
        a.setNativeCountry("The Russian Empire");
        a.setLanguage("Russian");
        a.setYear("1809");
        SafeFunctions.insertAuthor(context, a, db, false);

        a = new Author();
        a.setName("Ayn");
        a.setSurname("Rand");
        a.setNativeCountry("The Russian Empire");
        a.setLanguage("English");
        a.setYear("1905");
        SafeFunctions.insertAuthor(context, a, db, false);

        a = new Author();
        a.setName("David");
        a.setSurname("Nocholls");
        a.setNativeCountry("The UK");
        a.setLanguage("English");
        a.setYear("1966");
        SafeFunctions.insertAuthor(context, a, db, false);

        a = new Author();
        a.setName("Leo");
        a.setSurname("Tolstoy");
        a.setNativeCountry("The Russian Empire");
        a.setLanguage("Russian");
        a.setYear("1828");
        SafeFunctions.insertAuthor(context, a, db, false);

        a = new Author();
        a.setName("Ray");
        a.setSurname("Bradbury");
        a.setNativeCountry("The USA");
        a.setLanguage("English");
        a.setYear("1920");
        SafeFunctions.insertAuthor(context, a, db, false);

        a = new Author();
        a.setName("Oscar");
        a.setSurname("Wilde");
        a.setNativeCountry("Great Britain");
        a.setLanguage("English");
        a.setYear("1854");
        SafeFunctions.insertAuthor(context, a, db, false);

        a = new Author();
        a.setName("Homer");
        a.setSurname("");
        a.setNativeCountry("Greece");
        a.setLanguage("Greek");
        a.setYear("-800");
        SafeFunctions.insertAuthor(context, a, db, false);

        a = new Author();
        a.setName("Archimedes");
        a.setSurname("");
        a.setNativeCountry("Syracuse");
        a.setLanguage("Greek");
        a.setYear("-273");
        SafeFunctions.insertAuthor(context, a, db, false);
    }


    public static void initializePublishers(Context context) {

        AppDatabase db = SafeFunctions.createDatabase(context, false);

        Publisher p;

        p = new Publisher();
        p.setName("AST");
        p.setYear("1990");
        p.setDirectorName("Oleg Novikov");
        p.setOnlineShoppingAvailable(true);
        SafeFunctions.insertPublisher(context, p, db, false);

        p = new Publisher();
        p.setName("MIF");
        p.setYear("2005");
        p.setDirectorName("Igor Mann");
        p.setOnlineShoppingAvailable(true);
        SafeFunctions.insertPublisher(context, p, db, false);

        p = new Publisher();
        p.setName("Pearson");
        p.setYear("1844");
        p.setDirectorName("Andy Bird");
        p.setOnlineShoppingAvailable(false);
        SafeFunctions.insertPublisher(context, p, db, false);

        p = new Publisher();
        p.setName("Longman");
        p.setYear("1724");
        p.setDirectorName("Thomas Longman");
        p.setOnlineShoppingAvailable(false);
        SafeFunctions.insertPublisher(context, p, db, false);

        p = new Publisher();
        p.setName("Eksmo");
        p.setYear("1991");
        p.setDirectorName("Oleg Novikov");
        p.setOnlineShoppingAvailable(false);
        SafeFunctions.insertPublisher(context, p, db, false);

        p = new Publisher();
        p.setName("Detskaya literatura");
        p.setYear("1933");
        p.setDirectorName("Oleg Morozov");
        p.setOnlineShoppingAvailable(false);
        SafeFunctions.insertPublisher(context, p, db, false);
    }

    public static void initializeBooks(Context context) {

        AppDatabase db = SafeFunctions.createDatabase(context, false);

        Book b;

        b = new Book();
        b.setTitle("It");
        b.setPublicationYear("2020");
        b.setPublisher("AST");
        b.setCreationYear("1986");
        b.setAuthorName("Stephen");
        b.setAuthorSurname("King");
        b.setLanguage("Russian");
        SafeFunctions.insertBook(context, b, db, false);

        b = new Book();
        b.setTitle("It");
        b.setPublicationYear("2022");
        b.setPublisher("MIF");
        b.setCreationYear("1986");
        b.setAuthorName("Stephen");
        b.setAuthorSurname("King");
        b.setLanguage("English");
        SafeFunctions.insertBook(context, b, db, false);

        b = new Book();
        b.setTitle("It");
        b.setPublicationYear("2000");
        b.setPublisher("Pearson");
        b.setCreationYear("1986");
        b.setAuthorName("Stephen");
        b.setAuthorSurname("King");
        b.setLanguage("English");
        SafeFunctions.insertBook(context, b, db, false);

        b = new Book();
        b.setTitle("Atlas Shrugged");
        b.setPublicationYear("2014");
        b.setPublisher("Pearson");
        b.setCreationYear("1957");
        b.setAuthorName("Ayn");
        b.setAuthorSurname("Rand");
        b.setLanguage("English");
        SafeFunctions.insertBook(context, b, db, false);

        b = new Book();
        b.setTitle("Atlas Shrugged");
        b.setPublicationYear("1990");
        b.setPublisher("Longman");
        b.setCreationYear("1957");
        b.setAuthorName("Ayn");
        b.setAuthorSurname("Rand");
        b.setLanguage("English");
        SafeFunctions.insertBook(context, b, db, false);

        b = new Book();
        b.setTitle("Atlas Shrugged");
        b.setPublicationYear("2022");
        b.setPublisher("AST");
        b.setCreationYear("1957");
        b.setAuthorName("Ayn");
        b.setAuthorSurname("Rand");
        b.setLanguage("Russian");
        SafeFunctions.insertBook(context, b, db, false);

        b = new Book();
        b.setTitle("Atlas Shrugged");
        b.setPublicationYear("2001");
        b.setPublisher("AST");
        b.setCreationYear("1957");
        b.setAuthorName("Ayn");
        b.setAuthorSurname("Rand");
        b.setLanguage("English");
        SafeFunctions.insertBook(context, b, db, false);

        b = new Book();
        b.setTitle("Ideal");
        b.setPublicationYear("2021");
        b.setPublisher("MIF");
        b.setCreationYear("2015");
        b.setAuthorName("Ayn");
        b.setAuthorSurname("Rand");
        b.setLanguage("Russian");
        SafeFunctions.insertBook(context, b, db, false);

        b = new Book();
        b.setTitle("The Fountainhead");
        b.setPublicationYear("2001");
        b.setPublisher("AST");
        b.setCreationYear("1943");
        b.setAuthorName("Ayn");
        b.setAuthorSurname("Rand");
        b.setLanguage("Russian");
        SafeFunctions.insertBook(context, b, db, false);

        b = new Book();
        b.setTitle("Anthem");
        b.setPublicationYear("1984");
        b.setPublisher("MIF");
        b.setCreationYear("1946");
        b.setAuthorName("Ayn");
        b.setAuthorSurname("Rand");
        b.setLanguage("English");
        SafeFunctions.insertBook(context, b, db, false);

        b = new Book();
        b.setTitle("Iliad");
        b.setPublicationYear("1500");
        b.setPublisher("Eksmo");
        b.setCreationYear("-800");
        b.setAuthorName("Homer");
        b.setAuthorSurname("");
        b.setLanguage("English");
        SafeFunctions.insertBook(context, b, db, false);

        b = new Book();
        b.setTitle("Psammites");
        b.setPublicationYear("2001");
        b.setPublisher("Eksmo");
        b.setCreationYear("-230");
        b.setAuthorName("Archimedes");
        b.setAuthorSurname("");
        b.setLanguage("Russian");
        SafeFunctions.insertBook(context, b, db, false);


        b = new Book();
        b.setTitle("Blistayuschiye oblaka");
        b.setPublicationYear("2001");
        b.setPublisher("Eksmo");
        b.setCreationYear("1929");
        b.setAuthorName("Konstantin");
        b.setAuthorSurname("Paustovsky");
        b.setLanguage("Russian");
        SafeFunctions.insertBook(context, b, db, false);
    }

}

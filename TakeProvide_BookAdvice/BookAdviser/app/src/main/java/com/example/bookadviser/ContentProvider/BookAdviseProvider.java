package com.example.bookadviser.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteProgram;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQueryBuilder;

import com.example.bookadviser.AppDatabase;
import com.example.bookadviser.DatabaseClient;
import com.example.bookadviser.Entities.Author;
import com.example.bookadviser.Entities.Book;
import com.example.bookadviser.SafeFunctions;

import java.util.Arrays;
import java.util.List;

public class BookAdviseProvider extends ContentProvider {

    static final String AUTHORITY = "com.example.bookadviser.provider";

    private static final String BookAdvicePatternURI = "content://com.example.bookadviser.provider";
    public static final Uri CONTENT_URI = Uri.parse(BookAdvicePatternURI);

    private AppDatabase db;

    @Override
    public boolean onCreate() {
         db = DatabaseClient.getInstance(getContext())
                 .getAppDatabase();
        return true;
    }

    // Create constants to differentiate between different URI requests
    static final int AUTHOR_ALL_ROWS = 1;
    static final int PUBLISHER_ALL_ROWS = 2;
    static final int BOOK_ALL_ROWS = 3;
    static final int AUTHOR_SINGLE_ROW = 4;
    static final int PUBLISHER_SINGLE_ROW = 5;
    static final int BOOK_SINGLE_ROW = 6;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "Author", AUTHOR_ALL_ROWS);
        uriMatcher.addURI(AUTHORITY, "Publisher", PUBLISHER_ALL_ROWS);
        uriMatcher.addURI(AUTHORITY, "Book", BOOK_ALL_ROWS);
        uriMatcher.addURI(AUTHORITY, "Author/#", AUTHOR_SINGLE_ROW);
        uriMatcher.addURI(AUTHORITY, "Publisher/#", PUBLISHER_SINGLE_ROW);
        uriMatcher.addURI(AUTHORITY, "Book/#", BOOK_SINGLE_ROW);
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sort) {

        SupportSQLiteQueryBuilder sSQL = SupportSQLiteQueryBuilder.builder("Book");
        sSQL.columns(projection);
        sSQL.selection(selection, selectionArgs);

        return db.query(sSQL.create());
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case BOOK_ALL_ROWS:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + "Book";
            case AUTHOR_ALL_ROWS:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + "Author";
            case PUBLISHER_ALL_ROWS:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + "Publisher";
            case BOOK_SINGLE_ROW:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + "Book";
            case AUTHOR_SINGLE_ROW:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + "Author";
            case PUBLISHER_SINGLE_ROW:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + "Publisher";
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        if (contentValues == null){
            Toast.makeText(getContext(), "Unable to insert anything. ContentValues is null.",
                    Toast.LENGTH_LONG).show();
            return uri;
        }

        switch (uriMatcher.match(uri)){

            case AUTHOR_ALL_ROWS: {
                Author a = new Author();
                try{
                    a.setName(contentValues.getAsString("name"));
                    a.setSurname(contentValues.getAsString("surname"));
                    a.setLanguage(contentValues.getAsString("language"));
                    a.setYear(contentValues.getAsString("year"));
                    a.setNativeCountry(contentValues.getAsString("nativeCountry"));
                }
                catch (NullPointerException npe){
                    Toast.makeText(getContext(), "NullPointerException while inserting author",
                            Toast.LENGTH_LONG).show();
                }
                SafeFunctions.insertAuthor(getContext(), a, db, false);
                break;
            }

            case BOOK_ALL_ROWS: {
                Book b = new Book();
                try{
                    b.setTitle(contentValues.getAsString("title"));
                    b.setPublicationYear(contentValues.getAsString("publicationYear"));
                    b.setLanguage(contentValues.getAsString("language"));
                    b.setAuthorName(contentValues.getAsString("authorName"));
                    b.setAuthorSurname(contentValues.getAsString("authorSurname"));
                    b.setPublisher(contentValues.getAsString("publisher"));
                    b.setCreationYear(contentValues.getAsString("creationYear"));
                }
                catch (NullPointerException npe){
                    Toast.makeText(getContext(), "NullPointerException while inserting book",
                            Toast.LENGTH_LONG).show();
                }
                SafeFunctions.insertBook(getContext(), b, db, false);
                break;
            }
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection , @Nullable String[] selectionArgs ) {

        if (selectionArgs == null){
            Toast.makeText(getContext(), "Unable to delete anything. selectionArgs is null.",
                    Toast.LENGTH_LONG).show();
            return 0;
        }

        switch (uriMatcher.match(uri)) {
            case BOOK_ALL_ROWS: {

                Book bookToDelete = db.bookDao().
                        strict_findBookWithTitleAndCreationYear(selectionArgs[0],
                                selectionArgs[1]).get(0);

                SafeFunctions.deleteBook(getContext(), bookToDelete, db, false);
                break;
            }
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }


}

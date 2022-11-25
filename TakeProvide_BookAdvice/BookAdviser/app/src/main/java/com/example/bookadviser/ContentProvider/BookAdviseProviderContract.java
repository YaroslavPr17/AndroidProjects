package com.example.bookadviser.ContentProvider;

import android.net.Uri;

public final class BookAdviseProviderContract {
    static final String AUTHORITY = "com.example.bookadviser.provider";
    private static final String BookAdvicePatternURI = "content://com.example.bookadviser.provider";
    public static final Uri CONTENT_URI = Uri.parse(BookAdvicePatternURI);

    public static final Uri AUTHOR_ALL_ROWS_URI = Uri.parse(BookAdvicePatternURI + "/Author");
    public static final Uri BOOK_ALL_ROWS_URI = Uri.parse(BookAdvicePatternURI + "/Book");
    public static final Uri PUBLISHER_ALL_ROWS_URI = Uri.parse(BookAdvicePatternURI + "/Publisher");


}

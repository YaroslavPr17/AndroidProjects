package com.example.adviselistener;

import android.net.Uri;

public final class BookAdviseProviderContract {
    // static final String AUTHORITY = "com.example.bookadviser.provider";
    public static final String BookAdvicePatternURI = "content://com.example.bookadviser.provider";

    public static final Uri AUTHOR_ALL_ROWS_URI = Uri.parse(BookAdvicePatternURI + "/Author");
    public static final Uri BOOK_ALL_ROWS_URI = Uri.parse(BookAdvicePatternURI + "/Book");
    public static final Uri PUBLISHER_ALL_ROWS_URI = Uri.parse(BookAdvicePatternURI + "/Publisher");


}

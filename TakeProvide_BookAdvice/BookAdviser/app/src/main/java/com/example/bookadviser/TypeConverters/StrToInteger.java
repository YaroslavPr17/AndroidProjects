package com.example.bookadviser.TypeConverters;

import androidx.room.TypeConverter;

public class StrToInteger {

    @TypeConverter
    public Integer stringToInt(String str) {
        if (str == null) {
            return null;
        } else {
            return Integer.parseInt(str);
        }
    }
}

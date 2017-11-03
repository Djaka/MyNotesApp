package com.djakapermana.mynotesapp.db;

import android.provider.BaseColumns;

/**
 * Created by Djaka on 28/10/2017.
 */

public class DatabaseContract {

    static String TABLE_NOTE = "note";
    static final class NoteColumns implements BaseColumns{
        //Note Title
        static String TITLE = "title";
        //Note Description
        static String DESCRIPTION = "description";
        //Note Date
        static String DATE = "date";
    }
}

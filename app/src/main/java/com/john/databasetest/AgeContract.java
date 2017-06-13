package com.john.databasetest;

import android.provider.BaseColumns;

/**
 * Created by giann on 13-Jun-17.
 */

public class AgeContract {
    private AgeContract(){}

    public static class AgeEntry implements BaseColumns{
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_AGE = "age";
    }
}

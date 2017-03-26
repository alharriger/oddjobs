package com.osu.cse5236.oddjobs.database;

/**
 * Created by Zenith on 3/26/2017.
 */

public class JobDbSchema {
    public static final class JobTable {
        public static final String NAME = "jobs";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String COMPENSATION = "compensation";
            public static final String DESCRIPTION = "description";
            public static final String DATE = "date";
            public static final String COMPLETED = "completed";
        }
    }
}

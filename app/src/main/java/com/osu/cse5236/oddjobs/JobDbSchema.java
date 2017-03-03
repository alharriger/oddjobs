package com.osu.cse5236.oddjobs;

public class JobDbSchema {
    public static final class JobTable {
        public static final String NAME = "jobs";
    }

    public static final class Cols {
        public static final String UUID = "uuid";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String DATE = "date";
        public static final String PAID_HOURLY = "paidHourly";
        public static final String COMPENSATION = "compensation";
        public static final String POSTER = "poster";
        public static final String VOLUNTEER = "volunteer";
    }
}

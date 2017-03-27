package com.osu.cse5236.oddjobs.database;
/**
 * Created by pavlecoric on 3/23/17.
 */

public class UserDbSchema {
    public static final class UserTable {
        public static final String NAME = "users";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String FIRSTNAME = "firstName";
            public static final String LASTNAME = "lastName";
            public static final String EMAIL = "email";
            public static final String PHONE = "phone";
            public static final String PASSWORD = "password";
        }
    }
}

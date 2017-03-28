package com.osu.cse5236.oddjobs.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.util.Log;

import com.osu.cse5236.oddjobs.User;
import com.osu.cse5236.oddjobs.database.UserDbSchema.UserTable;

/**
 * Created by pavlecoric on 3/27/17.
 */

public class UserCursorWrapper extends CursorWrapper {
    private static final String TAG = "UserCursorWrapper RAWR";

    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser() {
        Log.d(TAG, "getUser() called");
        String firstName = getString(getColumnIndex(UserTable.Cols.FIRSTNAME));
        String lastName = getString(getColumnIndex(UserTable.Cols.LASTNAME));
        String phone = getString(getColumnIndex(UserTable.Cols.PHONE));
        String email = getString(getColumnIndex(UserTable.Cols.EMAIL));
        String password = getString(getColumnIndex(UserTable.Cols.PASSWORD));


        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}


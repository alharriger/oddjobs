package com.osu.cse5236.oddjobs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.osu.cse5236.oddjobs.database.UserBaseHelper;
import com.osu.cse5236.oddjobs.database.UserCursorWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.osu.cse5236.oddjobs.database.UserDbSchema.*;


/**
 * Created by pavlecoric on 3/27/17.
 */

public class UserCollection {
    private static final String TAG = "UserCollection RAWR";

    private static UserCollection sUserCollection;

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private static User mCurrentUser;
    public static String mCurrentUserFullName = "";

    // These variables are used as "global" variables, for the current user session.
    // It feels hacky and not ideal; maybe refactor when possible.
    public static String profileUserEmail;
    public static double currentUserLongitude = 0.0;
    public static double currentUserLatitude = 0.0;
    public static String currentUserCity = "";
    public static String currentUserPhone = "";
    public static String currentUserEmail = "";
    public static UUID editProfileUser;

    public void setCurrentUser(User user) {
        Log.d(TAG, "setCurrentUser() called");
        this.mCurrentUser = user;
        if (this.mCurrentUser != null) {
            Log.d(TAG, "this.mCurrentUser is not null");
            this.currentUserEmail = user.getEmail();
            if (this.mCurrentUser.getFirstName() != null) {
                this.mCurrentUserFullName = mCurrentUser.getFirstName();
            }
            if (this.mCurrentUser.getLastName() != null) {
                this.mCurrentUserFullName = this.mCurrentUserFullName + " " + mCurrentUser.getLastName();
                Log.d(TAG, "full name is " + this.mCurrentUserFullName);
            }
            if (this.mCurrentUser.getPhone() != null) {
                this.currentUserPhone = this.mCurrentUser.getPhone();
                Log.d(TAG, "user phone number is " + this.currentUserPhone);
            }
            if (this.mCurrentUser.getEmail() != null) {
                this.currentUserEmail = this.mCurrentUser.getEmail();
                Log.d(TAG, "user email is " + this.currentUserEmail);
            }
        } else {
            Log.d(TAG, "this.mCurrentUser is null");
        }
    }

    public String getCurrentUserFullName() {
        Log.d(TAG, "get current user called");
        return this.mCurrentUserFullName;
    }

    public User getCurrentUser() {
        Log.d(TAG, "get current user called");
        return this.mCurrentUser;
    }

    public static UserCollection get(Context context) {
        Log.d(TAG, "get() called");
        if (sUserCollection == null) {
            sUserCollection = new UserCollection(context);
        }
        return sUserCollection;
    }

    private UserCollection(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new UserBaseHelper(mContext).getWritableDatabase();
    }

    public void addUser(User u) {
        Log.d(TAG, "addUser() called");
        ContentValues values = getContentValues(u);
        mDatabase.insert(UserTable.NAME, null, values);
    }

//    public void delete(User u) {
//        Log.d(TAG, "deleteUser() called");
//        ContentValues values = getContentValues(u);
//        mDatabase.delete(UserTable.NAME, null, null);
//    }

    public List<User> getUsers() {
        Log.d(TAG, "getUsers() called");
        List<User> users = new ArrayList<>();

        UserCursorWrapper cursor = queryJobs(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                users.add(cursor.getUser());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return users;
    }

    public User getUser(UUID id) {
        Log.d(TAG, "getUser() called");
        UserCursorWrapper cursor = queryJobs(
                UserTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getUser();
        } finally {
            cursor.close();
        }
    }

    public void updateUser(User user) {
        Log.d(TAG, "updateJob() called");
        String uuidString = user.getId().toString();
        ContentValues values = getContentValues(user);

        mDatabase.update(UserTable.NAME, values,
                UserTable.Cols.UUID + " = ?",
                new String[] {uuidString });
    }

    public UserCursorWrapper queryJobs(String whereClause, String[] whereArgs) {
        Log.d(TAG, "queryUsers() called");
        Cursor cursor = mDatabase.query(
                UserTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new UserCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(User user) {
        Log.d(TAG, "getContentValues() called");
        ContentValues values = new ContentValues();
        values.put(UserTable.Cols.UUID, user.getId().toString());
        values.put(UserTable.Cols.FIRSTNAME, user.getFirstName());
        values.put(UserTable.Cols.LASTNAME, user.getLastName());
        values.put(UserTable.Cols.PHONE, user.getPhone());
        values.put(UserTable.Cols.EMAIL, user.getEmail());
        values.put(UserTable.Cols.PASSWORD, user.getPassword());

        return values;
    }
}

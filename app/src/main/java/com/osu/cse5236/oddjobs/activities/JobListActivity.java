package com.osu.cse5236.oddjobs.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.osu.cse5236.oddjobs.JobListFragment;
import com.osu.cse5236.oddjobs.UserCollection;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * Created by Zenith on 3/25/2017.
 */

public class JobListActivity extends SingleFragmentActivity {
    private static final String TAG = "JobListActivity RAWR";

    private LocationManager locationManager = null;
    private LocationListener locationListener = null;

    private Boolean flag = false;

    private String mCurrentUser = "";

    /**
     * Id to identity LOCATION permission requests.
     */
    private static final int REQUEST_ACCESS_COARSE_LOCATION = 0;
    private static final int REQUEST_ACCESS_FINE_LOCATION = 0;

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate called");
        super.onCreate(savedInstanceState);

        mCurrentUser = UserCollection.get(this).getCurrentUserFullName();
        Log.d(TAG, "current user is " + mCurrentUser);

        flag = displayGpsStatus();
        if (flag) {
            locationListener = new JobListActivity.MyLocationListener();
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{ACCESS_FINE_LOCATION}, REQUEST_ACCESS_FINE_LOCATION);
            }
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{ACCESS_COARSE_LOCATION}, REQUEST_ACCESS_COARSE_LOCATION);
            }
        } else {
            alertbox("Ack", "Your GPS is OFF, but it is needed for job locations.");
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onResume() {
        Log.d(TAG, "onResume called");
        super.onResume();
        flag = displayGpsStatus();
        if (flag) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (locationManager != null) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
//                    Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    UserCollection.currentUserLongitude = loc.getLongitude();
//                    UserCollection.currentUserLatitude = loc.getLatitude();
                } else {
                    locationListener = new JobListActivity.MyLocationListener();
                    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{ACCESS_FINE_LOCATION}, REQUEST_ACCESS_FINE_LOCATION);
                    }
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{ACCESS_COARSE_LOCATION}, REQUEST_ACCESS_COARSE_LOCATION);
                    }
                }
            }
        } else {
            alertbox("Ack", "Your GPS is OFF, but it is needed for job locations.");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "No permission!");
                return;
            }
            locationManager.requestLocationUpdates(LocationManager
                    .GPS_PROVIDER, 5000, 10, locationListener);
            System.out.println("RAWR locationManager successfully accessed.");
        } else {
            System.out.println("RAWR locationManager was null.");
        }
    }


    /*----Method to Check GPS is enable or disable ----- */
    private Boolean displayGpsStatus() {
        Log.d(TAG, "displayGpsStatus called");
        ContentResolver contentResolver = getBaseContext()
                .getContentResolver();
        return Settings.Secure
                .isLocationProviderEnabled(contentResolver,
                        LocationManager.GPS_PROVIDER);
    }

    /*----------Method to create an AlertBox ------------- */
    protected void alertbox(String title, String mymessage) {
        Log.d(TAG, "alertbox called");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mymessage)
                .setCancelable(false)
                .setTitle(title)
                .setPositiveButton("Turn it on",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent myIntent = new Intent(
                                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(myIntent);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // cancel the dialog box
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /*---------- Listener class to get coordinates ------------- */
    private class MyLocationListener implements LocationListener {
        final static String SUB_TAG = "MyLocationListener";

        @Override
        public void onLocationChanged(Location loc) {
            Log.d(TAG, SUB_TAG + "alertbox called");
            Toast.makeText(
                    getBaseContext(),
                    "Location changed: Lat: " + loc.getLatitude() + " Lng: "
                            + loc.getLongitude(), Toast.LENGTH_SHORT).show();
            UserCollection.currentUserLongitude = loc.getLongitude();
            UserCollection.currentUserLatitude = loc.getLatitude();

        /*------- To get city name from coordinates -------- */
            String cityName = null;
            Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
                if (addresses.size() > 0) {
                    System.out.println(addresses.get(0).getLocality());
                    cityName = addresses.get(0).getLocality();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            UserCollection.currentUserCity = cityName;
        }

        @Override
        public void onProviderDisabled(String provider) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    }

    @Override
    protected Fragment createFragment() {
        return new JobListFragment();
    }
}
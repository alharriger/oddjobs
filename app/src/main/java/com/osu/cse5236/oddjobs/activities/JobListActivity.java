package com.osu.cse5236.oddjobs.activities;

import android.Manifest;
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
import android.os.Bundle;
import android.provider.Settings;
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

/**
 * Created by Zenith on 3/25/2017.
 */

public class JobListActivity extends SingleFragmentActivity {
    private static final String TAG = "JobListActivity RAWR";

    private LocationManager locationManager = null;
    private LocationListener locationListener = null;

    private Boolean flag = false;

    private String mCurrentUser = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate called");
        super.onCreate(savedInstanceState);

        mCurrentUser = UserCollection.get(this).getCurrentUserFullName();
        Log.d(TAG, "current user is " + mCurrentUser);

        flag = displayGpsStatus();
        System.out.println("RAWR displayGpsStatus is: " + flag.toString());
        if (flag) {
            locationListener = new JobListActivity.MyLocationListener();
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if (ActivityCompat.checkSelfPermission(this,
//                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
            if (locationManager != null) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // This is an auto-generated comment.
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                }
                locationManager.requestLocationUpdates(LocationManager
                        .GPS_PROVIDER, 5000, 10, locationListener);
                System.out.println("RAWR locationManager successfully accessed.");
            } else {
                System.out.println("RAWR locationManager is null. :(");
            }

        } else {
            alertbox("Gps Status!", "Your GPS is: OFF");
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
        Log.d(TAG, "alertbox)_ called");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your Device's GPS is Disable")
                .setCancelable(false)
                .setTitle("** Gps Status **")
                .setPositiveButton("Gps On",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // finish the current activity
                                // AlertBoxAdvance.this.finish();
                                Intent myIntent = new Intent(
                                        Settings.ACTION_SECURITY_SETTINGS);
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

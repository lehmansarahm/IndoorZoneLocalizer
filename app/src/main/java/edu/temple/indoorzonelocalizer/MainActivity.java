package edu.temple.indoorzonelocalizer;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Toast;

/**
 *
 */
public class MainActivity extends AppCompatActivity {
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener mlocListener = new MyLocationListener();

        int locationAccess = getApplicationContext().checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION");
        if (locationAccess == PackageManager.PERMISSION_GRANTED) {
            mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
        }
    }

    /**
     *
     */
    public class MyLocationListener implements LocationListener {
        /**
         *
         * @param loc
         */
        @Override
        public void onLocationChanged(Location loc) {
            loc.getLatitude();
            loc.getLongitude();

            String text = "My current location is: " +
                "Latitude = " + loc.getLatitude() +
                ", Longitude = " + loc.getLongitude();

            Toast.makeText(getApplicationContext(),
                text, Toast.LENGTH_SHORT).show();
        }

        /**
         *
         * @param provider
         */
        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(getApplicationContext(),
                "GPS Disabled", Toast.LENGTH_SHORT ).show();
        }

        /**
         *
         * @param provider
         */
        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText( getApplicationContext(),
                "GPS Enabled", Toast.LENGTH_SHORT).show();
        }

        /**
         *
         * @param provider
         * @param status
         * @param extras
         */
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // do nothing for now
        }
    } /* End of class: MyLocationListener */
} /* End of class: MainActivity */
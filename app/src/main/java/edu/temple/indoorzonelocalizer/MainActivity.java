package edu.temple.indoorzonelocalizer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;

/**
 * Main activity class to display user's GPS coordinates
 */
public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final String[] INITIAL_PERMS = {
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private static final int INITIAL_REQUEST = 1337;

    protected LocationManager locManager;
    TextView latLongTextView;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latLongTextView=(TextView)findViewById(R.id.latLongTextView);
        locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        requestPermissions(INITIAL_PERMS, INITIAL_REQUEST);
        if (PackageManager.PERMISSION_GRANTED == checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            latLongTextView.setText("Location permissions granted.  Launching localizer.");
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
        else {
            latLongTextView.setText("Insufficient location permissions.");
        }
    }

    /**
     *
     * @param loc
     */
    @Override
    public void onLocationChanged(Location loc) {
        String text = "My current location is: " +
                "Latitude = " + loc.getLatitude() +
                ", Longitude = " + loc.getLongitude();
        latLongTextView.setText(text);
    }

    /**
     *
     * @param provider
     */
    @Override
    public void onProviderDisabled(String provider) {
        latLongTextView.setText("GPS disabled.");
    }

    /**
     *
     * @param provider
     */
    @Override
    public void onProviderEnabled(String provider) {
        latLongTextView.setText("GPS enabled.");
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
}
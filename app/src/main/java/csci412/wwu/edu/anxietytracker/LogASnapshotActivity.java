package csci412.wwu.edu.anxietytracker;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LogASnapshotActivity extends AppCompatActivity implements OnMapReadyCallback {
    private SnapshotDatabaseManager dbManager;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_asnapshot);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        dbManager = new SnapshotDatabaseManager(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_log:
                Log.w("MainActivity", "Open Snapshots");
                Intent snapIntent = new Intent(this, LogASnapshotActivity.class);
                this.startActivity(snapIntent);
                return true;
            case R.id.action_jour:
                Log.w("MainActivity", "Open Journal");
                Intent journalIntent = new Intent(this, JournalActivity.class);
                this.startActivity(journalIntent);
                return true;
            case R.id.action_med:
                Log.w("MainActivity", "Open Meditation");
                Intent medIntent = new Intent(this, MeditationActivity.class);
                this.startActivity(medIntent);
                return true;
            case R.id.action_vis:
                Log.w("MainActivity", "Open Visualizations");
                Intent visIntent = new Intent(this, VisualizationsActivity.class);
                this.startActivity(visIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void fillDatabase(View v){
        //ugh
        Spinner moodSelect = (Spinner) findViewById(R.id.moodSpinner);
        String mood = String.valueOf(moodSelect.getSelectedItem());
        double lat = 0;
        double longi = 0;
        int moodNum = 1;
        Snapshot curSnapshot = new Snapshot(0,lat,longi,moodNum);
        dbManager.insert(curSnapshot);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean network_enabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        Location location;
        double longitude = 0.0;
        double latitude = 0.0;

        if (network_enabled) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            location = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location!=null){
                longitude = location.getLongitude();
                latitude = location.getLatitude();
            }
        }

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude))      // Sets the center of the map to location user
                .zoom(15)                   // Sets the zoom
                .build();                   // Creates a CameraPosition from the builder

        // Add a marker in Sydney and move the camera
        LatLng currloc = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(currloc).title("Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currloc, 20));
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    // I don't know if this should accept an ID??
    //Snapshot snap = new Snapshot(0, latitude, longitude, 0);
    //Log.w("MainActivity", "journal = " + tjournal.toString()); ??
    //dbManager.insert(snap);
    //Toast.makeText(this, "Added snapshot at " + latitude + ", " + longitude, Toast.LENGTH_SHORT).show();
}
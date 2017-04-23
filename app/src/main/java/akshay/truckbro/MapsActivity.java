package akshay.truckbro;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GoogleMap sMap;
    private GoogleMap dMap;
    String coordDest="28.6506, 77.2303"; // chandni chowk
    String coordSource="18.6273° N, 73.8041"; //ginjer hotel
    //18.9220° N, 72.8347° E
    LatLng locGH = new LatLng(18.6273, 73.8041);

    LatLng locCC = new LatLng(28.6506, 77.2303);
    LatLng locGH = new LatLng(18.6273, 73.8041);
    LatLng locTaj = new LatLng(27.1750, 78.0422);
    //27.1750° N, 78.0422° E

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        sMap = googleMap;
        dMap= googleMap;
        try {
            // Add a marker in Sydney and move the camera
            //LatLng sourceLatLang = new LatLng(18.5935, 73.7077);
            sMap.addMarker(new MarkerOptions().position(locCC).title("ChandniChowk"));
            sMap.moveCamera(CameraUpdateFactory.newLatLng(sourceLatLang));

            //LatLng destLatLang = new LatLng(28.6506, 77.2303);g
            sMap.addMarker(new MarkerOptions().position(locGH).title("Gingjer Hotel"));
            sMap.moveCamera(CameraUpdateFactory.newLatLng(destLatLang));

            //LatLng sourceLatLang = new LatLng(18.5935, 73.7077);
            sMap.addMarker(new MarkerOptions().position(locGOI).title("Gate of India"));
            sMap.moveCamera(CameraUpdateFactory.newLatLng(sourceLatLang));

            //googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            googleMap.getUiSettings().setZoomGesturesEnabled(true);
            googleMap.getMaxZoomLevel();


            // Add a marker in Sydney and move the camera
            Polyline line1 = googleMap.addPolyline(new PolylineOptions()
                    //.add(new LatLng(51.5, -0.1), new LatLng(40.7, -74.0))
                    .add(locGH, locCC)
                    .width(5)
                    .color(Color.RED));

            Polyline line2 = googleMap.addPolyline(new PolylineOptions()
                    //.add(new LatLng(51.5, -0.1), new LatLng(40.7, -74.0))
                    .add(locGH, locGH)
                    .width(5)
                    .color(Color.GREEN));

            Polyline line3 = googleMap.addPolyline(new PolylineOptions()
                    //.add(new LatLng(51.5, -0.1), new LatLng(40.7, -74.0))
                    .add(locGH, locTaj)
                    .width(5)
                    .color(Color.BLUE));

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
        } finally
        {
        }

    }
}

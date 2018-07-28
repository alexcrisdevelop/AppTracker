package net.apptracker.com.apptracker

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,OnMapReadyCallback {

    private var mMap: GoogleMap? = null

    private val REQUEST_LOCATION_CODE = 1

    //change local dev


    override fun onMyLocationClick(location: Location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }


    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false
    }

    override fun onMapReady(googleMap: GoogleMap?) {

    /*    val sydney = LatLng(-33.852, 151.211)
        googleMap?.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))

        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(sydney)) */

        mMap = googleMap

        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) ) {

            mMap?.isMyLocationEnabled = true
            mMap?.setOnMyLocationButtonClickListener(this)
            mMap?.setOnMyLocationClickListener(this)
       //     mMap?.setMinZoomPreference(10.0f)
          //  mMap?.animateCamera( CameraUpdateFactory.zoomTo( 10.0f ) )


         //   mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(xxxx, xxxx), 10.0f))

        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Get the SupportMapFragment and request notification
            // when the map is ready to be used.

            mapFragment.getMapAsync(this)
        } else {
            // Show rationale and request permission.

            val permissions = Array(1) { Manifest.permission.ACCESS_FINE_LOCATION }

            ActivityCompat.requestPermissions(this, permissions, REQUEST_LOCATION_CODE)
        }


    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_LOCATION_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }
    }



}

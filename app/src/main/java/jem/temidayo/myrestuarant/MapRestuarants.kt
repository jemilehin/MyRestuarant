package jem.temidayo.myrestuarant

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import jem.temidayo.myrestuarant.RestuarantDataObjects.RestuarantAdrress

class MapRestuarants : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private var locationPermissionGranted = false
    private val defaultLocation = LatLng(-33.8523341, 151.2106085)
    private lateinit var restuarantAdrress: Array<LatLng>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_restuarants)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        restuarantAdrress = arrayOf(LatLng(-31.952854, 115.857342),
            LatLng(-33.87365, 151.20689),
            LatLng(-27.47093, 153.0235)
        )
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
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.mapType = GoogleMap.MAP_TYPE_NORMAL
        val zoomLevel = 10f


        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
        for(location in restuarantAdrress){
            map.addMarker(MarkerOptions().position(location).title("Marker in Sydney"))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel))
        }
    }
}
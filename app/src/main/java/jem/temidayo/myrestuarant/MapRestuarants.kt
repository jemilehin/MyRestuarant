package jem.temidayo.myrestuarant

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import jem.temidayo.myrestuarant.RestuarantDataObjects.RestuarantAdrress
import jem.temidayo.myrestuarant.RestuarantDataObjects.RestuarantPosition
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class MapRestuarants : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var map: GoogleMap
    private var locationPermissionGranted = false
//    private val defaultLocation = LatLng(-33.8523341, 151.2106085)
    private lateinit var restuarantAdrress: Array<String>
    private lateinit var Locations: MutableList<LatLng>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_restuarants)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        restuarantAdrress = arrayOf("Ajasa Command Rd, Oke Odo , Lagos", "152 Meiran Rd, Abule Egba , Lagos")
        Locations = ArrayList()
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
        val zoomLevel = 15f
        val geoCoder = Geocoder(this)

        for (latlng in restuarantAdrress){
            val code = geoCoder.getFromLocationName(latlng,1)
            Locations.add(LatLng(code[0].latitude,code[0].longitude))
        }

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
        for(location in Locations){
            map.addMarker(MarkerOptions().position(location).icon(BitmapDescriptorFactory.fromResource(R.drawable.shop_color)))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel))
        }
        map.setOnMarkerClickListener(this)
    }

    override fun onMarkerClick(marker: Marker): Boolean {

        // Retrieve the data from the marker.
//        val clickCount = marker.id as? Int

        // Check if a click count was set, then display the click count.
//        clickCount?.let {
//            val newClickCount = it + 1
//            marker.id = newClickCount
            Toast.makeText(
                    this,
                    "${marker.id} has been clicked times.",
                    Toast.LENGTH_SHORT
            ).show()
//        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false
    }
}
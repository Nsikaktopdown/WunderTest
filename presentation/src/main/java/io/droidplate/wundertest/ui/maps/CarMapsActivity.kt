package io.droidplate.wundertest.ui.maps

import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import io.droidplate.domain.model.Car
import io.droidplate.wundertest.*
import io.droidplate.wundertest.R
import io.droidplate.wundertest.model.CarItem
import io.droidplate.wundertest.ui.base.BaseActivity
import io.droidplate.wundertest.ui.cars.CarActivityViewModel
import io.droidplate.wundertest.utils.Bitmaps
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_car_maps.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class CarMapsActivity : BaseActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mMap: GoogleMap

    var locationDisposable: Disposable? = null
    var userlocation: Location? = null


    private var carlist = mutableListOf<CarItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        getAppInjector().inject(this)

        carlist = intent.getParcelableArrayListExtra("cars")


        checkLocationPermission()

        if (isLocationGranted()!!) {
            retrieveAndDisplayLocation()
        }


        my_location.setOnClickListener {
            updateMapCameraToLatLng(userlocation!!.toLatLng())
        }

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
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)

        for (i in carlist.indices) {

            createMarker(mMap, carlist[i].lat, carlist[i].long, carlist[i].name, R.drawable.car)
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(carlist[10].lat, carlist[10].long)))


        mMap.setOnMarkerClickListener(this@CarMapsActivity)
    }


    private fun createMarker(map: GoogleMap, latitude: Double, longitude: Double, title: String, iconResID: Int): Marker {

        return map.addMarker(MarkerOptions()
                .position(LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .icon(BitmapDescriptorFactory.fromResource(iconResID)))
    }

    private fun updateUserPosition(location: Location?) {

        if (location == null) return
        this.userlocation = location
        my_location.show()

        // mMap.clear() // remove all markers

        val userMarker = mMap.addMarker(
                MarkerOptions()
                        .icon(Bitmaps.descriptor(this, R.drawable.ic_my_location_black_24dp))
                        .position(location.toLatLng())
                        .draggable(false)
        )
        userMarker.setAnchor(0.5f, 1f)
        userMarker.setInfoWindowAnchor(0.5f, -1f)

        userMarker.showInfoWindow() // Show the marker window


        //updateMapCameraToLatLng(location.toLatLng())
    }

    private fun updateMapCameraToLatLng(latLng: LatLng?) {

        if (latLng == null) return

        val cameraPosition = CameraPosition.fromLatLngZoom(latLng, 15f)

        val update = CameraUpdateFactory.newCameraPosition(cameraPosition)

        mMap.animateCamera(update, 750, null)
    }

    private fun retrieveAndDisplayLocation() {

        locationDisposable = userLocation
                .subscribe({ updateUserPosition(it) }, { it.printStackTrace() })
    }

    override fun onMarkerClick(marker: Marker?): Boolean {

        mMap.clear()

        createMarker(mMap, marker!!.position.latitude , marker!!.position.longitude, marker!!.title, R.drawable.car)

        return true
    }

    override fun onStop() {
        super.onStop()
        if (locationDisposable != null) {
            locationDisposable!!.dispose()
        }
    }

    @SuppressLint("RestrictedApi")
    val userLocation: Observable<Location> = Observable.create { e ->

        val client = LocationServices.getFusedLocationProviderClient(applicationContext)

        val request = LocationRequest().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            // numUpdates = 2
            interval = 10000 // 10sec
            fastestInterval = 5000 // 5sec
        }

        val callback = object : LocationCallback() {

            override fun onLocationResult(result: LocationResult) {

                result.locations
                        .filterNotNull()
                        .forEach { if (!e.isDisposed) e.onNext(it) }
            }

            override fun onLocationAvailability(availability: LocationAvailability) {

                if (!availability.isLocationAvailable) {
                    if (!e.isDisposed) e.onError(Throwable("Location not available"))
                }
            }
        }

        e.setCancellable { client.removeLocationUpdates(callback) }

        try {
            client.requestLocationUpdates(request, callback, null)
        } catch (ex: SecurityException) {
            if (!e.isDisposed) e.onError(ex)
        }

    }


    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {

        val granted = PackageManager.PERMISSION_GRANTED

        if (requestCode == RC_LOCATION && permissions.size == 1) {
            onLocationPermissionResult(grantResults[0] == granted)
        }
    }

    companion object {

        @JvmField
        val RC_LOCATION = 1000

        @JvmStatic
        fun intent(context: Context, cars: ArrayList<CarItem>) = Intent(context, CarMapsActivity::class.java)
                .apply { putParcelableArrayListExtra("cars", cars) }

    }
}

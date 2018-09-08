package io.droidplate.wundertest.ui.base

import android.Manifest
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import io.droidplate.wundertest.R
import io.droidplate.wundertest.checkPermissionFor
import io.droidplate.wundertest.requestThesePermissions
import io.droidplate.wundertest.ui.maps.CarMapsActivity
import kotlinx.android.synthetic.main.activity_main.*

open class BaseActivity: AppCompatActivity(){

    var granted: Boolean? = null
    private fun showLocationRationale() {
        val snack = Snackbar.make(main_layout,
                "Shuttlers needs location permission to function",
                Snackbar.LENGTH_INDEFINITE
        )
        snack.setAction(R.string.grant) { requestLocationPermission() }
        snack.show()
    }

     fun checkLocationPermission() {

         granted = checkPermissionFor(Manifest.permission.ACCESS_FINE_LOCATION)

        onLocationPermissionResult(granted!!)
    }


     fun onLocationPermissionResult(granted: Boolean) {

        if (!granted) {
            requestLocationPermission()
        }else
            this.granted = true

    }

    fun isLocationGranted() = granted

   fun requestLocationPermission() {

        requestThesePermissions(CarMapsActivity.RC_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
    }
}
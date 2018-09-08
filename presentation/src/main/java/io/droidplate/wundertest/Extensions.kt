package io.droidplate.wundertest

import android.app.Activity
import android.arch.lifecycle.*
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.gms.maps.model.LatLng
import io.droidplate.wundertest.di.Injector

val Activity.app: App get() = application as App

fun AppCompatActivity.getAppInjector(): Injector = (app).injector

fun toast(message: String, context: Context) = Toast.makeText(context, message, Toast.LENGTH_LONG).show()
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun ProgressBar.showProgress(){
    visibility = View.VISIBLE
}

fun ProgressBar.hideProgress(){
    visibility = View.GONE
}

fun SwipeRefreshLayout.startRefreshing() {
    isRefreshing = true
}

fun SwipeRefreshLayout.stopRefreshing() {
    isRefreshing = false
}

fun Location.toLatLng() = LatLng(latitude, longitude)

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}

inline fun <reified T : ViewModel> FragmentActivity.withViewModel(viewModelFactory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val vm = getViewModel<T>(viewModelFactory)
    vm.body()
    return vm
}

fun Context.checkPermissionFor(permission: String)
        = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Activity.requestThesePermissions(requestCode: Int, vararg permissions: String)
        = ActivityCompat.requestPermissions(this, permissions, requestCode)

fun Activity.shouldShowRationaleFor(permission: String)
        = ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}
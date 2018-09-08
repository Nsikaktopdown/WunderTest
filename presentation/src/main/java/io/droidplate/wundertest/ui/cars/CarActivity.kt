package io.droidplate.wundertest.ui.cars

import android.Manifest
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import io.droidplate.wundertest.*
import io.droidplate.wundertest.model.CarItem
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.content.Intent
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import io.droidplate.wundertest.ui.base.BaseActivity
import io.droidplate.wundertest.ui.maps.CarMapsActivity


class CarActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val carAdapter = CarAdapter()
    private var carlist = mutableListOf<CarItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAppInjector().inject(this)


        checkLocationPermission()

        initViews()

        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent))
        withViewModel<CarActivityViewModel>(viewModelFactory) {
            swipeRefreshLayout.setOnRefreshListener { getCars(true) }
            observe(cars, ::updateCars)
        }

    }

    /**
     * Views initialization
     */
    fun initViews() {

        with(postsRecyclerView) {
            adapter = carAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@CarActivity)
        }
    }

    /**
     * adapter databinding
     * @param data [cars list from the data layer]
     */
    private fun updateCars(data: Data<List<CarItem>>?) {
        data?.let {
            when (it.dataState) {
                DataState.LOADING -> swipeRefreshLayout.startRefreshing()
                DataState.SUCCESS -> swipeRefreshLayout.stopRefreshing()
                DataState.ERROR -> swipeRefreshLayout.stopRefreshing()
            }
            it.data?.let { setData(it) }
            it.message?.let { toast(it, this@CarActivity) }
        }
    }

    fun setData(list: List<CarItem>) {
        carlist = list as MutableList<CarItem>
        carAdapter.addItems(list)
    }

    override
    fun onCreateOptionsMenu(menu: Menu): Boolean {
        val findMenuItems = menuInflater
        findMenuItems.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.maps -> startActivity(CarMapsActivity.intent(this@CarActivity, carlist as ArrayList<CarItem>))
        }

        return super.onOptionsItemSelected(item)
    }



}
package io.droidplate.wundertest.ui.cars

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.droidplate.wundertest.R
import io.droidplate.wundertest.inflate
import io.droidplate.wundertest.model.CarItem
import io.droidplate.wundertest.model.PostItem
import kotlinx.android.synthetic.main.item_car_list.view.*
import kotlinx.android.synthetic.main.item_post.view.*

class CarAdapter : RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    private var items = ArrayList<CarItem>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder = ViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_car_list)) {

        fun bind(items: CarItem) {
            itemView.car_name.text = items.name
            itemView.car_address.text = items.address
            itemView.car_engine.text = items.engineType
            itemView.car_fuel.text = "${items.fuel}"

        }
    }

    fun addItems(list: List<CarItem>) {
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }
}
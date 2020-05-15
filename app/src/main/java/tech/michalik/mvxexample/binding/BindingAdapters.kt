package tech.michalik.mvxexample.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import tech.michalik.mvxexample.Time
import tech.michalik.mvxexample.ui.main.ItemDisplayable
import tech.michalik.mvxexample.ui.main.ItemsListView
import javax.inject.Inject

class BindingAdapters @Inject constructor() {

    @BindingAdapter("bind:time")
    fun TextView.time(time: Time) {
        text = time.value.toString()
    }

    @BindingAdapter("bind:items")
    fun ItemsListView.bindItems(list: List<ItemDisplayable>) {
        setItems(list)
    }
}
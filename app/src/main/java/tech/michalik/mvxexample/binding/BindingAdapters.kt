package tech.michalik.mvxexample.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import tech.michalik.mvxexample.Time
import javax.inject.Inject

class BindingAdapters @Inject constructor() {

    @BindingAdapter("bind:time")
    fun TextView.time(time: Time) {
        text = time.value.toString()
    }
}
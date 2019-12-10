package tech.michalik.mvxexample.ui.main

import androidx.databinding.Bindable
import io.reactivex.Flowable
import io.reactivex.rxkotlin.subscribeBy
import tech.michalik.mvx.BaseViewModel
import tech.michalik.mvx.observable
import tech.michalik.mvxexample.Time
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {
    @get:Bindable
    var currentTime: Time by observable(
        initialValue = Time(0L),
        br = tech.michalik.mvxexample.BR.currentTime
    )

    init {
        currentTime = System.currentTimeMillis().let(::Time)
        Flowable.interval(0L, 1L, TimeUnit.SECONDS)
            .subscribeBy(
                onNext = {
                    currentTime = Time(it)
                }
            )
    }
}


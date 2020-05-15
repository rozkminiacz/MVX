package tech.michalik.mvxexample.ui.main

import androidx.databinding.Bindable
import io.reactivex.Flowable
import io.reactivex.rxkotlin.subscribeBy
import tech.michalik.mvx.BaseViewModel
import tech.michalik.mvx.observable
import tech.michalik.mvxexample.Time
import tech.michalik.mvxexample.BR
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {
    @get:Bindable
    var currentTime: Time by observable(
        initialValue = Time(0L),
        br = BR.currentTime
    )

    @get:Bindable
    var items: List<ItemDisplayable> by observable(emptyList(), BR.items)

    init {
        currentTime = System.currentTimeMillis().let(::Time)
        Flowable.interval(0L, 1L, TimeUnit.SECONDS)
            .subscribeBy(
                onNext = {
                    val time = Time(it)
                    currentTime = time
                    items = items.plus(
                        ItemDisplayable(
                            time.value.toString(),
                            UUID.randomUUID().toString()
                        )
                    )
                }
            )
    }
}


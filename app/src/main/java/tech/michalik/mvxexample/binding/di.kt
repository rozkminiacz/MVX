package tech.michalik.mvxexample.binding

import androidx.databinding.DataBindingComponent
import dagger.Component
import dagger.Module

/**
 * Created by jaroslawmichalik on 2019-12-10
 */
@Module
class BindingAdaptersModule {

}


@BindingScope
@Component(modules = [(BindingAdaptersModule::class)])
interface BindingComponent : DataBindingComponent {
    override fun getBindingAdapters(): BindingAdapters
}

annotation class BindingScope

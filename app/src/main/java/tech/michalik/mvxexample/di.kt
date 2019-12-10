package tech.michalik.mvxexample

import android.app.Application
import androidx.databinding.DataBindingUtil
import dagger.BindsInstance
import dagger.Component
import tech.michalik.mvxexample.binding.BindingAdaptersModule
import tech.michalik.mvxexample.binding.DaggerBindingComponent
import tech.michalik.mvxexample.ui.main.MainComponent
import tech.michalik.mvxexample.ui.main.MainModule
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {

    fun plusMainComponent(mainModule: MainModule): MainComponent

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

object AppModule {
    lateinit var appComponent: AppComponent

    fun attachApp(application: Application) {
        appComponent = DaggerAppComponent.builder()
            .application(application)
            .build()

        val bindingComponent = DaggerBindingComponent.builder()
            .bindingAdaptersModule(BindingAdaptersModule())
            .build()

        DataBindingUtil.setDefaultComponent(bindingComponent)
    }
}
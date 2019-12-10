package tech.michalik.mvxexample.ui.main

import dagger.Module
import dagger.Subcomponent

/**
 * Created by jaroslawmichalik on 2019-12-10
 */
@Module
class MainModule

@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun inject(mainScreen: MainScreen)
}
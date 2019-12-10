package tech.michalik.mvxexample.ui.main

import tech.michalik.mvx.MvvmActivity
import tech.michalik.mvxexample.AppModule
import tech.michalik.mvxexample.R
import tech.michalik.mvxexample.databinding.MainScreenBinding

/**
 * Created by jaroslawmichalik on 2019-12-10
 */
class MainScreen : MvvmActivity<MainViewModel, MainScreenBinding>(
    layoutId = R.layout.main_screen,
    vmClass = MainViewModel::class.java,
    viewModelBr = tech.michalik.mvxexample.BR.viewModel
) {

    override fun inject() {
        AppModule.appComponent.plusMainComponent(MainModule())
            .inject(this)
    }
}

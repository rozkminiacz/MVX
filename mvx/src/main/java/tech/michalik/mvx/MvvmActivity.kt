package tech.michalik.mvx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by jaroslawmichalik on 18/11/2018
 */

abstract class MvvmActivity<VM : ViewModel, VB : ViewDataBinding>(
    private val layoutId: Int,
    private val vmClass: Class<VM>,
    private val viewModelBr: Int
) : AppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: GenericViewModelProviderFactory<VM>

    val viewModel by lazy {
        ViewModelProvider(this@MvvmActivity, viewModelProvider).get(vmClass)
    }

    val binding by lazy {
        DataBindingUtil.setContentView<VB>(this, layoutId)
    }

    abstract fun inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        binding.setVariable(viewModelBr, viewModel)
    }
}


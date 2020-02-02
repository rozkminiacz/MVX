package tech.michalik.mvx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import javax.inject.Inject

/**
 * Created by jaroslawmichalik on 02/04/2019
 */
abstract class MvvmFragment<VM : ViewModel, VD : ViewDataBinding>(
    private val layoutId: Int,
    private val vmClass: Class<VM>,
    private val viewModelBr: Int
) : Fragment() {

    @Inject
    lateinit var viewModelProvider: GenericViewModelProviderFactory<VM>

    val viewModel: VM by lazy {
        ViewModelProvider(this@MvvmFragment, viewModelProvider).get(vmClass)
    }

    var binding: VD? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inject()
        val view = inflater.inflate(layoutId, container, false)
        binding = DataBindingUtil.bind<VD>(view)
        binding?.setVariable(viewModelBr, viewModel)

        binding?.run { onBindingReady(binding = this) }

        return view
    }

    open fun onBindingReady(binding: VD) {

    }

    abstract fun inject()

    fun viewModel(): VM? {
        return viewModel
    }

}
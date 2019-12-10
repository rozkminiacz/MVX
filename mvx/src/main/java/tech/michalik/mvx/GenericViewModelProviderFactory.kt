package tech.michalik.mvx

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class GenericViewModelProviderFactory<T : ViewModel> @Inject constructor(val provider: Provider<T>) : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return provider.get() as T
  }
}
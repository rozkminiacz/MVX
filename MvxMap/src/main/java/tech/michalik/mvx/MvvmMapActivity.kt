package tech.michalik.mvx

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.MapsInitializer

abstract class MvvmMapActivity<VM : ViewModel, VB : ViewDataBinding>(
    layoutId: Int,
    vmClass: Class<VM>,
    viewModelBr: Int) : MvvmActivity<VM, VB>(layoutId, vmClass, viewModelBr), MapViewDelegate {

  override fun onCreate(savedInstanceState: Bundle?) {
    exectueOnCreate(savedInstanceState)

    super.onCreate(savedInstanceState)
  }

  companion object {
    const val MAPVIEW_BUNDLE_KEY: String = "MAPVIEW_BUNDLE_KEY"
    val TAG: String = MapViewFragmentDelegate::class.java.simpleName
  }

  protected fun exectueOnCreate(savedInstanceState: Bundle?) {
    MapsInitializer.initialize(this)

    val mapViewBundle = if (savedInstanceState != null) {
      savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
    } else {
      Bundle()
    }
    mapView()?.onCreate(mapViewBundle)
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    executeOnSaveInstanteState(outState)
  }

  private fun executeOnSaveInstanteState(outState: Bundle) {
    var mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY)
    if (mapViewBundle == null) {
      mapViewBundle = Bundle()
      outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle)
    }

    mapView()?.onSaveInstanceState(mapViewBundle)
  }

  override fun onResume() {
    super.onResume()
    mapView()?.onResume()
  }

  override fun onStart() {
    super.onStart()
    mapView()?.onStart()
  }

  override fun onStop() {
    super.onStop()
    mapView()?.onStop()
  }

  override fun onPause() {
    mapView()?.onPause()
    super.onPause()
  }

  override fun onDestroy() {
    mapView()?.onDestroy()
    super.onDestroy()
  }

  override fun onLowMemory() {
    super.onLowMemory()
    mapView()?.onLowMemory()
  }
}
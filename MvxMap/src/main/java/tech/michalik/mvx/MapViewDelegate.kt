package tech.michalik.mvx

import com.google.android.gms.maps.MapView

/**
 * Created by jaroslawmichalik on 10.09.2018
 */
interface MapViewDelegate {
  fun mapView(): MapView?
}
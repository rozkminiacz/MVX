package tech.michalik.mvx

import androidx.annotation.CallSuper
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel
import kotlin.properties.ObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class BaseViewModel : ViewModel(), androidx.databinding.Observable {

  open fun close() {

  }

  @CallSuper
  override fun onCleared() {
    close()
    super.onCleared()
  }

  @Transient
  private var mCallbacks: PropertyChangeRegistry? = null

  override fun addOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback) {
    synchronized(this) {
      if (mCallbacks == null) {
        mCallbacks = PropertyChangeRegistry()
      }
    }
    mCallbacks!!.add(callback)
  }

  override fun removeOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback) {
    synchronized(this) {
      if (mCallbacks == null) {
        return
      }
    }
    mCallbacks!!.remove(callback)
  }

  /**
   * Notifies listeners that all properties of this instance have changed.
   */
  fun notifyChange() {
    synchronized(this) {
      if (mCallbacks == null) {
        return
      }
    }
    mCallbacks!!.notifyCallbacks(this, 0, null)
  }

  /**
   * Notifies listeners that a specific property has changed. The getter for the property
   * that changes should be marked with [Bindable] to generate a field in
   * `BR` to be used as `fieldId`.
   *
   * @param fieldId The generated BR id for the Bindable field.
   */
  fun notifyPropertyChanged(fieldId: Int) {
    synchronized(this) {
      if (mCallbacks == null) {
        return
      }
    }
    mCallbacks!!.notifyCallbacks(this, fieldId, null)
  }


}

fun <T> BaseViewModel.observable(initialValue: T, br: Int)
    : ReadWriteProperty<Any?, T> = object : ObservableProperty<T>(initialValue) {
  override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {
    if (oldValue != newValue) {
      this@observable.notifyPropertyChanged(br)
    }
  }
}

fun BaseViewModel.onChange(vararg propertyIds: Int, block: () -> Unit) {
  addOnPropertyChangedCallback(object : androidx.databinding.Observable.OnPropertyChangedCallback() {
    override fun onPropertyChanged(sender: androidx.databinding.Observable?, propertyId: Int) {
      if (propertyIds.contains(propertyId)) {
        block()
      }
    }
  })
}

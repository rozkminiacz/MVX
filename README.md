# MVX - utility library for MVVM with databinding

## Installation

build.gradle
```gradle
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

app/build.gradle
```gradle
  implementation 'com.github.rozkminiacz.MVX:mvx:1.0.1.1'
```

## Creating basic screen with MVX

1. Create layout resource:
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:bind="http://schemas.android.com/tools">

    <data>

        <variable
            name="viewModel"
            type="tech.michalik.mvxexample.ui.main.MainViewModel" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <TextView
            style="@style/TextAppearance.AppCompat.Title"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:text="@{viewModel.someText}" />

    </LinearLayout>
</layout>
```

2. Create viewmodel:
```kotlin
import androidx.databinding.Bindable
import tech.michalik.mvx.BaseViewModel
import tech.michalik.mvx.observable
import tech.michalik.mvxexample.BR
import tech.michalik.mvxexample.Time
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {
    @get:Bindable
    var someText: String by observable(
        initialValue = "",
        br = BR.someText
    )
}
```

3. Create activity:
```kotlin
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
    //or provide view model in other way - you are not bound to any DI framework
    override fun inject() {
        AppModule.appComponent.plusMainComponent(MainModule())
            .inject(this)
    }
}
```
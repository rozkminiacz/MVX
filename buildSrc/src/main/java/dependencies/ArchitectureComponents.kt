package dependencies

/**
 * Created by jaroslawmichalik on 2018-12-24
 */
object ArchitectureComponents : DependencyContainer {

  val browserVersion = "1.0.0-beta01"

  val workManager = "android.arch.work:work-runtime:1.0.0-beta01"
  val design = "com.google.android.material:material:1.0.0"
  val recyclerView = "androidx.recyclerview:recyclerview:1.0.0"
  val appCompat = "androidx.appcompat:appcompat:1.0.0"
  val support = "androidx.legacy:legacy-support-v4:1.0.0"
  val constraint = "androidx.constraintlayout:constraintlayout:1.1.2"
  val browser = "androidx.browser:browser:$browserVersion"

  override val all: List<String> = listOf(
      workManager,
      design,
      recyclerView,
      appCompat,
      support,
      constraint,
      browser
  )

  override val kapt: String = "com.android.databinding:compiler:3.2.1"

}

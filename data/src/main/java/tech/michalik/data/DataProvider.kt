package tech.michalik.data

import io.reactivex.Single

/**
 * Created by jaroslawmichalik on 20.01.2018
 */

interface DataProvider<T> {
  fun getOne(id: String, strategy: Strategy = Strategy.Online): Single<T>
  fun getBy(specification: Specification, strategy: Strategy = Strategy.Online): Single<List<T>>
  fun getAll(strategy: Strategy = Strategy.Online): Single<List<T>>

  fun create(t: T): Single<T> = Single.never()
}
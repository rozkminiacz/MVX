package tech.michalik.data

import io.reactivex.Single

abstract class AbstractProvider<T>(var baseDao: BaseDao<T> = EmptyBaseDao()) : DataProvider<T> {
  private val TAG = javaClass.simpleName

  protected fun Single<T>.saveOnSuccess() = doOnSuccess { baseDao.add(it) }

  protected fun Single<List<T>>.saveListOnSuccess() = doOnSuccess {
    baseDao.removeAll()
    baseDao.addAll(it)
  }

  fun Single<T>.cachedOnError(id: String) = onErrorReturn {
    baseDao.findById(id)
  }

  open fun <T> Single<T>.executePendingOnSuccess(): Single<T> = Single.never()

  class EmptyBaseDao<T> : BaseDao<T> {
    override fun add(item: T) = throw Exception()

    override fun addAll(items: List<T>) = throw Exception()

    override fun findAll(): List<T> = throw Exception()

    override fun findById(id: String): T = throw Exception()

    override fun remove(specification: Specification) = throw Exception()

    override fun removeAll() = throw Exception()

    override fun query(specification: Specification): List<T> = throw Exception()

  }
}
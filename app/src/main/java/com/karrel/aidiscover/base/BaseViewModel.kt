package com.karrel.aidiscover.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.karrel.aidiscover.util.RVBLog
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.Subject

abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    val disposables = CompositeDisposable()

    private val states = hashMapOf<String, State>()
    private val cursors = hashMapOf<String, String?>()
    private val counts = hashMapOf<String, Int>()

    private val loadingDisposable = hashMapOf<String, Disposable>()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onCleared() {
        super.onCleared()
        RVBLog.d()
        disposables.dispose()
    }

    protected fun Disposable.disposeOnClear(): Disposable {
        disposables.add(this)
        return this
    }

    protected fun <T> Observable<T>.manageStates(
        requestName: String,
        shouldLoad: Boolean = false,
        statePublisher: Subject<State>? = null
    ): Observable<T> {
        val state = getState(requestName)
        if (shouldLoad.not() && state == State.Loading) {
            return Observable.empty()
        }

        return this
            .doOnSubscribe { disposable ->
                putState(requestName, State.Loading, statePublisher)
                loadingDisposable[requestName] = disposable
            }
            .doOnNext { putState(requestName, State.Success, statePublisher) }
            .doOnError { putState(requestName, State.Fail, statePublisher) }
            .doOnDispose { putState(requestName, State.Uninitialized, statePublisher) }
    }

    protected fun putState(requestName: String, state: State, statePublisher: Subject<State>? = null) {
        states[requestName] = state
        statePublisher?.onNext(state)
        if (state == State.Loading) {
            return
        }

        loadingDisposable.remove(requestName)
    }

    fun getState(requestName: String): State {
        return states[requestName] ?: State.Uninitialized
    }

    protected fun putCursor(requestName: String, cursor: String?) {
        cursors[requestName] = cursor
    }

    fun getCursor(requestName: String): String? {
        return cursors[requestName]
    }

    protected fun putCount(requestName: String, count: Int) {
        counts[requestName] = count
    }

    fun getCount(requestName: String): Int {
        return counts[requestName] ?: 0
    }

    fun getDisposable(requestName: String): Disposable? {
        return loadingDisposable[requestName]
    }

    fun isLoading(requestName: String): Boolean {
        return states[requestName] == State.Loading || loadingDisposable[requestName]?.isDisposed?.not() == true
    }

    enum class State {
        Uninitialized,
        Loading,
        Success,
        Fail
    }
}
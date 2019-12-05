package com.karrel.aidiscover.ext

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.uber.autodispose.ObservableSubscribeProxy
import com.uber.autodispose.SingleSubscribeProxy
import com.uber.autodispose.android.lifecycle.autoDisposable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.internal.functions.Functions

/**
 * lifecycle to onDestroy, dismiss observable
 */
fun <T> Observable<T>.onDestroy(owner: LifecycleOwner): ObservableSubscribeProxy<T> {
    return autoDisposable(owner, Lifecycle.Event.ON_DESTROY)
}

fun <T> Single<T>.onDestroy(owner: LifecycleOwner): SingleSubscribeProxy<T> {
    return autoDisposable(owner, Lifecycle.Event.ON_DESTROY)
}


/**
 * lifecycle to onStop, dismiss observable
 */
fun <T> Observable<T>.onStop(owner: LifecycleOwner): ObservableSubscribeProxy<T> {
    return autoDisposable(owner, Lifecycle.Event.ON_STOP)
}


fun <T> Observable<T>.subscribeEmptyConsume(): Disposable {
    return this.subscribe(Functions.emptyConsumer(), Functions.emptyConsumer())
}

inline fun <T> Observable<T>.subscribeWithEmptyError(crossinline onNext: (T) -> Unit): Disposable {
    return this.subscribe({ onNext.invoke(it) }, { it.printStackTrace() })
}

fun <T> ObservableSubscribeProxy<T>.subscribeEmptyConsume(): Disposable {
    return this.subscribe(Functions.emptyConsumer(), Functions.emptyConsumer())
}

inline fun <T> ObservableSubscribeProxy<T>.subscribeWithEmptyError(crossinline onNext: (T) -> Unit): Disposable {
    return this.subscribe({ onNext.invoke(it) }, { it.printStackTrace() })
}


fun <T> Observable<T>.observeOnMainThread(): Observable<T> =
    observeOn(AndroidSchedulers.mainThread())
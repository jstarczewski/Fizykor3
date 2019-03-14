package com.clakestudio.pc.fizykor

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Singleton

@Singleton
class DrawerLiveEvent<T> : MutableLiveData<T>() {

    private val pending = AtomicBoolean(false)
    private val isOwnerSet = AtomicBoolean(false)
    private lateinit var owner: LifecycleOwner

    @MainThread
    fun observe(observer: Observer<in T>) {

        super.observe(owner, Observer<T> { t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })

    }

    fun setOwner(owner: LifecycleOwner) {
        if (isOwnerSet.compareAndSet(false, true))
            this.owner = owner
    }

    @MainThread
    override fun setValue(value: T?) {
        pending.set(true)
        super.setValue(value)
    }

    @MainThread
    fun call() {
        value = null
    }

}
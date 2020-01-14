package com.example.mytestapplication.base

import androidx.lifecycle.Observer

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

fun <T> eventObserver(callback: (T) -> Unit): Observer<Event<T>> {
    return Observer {
        it?.getContentIfNotHandled()?.let {
            callback.invoke(it)
        }
    }
}
package com.gino.reddittestclient.utils

import android.util.Log

/**
 * @author gino.ghiotto
 */
object Logger {

    private const val TAG = "Reddit_client"

    /**
     * dt: Debug with Thread details.
     * Print current thread name plus given value.
     */
    fun dt(value: String) {
        Log.d(TAG, "Thread Name: ${Thread.currentThread().name} - $value")
    }
}
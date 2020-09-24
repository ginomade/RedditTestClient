package com.gino.reddittestclient.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Lazy
import javax.inject.Inject

/**
 * @author gino.ghiotto
 */
class ViewModelFactory<T : ViewModel>
@Inject constructor(private val viewModel: @JvmSuppressWildcards Lazy<T>) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModel.get() as T
}
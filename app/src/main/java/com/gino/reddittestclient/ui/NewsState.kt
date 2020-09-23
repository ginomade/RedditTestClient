package com.gino.reddittestclient.ui

import com.gino.reddittestclient.utils.RedditNews

/**
 * @author gino.ghiotto
 */
sealed class NewsState {
    class Success(val redditNews: RedditNews) : NewsState()
    class Error(val message: String?) : NewsState()
}
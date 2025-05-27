package com.artemissoftware.njordshop.core.domain

import com.artemissoftware.njordshop.core.domain.error.NSError


sealed interface Resource<T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Failure<T>(val error: NSError) : Resource<T>

    fun onSuccess(block: (T) -> Unit): Resource<T> {
        if (this is Success) block(data)
        return this
    }

    fun onFailure(block: (NSError) -> Unit): Resource<T> {
        if (this is Failure) block(error)
        return this
    }
}
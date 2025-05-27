package com.artemissoftware.njordshop.core.network.util

import com.artemissoftware.njordshop.core.domain.Resource
import com.artemissoftware.njordshop.core.domain.error.DataError
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException

internal object HandleApi {
    suspend fun <T> safeApiCall(callFunction: suspend () -> T): T {
        return try {
            callFunction.invoke()
        } catch (ex: Exception) {
            throw ex
        }
    }

    inline fun <T> safeNetworkCall(apiCall: () -> T): Resource<T> {
        return try {
            Resource.Success(data = apiCall())
        } catch (ex: Exception) {
            Resource.Failure(error = handleException(ex))
        }
    }

    private fun handleException(ex: Exception): DataError {
        return when (ex) {
            is UnknownHostException -> {
                DataError.NetworkError.UnknownHost
            }

            is ConnectException -> {
                DataError.NetworkError.Connect
            }

            is SocketTimeoutException -> {
                DataError.NetworkError.SocketTimeout
            }
            is CancellationException -> {
                throw ex
            }
            else -> DataError.NetworkError.Unknown
        }
    }
}
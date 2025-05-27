package com.artemissoftware.njordshop.core.domain.error

sealed interface DataError: NSError {

    sealed class NetworkError : DataError {
        data class Error(val message: String) : NetworkError()
        data object SocketTimeout : NetworkError()
        data object Unknown : NetworkError()
        data object UnknownHost : NetworkError()
        data object Connect : NetworkError()
    }
}
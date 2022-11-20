package com.example.binarwatchflix.wrapper

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Idle<T>(data: T? = null) : Resource<T>(data)
    class Success<T>(data: T?) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(data: T? = null, message: String? = null) : Resource<T>(data, message)
}

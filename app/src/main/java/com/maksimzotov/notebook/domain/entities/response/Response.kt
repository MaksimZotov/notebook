package com.maksimzotov.notebook.domain.entities.response

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

sealed class Response <out T> (val status: Status, val data: T?, val message:String?) {

    data class Success<out R>(val _data: R?): Response<R>(
        status = Status.SUCCESS,
        data = _data,
        message = null
    )

    class Error(message: String? = null): Response<Nothing>(
        status = Status.ERROR,
        data = null,
        message = message
    )

    class Loading(message: String? = null): Response<Nothing>(
        status = Status.LOADING,
        data = null,
        message = message
    )
}
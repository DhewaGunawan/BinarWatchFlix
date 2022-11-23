package com.example.binarwatchflix.base.exception


class ApiErrorException(override val message: String? = null, val httpCode: Int? = null) :
    Exception()
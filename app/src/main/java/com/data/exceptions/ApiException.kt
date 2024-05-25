package com.data.exceptions

class ApiException(message: String, val code: Int): Exception(message)
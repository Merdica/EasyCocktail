package de.twosoulsmedia.easycocktails.data

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import java.io.IOException
import java.net.UnknownHostException

private const val TAG = "ExceptionHandler"

fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
    Log.d(TAG, "Coroutine catched error: ${e.message}")
}

fun handleExceptions(e: Exception) {
    when(e) {
        is IOException -> handleIOException(e)
        else -> Log.e(TAG, "Unknown Error: ${e.message}")
    }
}

private fun handleIOException(exception: IOException) {
    when (exception) {
        is UnknownHostException -> Log.e(TAG, "UnknownHostException: IP address of the host could not be determined")
        else -> Log.e(TAG, "Unknown IOException: ${exception.message}")
    }
}

fun handleHTTPException(errorCode: Int) {
    Log.d("CocktailRepository", "HTTP Error ${errorCode}")
}
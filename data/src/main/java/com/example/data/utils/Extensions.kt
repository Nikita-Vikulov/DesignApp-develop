package com.example.data.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KFunction1

suspend inline fun <T, R> Iterable<T>.parallelMap(
    coroutineContext: CoroutineContext = Dispatchers.Default,
    crossinline transform: suspend (T) -> R
) = coroutineScope {
    map {
        async(coroutineContext) {
            transform(it)
        }
    }.awaitAll()
}


suspend fun <T, R> Iterable<T>.parallelMap(
    transform: KFunction1<T, R>,
    coroutineContext: CoroutineContext = Dispatchers.Default
) = parallelMap(coroutineContext) {
    transform(it)
}
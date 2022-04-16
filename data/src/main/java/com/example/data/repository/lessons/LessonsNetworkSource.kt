package com.example.data.repository.lessons

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import com.example.data.LessonsAPIService

class LessonsNetworkSource(retrofit: Retrofit): LessonsSource {

    private val service = retrofit.create(LessonsAPIService::class.java)

    override suspend fun lesson(id: Int) = withContext(Dispatchers.IO) {
        try { service.getLesson(id) }
        catch(e: Exception) { null }
    }

    override suspend fun lessons() = withContext(Dispatchers.IO) {
        service.getLessons()
    }

}
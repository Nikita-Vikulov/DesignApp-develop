package com.example.domain.repository

import kotlinx.coroutines.flow.Flow
import com.example.domain.Lesson

interface LessonsRepository {

    fun lessons(): Flow<Result<List<Lesson>>>

    suspend fun lesson(id: Int): Lesson?

}
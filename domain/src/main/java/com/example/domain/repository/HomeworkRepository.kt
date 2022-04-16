package com.example.domain.repository

import kotlinx.coroutines.flow.Flow
import com.example.domain.Homework

interface HomeworkRepository {

    fun homework(): Flow<Result<List<Homework>>>

}
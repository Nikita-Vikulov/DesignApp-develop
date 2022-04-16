package com.example.data.repository.homework

import kotlinx.coroutines.flow.flow
import com.example.data.entity.homework.HomeworkMapper
import com.example.data.utils.parallelMap
import com.example.domain.repository.HomeworkRepository
import java.io.IOException

class HomeworkRepository(
    private val homeworkSourceProvider: HomeworkSourceProvider,
    private val mapper: HomeworkMapper
) : HomeworkRepository {

    override fun homework() = flow {
        val homework = try {
            homeworkSourceProvider.homeworkSource().homework()
        } catch (e: IOException) {
            emit(Result.failure(e))
            return@flow
        }
        emit(Result.success(homework.parallelMap {
            mapper.mapFromEntity(it)
        }))
    }

}
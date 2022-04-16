package com.example.data.repository.lessons

import kotlinx.coroutines.flow.flow
import com.example.data.entity.lesson.LessonMapper
import com.example.data.utils.parallelMap
import com.example.domain.repository.LessonsRepository
import java.io.IOException

class LessonsRepository(private val lessonsSourceProvider: LessonsSourceProvider, private val lessonMapper: LessonMapper) :
    LessonsRepository {

    override fun lessons() = flow {
        val lessons = try {
             lessonsSourceProvider.lessonsSource().lessons()
        }
        catch (e: IOException) {
            emit(Result.failure(e))
            return@flow
        }
        emit(Result.success(lessons.parallelMap(lessonMapper::mapFromEntity)))
    }

    override suspend fun lesson(id: Int) = try {
        lessonsSourceProvider.lessonsSource().lesson(id)?.let {
            lessonMapper.mapFromEntity(it)
        }
    }
    catch (e: IOException) {
        null
    }

}

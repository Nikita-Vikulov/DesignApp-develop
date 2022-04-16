package com.example.data.entity.homework

import com.example.data.repository.lessons.LessonsRepository
import com.example.data.utils.DateHelper
import com.example.domain.Homework
import java.io.IOException
import java.util.*

class HomeworkMapper(
    private val lessonsRepository: LessonsRepository,
) {

    suspend fun mapFromEntity(homework: HomeworkEntity) = Homework(
        homework.id,
        lessonsRepository.lesson(homework.lessonId) ?: throw IOException("No lesson with this id"),
        DateHelper.dayFormat.parse(homework.expireTime).time,
        homework.task
    )

    suspend fun mapToEntity(homework: Homework) =
        HomeworkEntity(homework.id, homework.lesson.id, DateHelper.dayFormat.format(Date(homework.expireTime)), homework.task)

}
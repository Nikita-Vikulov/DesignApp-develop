package com.example.data.entity.lesson

import com.example.data.utils.DateHelper
import com.example.domain.Lesson
import java.util.*

class LessonMapper {

    fun mapFromEntity(lesson: LessonEntity) = Lesson(
        lesson.id, lesson.name, lesson.description,
        lesson.teacher, DateHelper.today.parse(lesson.time).time, lesson.duration)

    fun mapToEntity(lesson: Lesson) = LessonEntity(lesson.id, lesson.name, lesson.description, lesson.teacher, DateHelper.today.format(
        Date(lesson.time)
    ), lesson.duration)

}
package com.example.data.repository.lessons

import com.example.data.entity.lesson.LessonEntity

interface LessonsSource {

    suspend fun lessons(): List<LessonEntity>

    suspend fun lesson(id: Int): LessonEntity?

}
package com.example.data

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import com.example.data.entity.lesson.LessonEntity

interface LessonsAPIService {

    @GET("get_lessons.php")
    suspend fun getLessons(): List<LessonEntity>

    @FormUrlEncoded
    @POST("get_lesson.php")
    suspend fun getLesson(@Field("id") id: Int): LessonEntity

}
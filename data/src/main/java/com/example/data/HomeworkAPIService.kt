package com.example.data

import retrofit2.http.GET
import com.example.data.entity.homework.HomeworkEntity

interface HomeworkAPIService {

    @GET("get_homework.php")
    suspend fun getHomework(): List<HomeworkEntity>

}
package com.example.data.repository.homework

import com.example.data.entity.homework.HomeworkEntity

interface HomeworkSource {

    suspend fun homework(): List<HomeworkEntity>

}
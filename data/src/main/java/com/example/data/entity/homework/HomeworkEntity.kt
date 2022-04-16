package com.example.data.entity.homework

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class HomeworkEntity(val id: Int, @SerialName("lesson_id") val lessonId: Int, @SerialName("expire_time") val expireTime: String, val task: String)
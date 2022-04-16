package com.example.data.entity.lesson

import kotlinx.serialization.Serializable

@Serializable
class LessonEntity(val id: Int, val name: String, val description: String, val teacher: String, val time: String, val duration: Int)
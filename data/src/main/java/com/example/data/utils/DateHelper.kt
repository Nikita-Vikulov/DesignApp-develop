package com.example.data.utils

import com.example.data.utils.DateHelper.today
import com.example.domain.Lesson
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateHelper {

    val dayFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ROOT)

    val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ROOT)

    val today = SimpleDateFormat("HH:mm", Locale.ROOT)

}

fun Lesson.time() = today.format(time) + " - " + today.format(time + TimeUnit.MINUTES.toMillis(
    duration.toLong()
))
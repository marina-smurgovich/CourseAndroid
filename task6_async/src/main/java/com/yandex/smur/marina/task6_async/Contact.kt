package com.yandex.smur.marina.task6_async
import java.io.Serializable


data class Contact(
       val id: Double = Math.random(),
        val name: String,
        val info: String,
        val image: Int) : Serializable

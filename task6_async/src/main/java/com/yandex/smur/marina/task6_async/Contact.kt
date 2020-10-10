package com.yandex.smur.marina.task6_async
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Contact(
        @PrimaryKey val id: Double = Math.random(),
        val name: String,
        val info: String,
        val image: Int) : Serializable

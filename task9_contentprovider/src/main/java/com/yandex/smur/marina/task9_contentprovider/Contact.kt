package com.yandex.smur.marina.task9_contentprovider

import java.io.Serializable

data class Contact(
        val id: Double = Math.random(),
        val name: String,
        val info: String,
        val image: Int) : Serializable

package com.yandex.smur.marina.task7_broadcastreceiver_service

enum class StorageType (private val type : String) {
    INTERNAL("INTERNAL"),
    EXTERNAL("EXTERNAL");

    override fun toString(): String = type

}
package com.yandex.smur.marina.task6_async.async

interface DBListener<T> {
    fun onDataReceived(data: T)
}
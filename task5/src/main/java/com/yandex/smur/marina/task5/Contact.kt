package com.yandex.smur.marina.task5

import com.yandex.smur.marina.hw3.Contact
import java.io.Serializable

class Contact(
        val name: String,
        val info: String,
        val image: Int) : Serializable {


//    override fun equals(obj: Any?): Boolean {
//        if (obj is Contact) {
//            val contactObj = obj
//            return ( id == contactObj.id && name == contactObj.name &&
//                    info == contactObj.telOrEmail
//                    && image == contactObj.image)
//        }
//        return false
//    }

}
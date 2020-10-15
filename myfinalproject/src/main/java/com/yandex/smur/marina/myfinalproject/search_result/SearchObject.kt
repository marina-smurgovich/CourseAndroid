package com.yandex.smur.marina.myfinalproject.search_result

import java.io.Serializable

class SearchObject(
        var searchingByKeyword: String,
        var healthLabels: ArrayList<String>?,
        val dietLabels: ArrayList<String>?,
        val calories: String,
        val cookingTime: String ) : Serializable {

}
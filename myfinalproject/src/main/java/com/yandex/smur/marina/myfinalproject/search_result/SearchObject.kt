package com.yandex.smur.marina.myfinalproject.search_result

import java.io.Serializable

class SearchObject(
        val searchingByKeyword : String,
        val healthLabels : String,
        val dietLabels : String,
        val calories : String,
        val nutrients : String ) : Serializable {

}
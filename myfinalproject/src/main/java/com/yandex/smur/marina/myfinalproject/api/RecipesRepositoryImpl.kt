package com.yandex.smur.marina.myfinalproject.api

import android.util.Log
import com.yandex.smur.marina.myfinalproject.search_result.SearchObject
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import okhttp3.Request.Builder
import java.lang.NullPointerException

private const val API_KEY = "e6747edf66aad2ed5672237e65ab4b94"
private const val API_ID = "03a0780b"
//val url = "https://test-es.edamam.com/search?q=apple$&app_id=03a0780b&app_key=e6747edf66aad2ed5672237e65ab4b94&health=alcohol-free"

class RecipesRepositoryImpl(private val okHttpClient: OkHttpClient,
                            private val recipesDataModelMapper: (String) -> List<RecipeDataModel>
) : RecipesRepository {

    override fun getRecipes(searchObject: SearchObject): Single<List<RecipeDataModel>>{
        val url = initUrl(searchObject)
        val request = Builder().url(url).build()
        return Single.create<String> {emitter ->
            okHttpClient.newCall(request).execute().use {response ->
                if (!response.isSuccessful) emitter.onError(Throwable("Server error code: ${response.code}"))
                if (response.body == null) emitter.onError(NullPointerException("Body is null"))
                emitter.onSuccess((response.body as ResponseBody).string())
            }
        }.subscribeOn(Schedulers.io())
                .map { jsonData -> recipesDataModelMapper(jsonData) }

        }

    private fun initUrl (searchObject: SearchObject) : String {
        var url : String? = null
        if (searchObject.searchingByKeyword != " " && searchObject.healthLabels == null &&
                searchObject.dietLabels == null && searchObject.calories == " " &&
                searchObject.cookingTime == " ") {
            url = "https://test-es.edamam.com/search?q=${searchObject.searchingByKeyword}&app_id=${API_ID}&app_key=${API_KEY}"
        }
        if (searchObject.searchingByKeyword != " " && searchObject.healthLabels != null &&
                searchObject.dietLabels == null && searchObject.calories == " " &&
                searchObject.cookingTime == " ") {
            url = "https://test-es.edamam.com/search?q=${searchObject.searchingByKeyword}&app_id=${API_ID}&app_key=${API_KEY}"
            for (item in searchObject.healthLabels!!) {
                val str = "&health=${searchObject.healthLabels!!.indexOf(item)}"
                url = url + str
            }
        }
        return url!!
    }

    }

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

class RecipesRepositoryImpl(
        private val okHttpClient: OkHttpClient,
        private val recipesDataModelMapper: (String) -> List<RecipeDataModel>,
) : RecipesRepository {

    override fun getRecipes(searchObject: SearchObject): Single<List<RecipeDataModel>> {
        val url = initUrl1(searchObject)
        val request = Builder().url(url).build()
        return Single.create<String> { emitter ->
            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) emitter.onError(Throwable("Server error code: ${response.code}"))
                if (response.body == null) emitter.onError(NullPointerException("Body is null"))
                emitter.onSuccess((response.body as ResponseBody).string())
            }
        }.subscribeOn(Schedulers.io())
                .map { jsonData -> recipesDataModelMapper(jsonData) }

    }

    private fun initUrl1(searchObject: SearchObject) : String{
        var url = "https://api.edamam.com/search?q=${searchObject.searchingByKeyword}&app_id=${API_ID}&app_key=${API_KEY}&from=0&to=50"
        if (searchObject.healthLabels != null) {
            for (item in 0 until searchObject.healthLabels!!.size) {
                val str = "&health=${searchObject.healthLabels!!.get(item)}"
                url = url + str
                Log.d("ActivitySearchResult", url)
            }
        }
        if (searchObject.dietLabels != null) {
            for (item in 0 until searchObject.dietLabels.size) {
                val str = "&diet=${searchObject.dietLabels.get(item)}"
                url = url + str
                Log.d("ActivitySearchResult", url)
            }
        }
        if (searchObject.calories != " ") {
            val str = "&calories=0-${searchObject.calories}"
            url = url + str
            Log.d("ActivitySearchResult", url)
        }
        if (searchObject.cookingTime != " ") {
            val strTime = "&time=${searchObject.cookingTime}% 2B"
            url = url + strTime
            Log.d("ActivitySearchResult", url)
        }
        return url
    }

}

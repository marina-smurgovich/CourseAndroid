package com.yandex.smur.marina.myfinalproject.api

import com.yandex.smur.marina.myfinalproject.RecipeDataModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleOnSubscribe
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import okhttp3.Request.Builder
import java.io.IOException
import java.lang.NullPointerException

private const val API_KEY = "e6747edf66aad2ed5672237e65ab4b94"
private const val API_ID = "03a0780b"
//val url = "https://test-es.edamam.com/search?q=$chiken$&app_id=$03a0780b&app_key=$e6747edf66aad2ed5672237e65ab4b94&from=0&to=20&calories=591-722&health=alcohol-free"

class RecipesRepositoryImpl (private val okHttpClient : OkHttpClient,
        private val recipesDataModelMapper: (String) -> List<RecipeDataModel>) : RecipesRepository {

    override fun getRecipes(searchingByKeyword: String): Single<List<RecipeDataModel>> {
        val url = "https://test-es.edamam.com/search?q=${searchingByKeyword}$&app_id=${API_ID}&app_key=${API_KEY}"
        val request = Builder().url(url).build()
        return Single.create<String> {emitter ->
            okHttpClient.newCall(request).execute().use {response ->
                if (!response.isSuccessful) emitter.onError(Throwable(response.code.toString()))
                if (response.body == null) emitter.onError(NullPointerException("Body is null"))
                emitter.onSuccess(response.body.toString())
            }
        }.subscribeOn(Schedulers.io())
                .map { jsonData -> recipesDataModelMapper(jsonData)}

        }

    }

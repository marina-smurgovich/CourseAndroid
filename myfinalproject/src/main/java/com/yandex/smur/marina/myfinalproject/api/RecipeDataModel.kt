package com.yandex.smur.marina.myfinalproject.api

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class RecipeDataModel(
        @PrimaryKey
        val id: Double = Math.random(),

        @ColumnInfo
        val urlImage: String,

        @ColumnInfo
        val title: String,

        @ColumnInfo
        val numberOfServings: Double,

        @ColumnInfo
        val energy: Double,

        @ColumnInfo
        val protein: Double,

        @ColumnInfo
        val fat: Double,

        @ColumnInfo
        val carbs: Double,

        @ColumnInfo
        val listOfIngredients: MutableList<String>,

        @ColumnInfo
        val urlRecipe: String
)
    : Serializable {

}

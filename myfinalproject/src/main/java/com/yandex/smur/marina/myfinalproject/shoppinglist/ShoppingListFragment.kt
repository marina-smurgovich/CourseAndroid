package com.yandex.smur.marina.myfinalproject.shoppinglist

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yandex.smur.marina.myfinalproject.R
import com.yandex.smur.marina.myfinalproject.recipe_activity.Ingredient
import com.yandex.smur.marina.myfinalproject.sqlite_database.DBHelperSelectedIngredients
import kotlinx.android.synthetic.main.fragment_shopping_list.*


class ShoppingListFragment : Fragment() {

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var databaseSelectedIngredients: SQLiteDatabase
    private lateinit var contentValues: ContentValues
    private lateinit var dbHelperSelectedIngredients: DBHelperSelectedIngredients
    private lateinit var adapter: ShoppingListAdapter
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_shopping_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbHelperSelectedIngredients = DBHelperSelectedIngredients(requireActivity())
        databaseSelectedIngredients = dbHelperSelectedIngredients.writableDatabase
        contentValues = ContentValues()

        val shoppingList: MutableList<Ingredient> = getSelectedIngredientsFromDB()

        recyclerViewShoppingList.apply {
            adapter = ShoppingListAdapter(shoppingList, object : ShoppingListAdapter.OnclickListenerAdapter {
                override fun onItemClick(ingredient: Ingredient) {
                    updateShoppingListAfterRemoveIngredient(ingredient)
                    deleteIngredient(ingredient)
                }
            })
            viewManager = LinearLayoutManager(activity)
        }

        buttonRemoveAllInShopingList.setOnClickListener {
            deleteAllListInShoppingList()
            updateAfterRemoveAllIngredients()
        }
    }

    private fun getSelectedIngredientsFromDB(): MutableList<Ingredient> {
        val list: MutableList<Ingredient> = mutableListOf()
        val cursor: Cursor = databaseSelectedIngredients.rawQuery("SELECT * FROM selected_ingredients", null)

        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id: Double = cursor.getDouble(0)
                val ingredientstr: String = cursor.getString(1)
                val ingredient = Ingredient(id, ingredientstr)

                list.add(ingredient)
            }
        }
        cursor.close()
        return list
    }

    private fun deleteAllListInShoppingList() {
        databaseSelectedIngredients.delete("selected_ingredients", null, null)
    }

    private fun deleteIngredient(ingredient: Ingredient) {
        val id = ingredient.id
        databaseSelectedIngredients.delete("selected_ingredients", "id = " + id, null)
    }

    private fun updateShoppingListAfterRemoveIngredient(ingredient: Ingredient) {
        adapter = recyclerViewShoppingList.adapter as ShoppingListAdapter
        adapter.deleteIngredientR(ingredient)
    }

    private fun updateAfterRemoveAllIngredients() {
        adapter = recyclerViewShoppingList.adapter as ShoppingListAdapter
        adapter.deleteAll()
    }
}
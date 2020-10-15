package com.yandex.smur.marina.myfinalproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yandex.smur.marina.myfinalproject.dialogs.*
import com.yandex.smur.marina.myfinalproject.search_result.ActivitySearchResult
import com.yandex.smur.marina.myfinalproject.search_result.SearchObject
import kotlinx.android.synthetic.main.fragment_search_forrecipes.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val REQUEST_CODE_DIALOG_SEARCH_BY_KEY_WORD = 10
private const val REQUEST_CODE_DIALOG_HEALTH_LABELS = 11
private const val REQUEST_CODE_DIALOG_DIET_LABELS = 12
private const val REQUEST_CODE_DIALOG_CALORIES = 13
private const val REQUEST_CODE_DIALOG_COOKING_TIME = 13

/**
 * A simple [Fragment] subclass.
 * Use the [SearchForrecipesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchForrecipesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val param1 by lazy { arguments?.getString(ARG_PARAM1) }
    private val param2 by lazy { arguments?.getString(ARG_PARAM2) }

    var searchByKeyword: String = " "
    var arrayHealthLabels: ArrayList<String>? = null
    var dietLabels: ArrayList<String>? = null
    var calories: String = " "
    var cookingTime: String = " "

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_search_forrecipes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent(activity, ActivitySearchResult::class.java)
                val searchObject = SearchObject(searchByKeyword, arrayHealthLabels, dietLabels, calories,  cookingTime)
                intent.putExtra("mySearchObject", searchObject)
                startActivity(intent)
            }
        })

        buttonSearchingByKeyword.setOnClickListener {
            val dialogSearchingByKeyword = DialogSearchingByKeyword().newInstance(102)
            dialogSearchingByKeyword.setTargetFragment(this, REQUEST_CODE_DIALOG_SEARCH_BY_KEY_WORD)
            fragmentManager?.let { it1 -> dialogSearchingByKeyword.show(it1, "SearchingByKeyword") }
        }

        buttonHealthLabels.setOnClickListener {
            val dialogHealthLabels = DialogHealthLabels().newInstance(103)
            dialogHealthLabels.setTargetFragment(this, REQUEST_CODE_DIALOG_HEALTH_LABELS)
            fragmentManager?.let { it1 -> dialogHealthLabels.show(it1, "HealthLabels") }
        }

        buttonDietLabels.setOnClickListener {
            val dialogDietLabels = DialogDietLabels().newInstance(104)
            dialogDietLabels.setTargetFragment(this, REQUEST_CODE_DIALOG_DIET_LABELS)
            fragmentManager?.let { it1 -> dialogDietLabels.show(it1, "DietLabels") }
        }
        buttonCalories.setOnClickListener {
            val dialogCalories = DialogCalories().newInstance(105)
            dialogCalories.setTargetFragment(this, REQUEST_CODE_DIALOG_CALORIES)
            fragmentManager?.let { it1 -> dialogCalories.show(it1, "Calories") }
        }

        buttonCookingTime.setOnClickListener {
            val dialogCookingTime = DialogCookingTime().newInstance(106)
            dialogCookingTime.setTargetFragment(this, REQUEST_CODE_DIALOG_COOKING_TIME)
            fragmentManager?.let { it1 -> dialogCookingTime.show(it1, "CookingTime") }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE_DIALOG_SEARCH_BY_KEY_WORD ->
                if (resultCode == Activity.RESULT_OK) {
                    searchByKeyword = data?.getStringExtra("editViewText") ?: " "
                }
            REQUEST_CODE_DIALOG_HEALTH_LABELS ->
                if (resultCode == Activity.RESULT_OK) {
                    arrayHealthLabels = data?.getStringArrayListExtra("DialogHealthLabels") as ArrayList<String>
                            ?: null
                    Log.d("ActivitySearchResult", arrayHealthLabels.toString())
                }
            REQUEST_CODE_DIALOG_DIET_LABELS ->
                if (resultCode == Activity.RESULT_OK) {
                    dietLabels = data?.getStringArrayListExtra("DialogDietLabels") as ArrayList<String>
                            ?: null
                    Log.d("ActivitySearchResult", dietLabels.toString())
                }
            REQUEST_CODE_DIALOG_CALORIES ->
                if (resultCode == Activity.RESULT_OK) {
                    calories = data?.getStringExtra("DialogCalories") ?: " "
                    Log.d("ActivitySearchResult", calories.toString())
                }
            REQUEST_CODE_DIALOG_COOKING_TIME ->
                if (resultCode == Activity.RESULT_OK) {
                cookingTime = data?.getStringExtra("DialogCalories") ?: " "
                Log.d("ActivitySearchResult", cookingTime.toString())
            }

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchForrecipesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SearchForrecipesFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
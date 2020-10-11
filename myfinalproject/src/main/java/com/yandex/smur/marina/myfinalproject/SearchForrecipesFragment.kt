package com.yandex.smur.marina.myfinalproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yandex.smur.marina.myfinalproject.dialogs.DialogDietLabels
import com.yandex.smur.marina.myfinalproject.dialogs.DialogHealthLabels
import com.yandex.smur.marina.myfinalproject.dialogs.DialogSearchingByKeyword
import com.yandex.smur.marina.myfinalproject.search_result.ActivitySearchResult
import com.yandex.smur.marina.myfinalproject.search_result.SearchObject
import kotlinx.android.synthetic.main.fragment_search_forrecipes.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val REQUEST_CODE_DIALOG_SEARCH_BY_KEY_WORD = 10

/**
 * A simple [Fragment] subclass.
 * Use the [SearchForrecipesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchForrecipesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val param1 by lazy { arguments?.getString(ARG_PARAM1) }
    private val param2 by lazy { arguments?.getString(ARG_PARAM2) }

    //!!!!!!!!!!!!!!!!!!!!!!!
    lateinit var searchByKeyword: String

//    override fun onDialogPositiveClick(string: String) {
//        srtFr = string
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_search_forrecipes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent(activity, ActivitySearchResult::class.java)
                val searchObject = SearchObject(searchByKeyword, "", "", "", "")
                intent.putExtra("mySearchObject", searchObject)
                startActivity(intent)
            }

        })

//        searchButton.setOnClickListener {
//            startActivity(Intent(activity, ActivitySearchResult::class.java)) }

        buttonSearchingByKeyword.setOnClickListener {
            val dialogSearchingByKeyword = DialogSearchingByKeyword().newInstance(102)
            dialogSearchingByKeyword.setTargetFragment(this, REQUEST_CODE_DIALOG_SEARCH_BY_KEY_WORD)
            fragmentManager?.let { it1 -> dialogSearchingByKeyword.show(it1, "SearchingByKeyword") }

//                    .apply {
//                setTargetFragment(this, REQUEST_CODE_DIALOG_SEARCH_BY_KEY_WORD)
//            }
//            activity?.supportFragmentManager?.let {dialogSearchingByKeyword.show(it, "SearchingByKeyword") }
        }

        buttonHealthLabels.setOnClickListener {
            activity?.supportFragmentManager?.let { DialogHealthLabels().show(it, "HealthLabels") }
        }
        buttonDietLabels.setOnClickListener {
            activity?.supportFragmentManager?.let { DialogDietLabels().show(it, "DietLabels") }
        }
        buttonCalories.setOnClickListener { TODO("Not yet implemented") }
        buttonNutrients.setOnClickListener { TODO("Not yet implemented") }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE_DIALOG_SEARCH_BY_KEY_WORD ->
                if (resultCode == Activity.RESULT_OK) {
                    searchByKeyword = data?.getStringExtra("editViewText") ?: " "
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
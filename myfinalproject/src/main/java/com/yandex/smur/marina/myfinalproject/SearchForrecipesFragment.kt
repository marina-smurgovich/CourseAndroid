package com.yandex.smur.marina.myfinalproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.yandex.smur.marina.myfinalproject.dialogs.DialogDietLabels
import com.yandex.smur.marina.myfinalproject.dialogs.DialogHealthLabels
import com.yandex.smur.marina.myfinalproject.dialogs.DialogSearchingByKeyword
import com.yandex.smur.marina.myfinalproject.search_result.ActivitySearchResult
import kotlinx.android.synthetic.main.fragment_search_forrecipes.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchForrecipesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchForrecipesFragment : Fragment()
        , DialogSearchingByKeyword.DialogSearchingByKeywordListener
{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    //!!!!!!!!!!!!!!!!!!!!!!!
    var srtFr: String? = null

    //!!!!!!!!!!!!!!!!!!!!!!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    //!!!!!!!!!!!!!!!!!!!!!!!
    override fun onDialogPositiveClick(string: String){
        srtFr = string
    }
    //!!!!!!!!!!!!!!!!!!!!!!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentLayout = inflater.inflate(R.layout.fragment_search_forrecipes, container, false)

        val navController = NavHostFragment.findNavController(this)

        fragmentLayout.searchButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                startActivity(Intent(activity, ActivitySearchResult::class.java))
            }

        })
//!!!!!!!!!!!!!!!!!!!!!!!
        fragmentLayout.buttonSearchingByKeyword.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val dialogSearchingByKeyword = DialogSearchingByKeyword()
                dialogSearchingByKeyword.setTargetFragment(this@SearchForrecipesFragment, 1)
                dialogSearchingByKeyword.show(activity!!.supportFragmentManager, "SearchingByKeyword")

            }

        })

        fragmentLayout.textViewTest.text = srtFr
//!!!!!!!!!!!!!!!!!!!!!!!!!







        fragmentLayout.buttonHealthLabels.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val dialogHealthLabels : DialogHealthLabels = DialogHealthLabels()
                dialogHealthLabels.show(activity!!.supportFragmentManager, "HealthLabels")
            }
        })

        fragmentLayout.buttonDietLabels.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val dialogDietLabels : DialogDietLabels = DialogDietLabels()
                dialogDietLabels.show(activity!!.supportFragmentManager, "DietLabels")
            }
        })

        fragmentLayout.buttonCalories.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                TODO("Not yet implemented")
            }
        })

        fragmentLayout.buttonNutrients.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                TODO("Not yet implemented")
            }
        })


        return fragmentLayout
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

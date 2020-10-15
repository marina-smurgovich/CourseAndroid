package com.yandex.smur.marina.myfinalproject.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.yandex.smur.marina.myfinalproject.R


class DialogHealthLabels : DialogFragment() {

    fun newInstance(num: Int): DialogHealthLabels {
        val dialogFragment = DialogHealthLabels()
        val bundle = Bundle()
        bundle.putInt("num", num)
        dialogFragment.setArguments(bundle)
        return dialogFragment
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val array = initArray()
        val selectedList : ArrayList<String> = arrayListOf()
        val builder : AlertDialog.Builder = AlertDialog.Builder(activity)

        builder.setTitle(R.string.title_health_labels)
                .setMultiChoiceItems(array, null, object : DialogInterface.OnMultiChoiceClickListener {
                    override fun onClick(p0: DialogInterface?, which: Int, isChecked: Boolean) {
                        if (isChecked) {
                            selectedList.add(array.get(which))
                        } else if (selectedList.contains(which)) {
                            selectedList.remove(which.toString())
                        }
                    }
                })
                .setPositiveButton(R.string.add_keyword, object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        val intent = Intent()
                        intent.putStringArrayListExtra("DialogHealthLabels", selectedList)
                        targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
                    }
                })
                .setNegativeButton(R.string.cancel, object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                    }
                })
        return builder.create()

    }

    private fun initArray() : Array<String> {
        val array = arrayOf("Alcohol-free", "Celery-free", "Crustacean-free", "Dairy-free",
        "Egg-free", "Fish-free", "Gluten-free", "Keto", "Kidney friendly", "Kosher", "Low potassium",
        "Lupine-free", "Mustard-free", "No oil added", "No-sugar", "Paleo", "Peanut-free", "Pescatarian",
        "Pork-free", "Red meat-free", "Sesame-free", "Shellfish-free", "Soy-free", "Sugar-conscious",
        "Tree-Nut-free", "Vegan", "Vegetarian", "Wheat-free")
        return array
    }
}



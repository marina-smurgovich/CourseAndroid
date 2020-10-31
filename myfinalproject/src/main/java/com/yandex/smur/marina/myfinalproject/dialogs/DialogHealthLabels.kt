package com.yandex.smur.marina.myfinalproject.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
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
        val selectedList: ArrayList<String> = arrayListOf()
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        builder.setTitle(R.string.title_health_labels)
                .setMultiChoiceItems(array, null) { p0, which, isChecked ->
                    if (isChecked) {
                        selectedList.add(array.get(which))
                    } else if (selectedList.contains(which)) {
                        selectedList.remove(which.toString())
                    }
                }
                .setPositiveButton(R.string.add_keyword) { p0, p1 ->
                    val intent = Intent()
                    intent.putStringArrayListExtra("DialogHealthLabels", selectedList)
                    targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
                }
                .setNegativeButton(R.string.cancel) { p0, p1 -> }
        return builder.create()

    }

    private fun initArray(): Array<String> {
        val array = arrayOf("alcohol-free", "peanut-free", "sugar-conscious",
                "vegan", "vegetarian")
        return array
    }
}



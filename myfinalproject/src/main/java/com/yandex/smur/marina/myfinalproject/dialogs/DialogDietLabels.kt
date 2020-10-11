package com.yandex.smur.marina.myfinalproject.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.yandex.smur.marina.myfinalproject.R

class DialogDietLabels : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val selectedList : MutableList<String> = mutableListOf()
        val builder : AlertDialog.Builder = AlertDialog.Builder(activity)

        builder.setTitle(R.string.title_diet_labels)
                .setMultiChoiceItems(R.array.list_diet_labels, null, object : DialogInterface.OnMultiChoiceClickListener {
                    override fun onClick(p0: DialogInterface?, which: Int, isChecked: Boolean) {
                        if (isChecked) {
                            selectedList.add(which.toString())
                        } else if (selectedList.contains(which)) {
                            selectedList.remove(which.toString())
                        }
                    }
                })
                .setPositiveButton(R.string.add_keyword, object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        //добавляю слово в объект поиска
                    }
                })
                .setNegativeButton(R.string.cancel, object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        //добавляю слово в объект поиска
                    }
                })
        return builder.create()
    }


}
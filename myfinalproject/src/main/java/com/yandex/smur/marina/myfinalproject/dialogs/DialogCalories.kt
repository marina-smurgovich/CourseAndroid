package com.yandex.smur.marina.myfinalproject.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.yandex.smur.marina.myfinalproject.R
import kotlinx.android.synthetic.main.dialog_calories.view.*

class DialogCalories : DialogFragment() {
    fun newInstance(num: Int): DialogCalories {
        val dialogFragment = DialogCalories()
        val bundle = Bundle()
        bundle.putInt("num", num)
        dialogFragment.setArguments(bundle)
        return dialogFragment
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater: LayoutInflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_calories, null)
        return AlertDialog.Builder(activity)
                .setTitle(R.string.calories_item)
                .setMessage(R.string.enter_calories)
                .setView(view)

                .setPositiveButton(R.string.add_keyword) { dialog, whichButton ->
                    val input = view.editTextCalories.text.toString()
                    val intent = Intent()
                    intent.putExtra("DialogCalories", input)
                    targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
                }

                .setNegativeButton(R.string.cancel) { p0, p1 -> }
                .create();
    }
}

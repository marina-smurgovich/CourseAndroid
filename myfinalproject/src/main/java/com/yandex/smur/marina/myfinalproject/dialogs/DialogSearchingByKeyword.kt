package com.yandex.smur.marina.myfinalproject.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.yandex.smur.marina.myfinalproject.R
import kotlinx.android.synthetic.main.dialog_searching_by_keyword.*
import kotlinx.android.synthetic.main.dialog_searching_by_keyword.view.*

class DialogSearchingByKeyword() : DialogFragment() {



//    public interface DialogSearchingByKeywordListener {
//       fun onDialogPositiveClick(string: String)
//    }
//
//    public lateinit var dialogSearchingByKeywordListener: DialogSearchingByKeywordListener
//private lateinit var ed : EditText

    fun newInstance(num: Int): DialogSearchingByKeyword {
        val dialogFragment = DialogSearchingByKeyword()
        val bundle = Bundle()
        bundle.putInt("num", num)
        dialogFragment.setArguments(bundle)
        return dialogFragment
    }


    override fun onCreateDialog(savedInstanceState: Bundle?) : Dialog {
        val inflater : LayoutInflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.dialog_searching_by_keyword, null)
        return AlertDialog.Builder(activity)
                .setTitle(R.string.title_searching_by_keyword)
                .setMessage(R.string.text_searching_by_keyword)
                .setView(view)

                .setPositiveButton(R.string.add_keyword, object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, whichButton: Int) {
                        val input = view.editTextSearchingByKeyword.text.toString()
                        val intent = Intent()
                        intent.putExtra("editViewText", input)
                      targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
                    }
                })

                .setNegativeButton(R.string.cancel, object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                    }
                })
                .create();
    }

}
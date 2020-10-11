package com.yandex.smur.marina.myfinalproject.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.yandex.smur.marina.myfinalproject.R

class DialogSearchingByKeyword() : DialogFragment() {



    public interface DialogSearchingByKeywordListener {
       fun onDialogPositiveClick(string: String)
    }

    public lateinit var dialogSearchingByKeywordListener: DialogSearchingByKeywordListener

    private var ed : EditText?  = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        ed = view!!.findViewById(R.id.editTextSearchingByKeyword)
        val builder : AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater : LayoutInflater = activity!!.layoutInflater

        builder.setTitle(R.string.title_searching_by_keyword)
                .setMessage(R.string.text_searching_by_keyword)
                .setView(inflater.inflate(R.layout.dialog_searching_by_keyword, null))

                .setPositiveButton(R.string.add_keyword, object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        val input = ed!!.text.toString()
                        dialogSearchingByKeywordListener.onDialogPositiveClick(input)


                    }
                })

                .setNegativeButton(R.string.cancel, object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                    }
                })



        return builder.create();
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        dialogSearchingByKeywordListener = (targetFragment as DialogSearchingByKeywordListener?)!!
    }
}
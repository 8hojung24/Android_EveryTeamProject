//package com.example.everyteamproject

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_role_dialog.*
import com.example.everyteamproject.R

class CustomDialog(context: Context)
{
    private val dialog = Dialog(context)
    private lateinit var onClickListener: OnDialogClickListener

    fun setOnClickListener(listener: OnDialogClickListener)
    {
        onClickListener = listener
    }

    fun showDialog()
    {
        dialog.setContentView(R.layout.activity_role_dialog) //여기확인-Okay
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val role_edit = dialog.findViewById<EditText>(R.id.role_edit)

        dialog.cancel_button.setOnClickListener {
            dialog.dismiss()
        }

        dialog.finish_button.setOnClickListener {
            onClickListener.onClicked(role_edit.text.toString())
            dialog.dismiss()
        }

    }

    interface OnDialogClickListener
    {
        fun onClicked(name: String)
    }
}
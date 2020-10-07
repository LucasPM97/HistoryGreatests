package com.lucas.historygreatests.ui.components.views

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.lucas.historygreatests.R

object LoadingFullDialog {

    private lateinit var dialog: Dialog

    fun showLoadingDialog(context: Context){
        if (dialog != null && dialog.isShowing) {
            return
        }

        dialog = Dialog(context).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.dialog_loading)
            window?.let {
                it.setBackgroundDrawableResource(android.R.color.transparent)
            }
            show()
        }
    }

    fun dissmisLoadingDialog(){
        dialog?.let {
            if (dialog.isShowing()) dialog.dismiss()
        }
    }


}
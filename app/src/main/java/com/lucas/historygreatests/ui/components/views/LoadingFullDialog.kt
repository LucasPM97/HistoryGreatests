package com.lucas.historygreatests.ui.components.views

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.lucas.historygreatests.R

object LoadingFullDialog {

    private lateinit var dialog: Dialog

    fun showLoadingDialog(context: Context){
        if (dialog != null && dialog?.isShowing) {
            return
        }

        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_loading)
        dialog.window?.let {
            it.setBackgroundDrawableResource(android.R.color.transparent)
        }
        dialog.show()
    }

    fun dissmisLoadingDialog(){
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


}
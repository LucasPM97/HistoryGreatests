package com.lucas.historygreatests.ui.components.views

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.lucas.historygreatests.R

class LoadingFullDialog (context: Context) {

    private var dialog:Dialog = Dialog(context).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        setContentView(R.layout.dialog_loading)
        window?.let {
            it.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    fun show(){
        dialog.show()
    }

    fun dismiss(){
        if (dialog.isShowing()) dialog.dismiss()
    }

}
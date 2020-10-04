package com.lucas.historygreatests.utils

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.lucas.historygreatests.ui.components.views.LoadingFullDialog

fun Fragment.showLoading(context: Context) {
    LoadingFullDialog.showLoadingDialog(context)
}

fun Fragment.dissmisLoading() {
    LoadingFullDialog.dissmisLoadingDialog()
}

package com.lucas.historygreatests.utils.extensions

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

//ImageView
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    url?.let {
        view.loadFromUrl(url)
    }
}

//View
@BindingAdapter("backgroundColor")
fun setBackgroundColorFromHex(view: View, hxColor: String) {
    view.setBackgroundColor(Color.parseColor(hxColor))
}

@BindingAdapter("clipToOutline")
fun setClipToOutline(view: View, enabled: Boolean) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        view.clipToOutline = enabled
    }
}


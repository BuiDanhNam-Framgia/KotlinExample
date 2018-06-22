@file:JvmName("ExtensionNew")

package framgia.vn.examplekotlin

import android.app.Activity
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ViewGroup.inflate(@LayoutRes res: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(res, this, attachToRoot)
}

fun ImageView.loadImage(res: String? = R.mipmap.ic_launcher.toString()){
     Picasso.with(context).load(res).into(this)
}

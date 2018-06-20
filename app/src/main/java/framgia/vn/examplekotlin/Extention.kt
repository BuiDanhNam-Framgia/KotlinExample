@file:JvmName("ExtensionNew")

package framgia.vn.examplekotlin

import android.app.Activity
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(@LayoutRes res: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(res, this, attachToRoot)
}

private fun View.snack() {

}

inline fun <reified T : View> Activity.find(@IdRes id: Int): T = findViewById(id) as T

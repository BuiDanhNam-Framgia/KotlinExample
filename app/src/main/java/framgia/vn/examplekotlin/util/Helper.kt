package framgia.vn.examplekotlin.util

import android.content.Context
import android.net.ConnectivityManager

public fun checkInternet(context: Context): Boolean {
    var connect = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var infor = connect.activeNetworkInfo
    return  (infor != null && infor.isConnectedOrConnecting)
}

interface test {
    fun check(){

    }

}

class test2:test{
    override fun check() {
        super.check()

    }
}
package framgia.vn.examplekotlin.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor(var baseURL: String) {
    private val redditAPI: RedditAPI? = null
     companion object {
        fun getRetrofitAPI(baseURL: String): RedditAPI? {
            var interpolator = Interceptor { chain ->
                val request: Request? = chain?.request()?.newBuilder()?.addHeader("", "")?.build()
                chain?.proceed(request)

            }
            var okhttp = OkHttpClient.Builder()
//                    .addNetworkInterceptor(interpolator)
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okhttp)
                    .build()

            return retrofit?.create(RedditAPI::class.java)
        }
    }

}

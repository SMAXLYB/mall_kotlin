package cn.smaxlyb.mall.ibrary.net

import cn.smaxlyb.mall.ibrary.global.GlobalKeys
import cn.smaxlyb.mall.ibrary.global.Mall
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


// 职责:创建RestService
object RestCreator {

    private object OkHttpHolder{
        private const val TIME_OUT = 60

        internal val HTTP_CLIENT = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request =chain.request().newBuilder()
                    .build()
                chain.proceed(request)
            }
            .connectTimeout(TIME_OUT.toLong(),TimeUnit.SECONDS)
            .build()
    }

    private object RetrofitHolder {
        private val BASE_URL = Mall.getConfiguration<String>(GlobalKeys.API_HOST)

        internal val RETROFIT_CLIENT = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpHolder.HTTP_CLIENT)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    private object RestServiceHolder{
        internal val REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT
            .create(RestService::class.java)
    }

    val restService: RestService = RestServiceHolder.REST_SERVICE
}
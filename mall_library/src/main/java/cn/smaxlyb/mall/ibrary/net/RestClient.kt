package cn.smaxlyb.mall.ibrary.net

import android.content.Context
import cn.smaxlyb.mall.ibrary.loader.LoaderStyle
import cn.smaxlyb.mall.ibrary.loader.MallLoader
import cn.smaxlyb.mall.ibrary.net.callback.*
import retrofit2.Call
import retrofit2.Callback
import java.util.*

// 职责:网络请求
class RestClient internal constructor(
    private val url: String?,
    private val iRequest: IRequest?,
    private val iComplete: IComplete?,
    private val iSuccess: ISuccess?,
    private val iFailure: IFailure?,
    private val iError: IError?,
    private val params: WeakHashMap<String, Any>?,
    private val context: Context?,
    private val style: LoaderStyle?
) {
    companion object {
        fun builder() = RestClientBuilder()
    }

    private fun request(httpMethod: HttpMethod) {
        val restService = RestCreator.restService
        val call: Call<String>?

        // 请求开始回调
        iRequest?.onRequestStart()

        // 弹出dialog
        context?.let { MallLoader.showLoadingDialog(it,style!!) }

        // 获取请求方式
        call = when (httpMethod) {
            HttpMethod.POST -> restService.post(url, params)
            HttpMethod.GET -> restService.get(url, params)
            HttpMethod.PUT -> restService.put(url, params)
            HttpMethod.DELETE -> restService.delete(url, params)
            HttpMethod.UPLOAD -> TODO()
        }

        // 开始请求
        call.enqueue(requestCallbacks)
    }

    private val requestCallbacks: Callback<String> =
        RequestCallbacks(
            iRequest,
            iComplete,
            iSuccess,
            iError,
            iFailure,
            context
        )

    fun get() {
        request(HttpMethod.GET)
    }

    fun post() {
        request(HttpMethod.POST)
    }

    fun put() {
        request(HttpMethod.PUT)
    }

    fun delete() {
        request(HttpMethod.DELETE)
    }
}
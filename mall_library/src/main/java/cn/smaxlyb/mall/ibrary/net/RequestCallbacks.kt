package cn.smaxlyb.mall.ibrary.net

import android.content.Context
import cn.smaxlyb.mall.ibrary.loader.MallLoader
import cn.smaxlyb.mall.ibrary.net.callback.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 职责:回调
class RequestCallbacks(
    private val iRequest: IRequest?,
    private val iComplete: IComplete?,
    private val iSuccess: ISuccess?,
    private val iError: IError?,
    private val iFailure: IFailure?,
    private val context: Context?
) : Callback<String> {

    override fun onFailure(call: Call<String>, t: Throwable) {
        // 请求失败
        iFailure?.onFailure()
        iRequest?.onRequestEnd()

        if (context != null) {
            onRequestFinish()
        }
    }

    override fun onResponse(call: Call<String>, response: Response<String>) {
        // 如果请求成功
        if (response.isSuccessful) {
            if (call.isExecuted) {
                response.body()?.let { iSuccess?.onSuccess(it) }
            }
        } else {
            // 如果请求出错
            iError?.onError(response.code(), response.message())
        }

        if (context != null) {
            onRequestFinish()
        }
    }

    private fun onRequestFinish() {
        MallLoader.stopAllDialog()
    }

    companion object {
        const val TAG = "RequestCallbacks"
    }

}
package cn.smaxlyb.mall.ibrary.net

import android.content.Context
import cn.smaxlyb.mall.ibrary.loader.LoaderStyle
import cn.smaxlyb.mall.ibrary.net.callback.*
import java.util.*

// 职责: 负责辅助创建restClient
class RestClientBuilder(
    private var url: String? = null,
    private var iRequest: IRequest? = null,
    private var iComplete: IComplete? = null,
    private var iSuccess: ISuccess? = null,
    private var iFailure: IFailure? = null,
    private var iError: IError? = null,
    private var context: Context? = null,
    private var style: LoaderStyle? = null
) {
    private val mParams = WeakHashMap<String, Any>()

    fun setUrl(url: String): RestClientBuilder {
        this.url = url
        return this
    }

    fun setParams(key: String, value: Any): RestClientBuilder {
        mParams[key] = value
        return this
    }

    fun setParams(params: WeakHashMap<String, Any>): RestClientBuilder {
        mParams.putAll(params)
        return this
    }

    fun onRequest(iRequest: IRequest): RestClientBuilder {
        this.iRequest = iRequest
        return this
    }

    fun onComplete(iComplete: IComplete): RestClientBuilder {
        this.iComplete = iComplete
        return this
    }

    fun onSuccess(iSuccess: ISuccess): RestClientBuilder {
        this.iSuccess = iSuccess;
        return this
    }

    fun onFailure(iFailure: IFailure): RestClientBuilder {
        this.iFailure = iFailure
        return this
    }

    fun onError(iError: IError): RestClientBuilder {
        this.iError = iError
        return this
    }

    fun setLoader(
        context: Context,
        style: LoaderStyle = LoaderStyle.BallBeatIndicator
    ): RestClientBuilder {
        this.context = context
        this.style = style
        return this
    }

    fun build(): RestClient {
        return RestClient(url, iRequest, iComplete, iSuccess, iFailure, iError, mParams,context,style)
    }
}
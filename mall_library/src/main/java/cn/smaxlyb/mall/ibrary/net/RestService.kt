package cn.smaxlyb.mall.ibrary.net

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface RestService {
    @GET
    fun get(@Url url: String?, @QueryMap params: WeakHashMap<String, Any>?): Call<String>

    @POST
    @FormUrlEncoded
    fun post(@Url url: String?, @QueryMap params: WeakHashMap<String, Any>?): Call<String>

    @FormUrlEncoded
    @PUT
    fun put(@Url url: String?, @QueryMap params: WeakHashMap<String, Any>?): Call<String>

    @DELETE
    fun delete(@Url url: String?, @QueryMap params: WeakHashMap<String, Any>?): Call<String>

    @GET
    @Streaming
    fun download(@Url url: String?, @QueryMap params: WeakHashMap<String, Any>?): Call<ResponseBody>

    fun upload(@Url url: String?, @Part file: MultipartBody.Part): Call<String>
}
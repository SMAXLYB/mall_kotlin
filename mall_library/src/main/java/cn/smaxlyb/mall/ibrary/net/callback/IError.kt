package cn.smaxlyb.mall.ibrary.net.callback

// 请求出错调用
interface IError {
    fun onError(code: Int, msg: String)
}
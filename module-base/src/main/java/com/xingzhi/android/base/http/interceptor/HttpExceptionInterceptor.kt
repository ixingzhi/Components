package com.xingzhi.android.base.http.interceptor

import android.util.Log
import com.xingzhi.android.base.http.interceptor.utils.HttpInterceptorUtils
import com.xingzhi.android.BuildConfig
import okhttp3.*
import okhttp3.internal.http.promisesBody
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by xiedongdong on 2020/01/18
 */
class HttpExceptionInterceptor : Interceptor {
    private val sbHttpExceptionMessage = StringBuilder()

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        // 清空Message
        sbHttpExceptionMessage.setLength(0)
        // 请求日志
        logForRequest(request, chain.connection())
        //执行请求，计算请求时间
        val startNs = System.nanoTime()
        var response: Response
        try {
            response = chain.proceed(request)
            if (response.code == 200) {
                return response
            }
            log("response code：" + response.code)
            val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
            // 响应日志
            response = logForResponse(response, tookMs)
            // 上传异常信息
            uploadExceptionMessage()
        } catch (e: Exception) {
            httpException(e)
            throw e
        }
        return response
    }

    private fun logForRequest(request: Request, connection: Connection?) {
        val requestBody = request.body
        val hasRequestBody = requestBody != null
        val protocol = connection?.protocol() ?: Protocol.HTTP_1_1
        try {
            val requestStartMessage = "--> " + request.method + ' ' + request.url + ' ' + protocol
            httpLog(requestStartMessage)
            if (hasRequestBody) {
                // Request body headers are only present when installed as a network interceptor. Force
                // them to be included (when available) so there values are known.
                if (requestBody!!.contentType() != null) {
                    httpLog("\tContent-Type: " + requestBody.contentType())
                }
                if (requestBody.contentLength() != -1L) {
                    httpLog("\tContent-Length: " + requestBody.contentLength())
                }
            }
            val headers = request.headers
            var i = 0
            val count = headers.size
            while (i < count) {
                val name = headers.name(i)
                // Skip headers from the request body as they are explicitly logged above.
                if (!"Content-Type".equals(
                        name,
                        ignoreCase = true
                    ) && !"Content-Length".equals(name, ignoreCase = true)
                ) {
                    httpLog("\t" + name + ": " + headers.value(i))
                }
                i++
            }
            httpLog(" ")
            if (hasRequestBody) {
                if (HttpInterceptorUtils.isPlaintext(requestBody!!.contentType())) {
                    httpLog("\tbody:" + HttpInterceptorUtils.bodyToString(request))
                } else {
                    httpLog("\tbody: maybe [binary body], omitted!")
                }
            }
        } catch (e: Exception) {
            log(e.message)
        } finally {
            httpLog("--> END " + request.method)
        }
    }

    private fun logForResponse(response: Response, tookMs: Long): Response {
        val builder = response.newBuilder()
        val clone = builder.build()
        var responseBody = clone.body
        try {
            httpLog("<-- " + clone.code + ' ' + clone.message + ' ' + clone.request.url + " (" + tookMs + "ms）")
            val headers = clone.headers
            var i = 0
            val count = headers.size
            while (i < count) {
                httpLog("\t" + headers.name(i) + ": " + headers.value(i))
                i++
            }
            httpLog(" ")
            if (clone.promisesBody()) {
                if (responseBody == null) return response
                if (HttpInterceptorUtils.isPlaintext(responseBody.contentType())) {
                    val bytes = HttpInterceptorUtils.toByteArray(responseBody.byteStream())
                    val contentType = responseBody.contentType()
                    val body = String(bytes, HttpInterceptorUtils.getCharset(contentType))
                    httpLog("\tbody:$body")
                    responseBody = ResponseBody.create(responseBody.contentType(), bytes)
                    return response.newBuilder().body(responseBody).build()
                } else {
                    httpLog("\tbody: maybe [binary body], omitted!")
                }
            }
        } catch (e: Exception) {
            log(e.message)
        } finally {
            httpLog("<-- END HTTP")
        }
        return response
    }

    private fun httpException(e: Exception?) {
        httpLog("<-- HTTP FAILED: $e")
        if (e == null) {
            uploadExceptionMessage()
            return
        }
        if (e.toString().contains("java.io.IOException: Canceled") ||
            e.toString().contains("java.net.SocketException: Socket closed") ||
            e.toString().contains("No address associated with hostname")
        ) {
            sbHttpExceptionMessage.setLength(0)
        } else {
            uploadExceptionMessage()
        }
    }

    private fun httpLog(message: String) {
        sbHttpExceptionMessage.append(message)
        sbHttpExceptionMessage.append("\n")
    }

    private fun log(message: String?) {
        // 测试环境，打印网络日志
        if (BuildConfig.DEBUG) {
            Log.d(TAG, message ?: "")
        }
    }

    private fun uploadExceptionMessage() {
        val message = sbHttpExceptionMessage.toString()
        // TODO 上传错误日志
        log(message)
    }

    companion object {
        const val TAG = "HttpException"
    }

}
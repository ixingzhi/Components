package com.xingzhi.android.base.http.interceptor

import android.util.Log
import com.xingzhi.android.base.http.interceptor.utils.HttpInterceptorUtils
import com.xingzhi.android.BuildConfig
import okhttp3.*
import okhttp3.internal.http.promisesBody
import java.io.IOException
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

/**
 * Created by xiedongdong on 2020/01/18
 */
class HttpLoggingInterceptor @JvmOverloads constructor(tag: String? = TAG) : Interceptor {

    companion object {
        private const val TAG = "HttpLogging"
    }

    @Volatile
    private var printLevel = Level.BODY
    private var colorLevel = java.util.logging.Level.INFO
    private val logger: Logger = Logger.getLogger(tag ?: "")

    fun setPrintLevel(level: Level?) {
        if (level == null) throw NullPointerException("level == null. Use Level.NONE instead.")
        printLevel = level
    }

    fun setColorLevel(level: java.util.logging.Level) {
        colorLevel = level
    }

    private fun httpLog(message: String) {
        logger.log(colorLevel, message)
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (printLevel == Level.NONE) {
            return chain.proceed(request)
        }

        //请求日志拦截
        logForRequest(request, chain.connection())

        //执行请求，计算请求时间
        val startNs = System.nanoTime()
        val response: Response
        response = try {
            chain.proceed(request)
        } catch (e: Exception) {
            httpLog("<-- HTTP FAILED: $e")
            throw e
        }
        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)

        //响应日志拦截
        return logForResponse(response, tookMs)
    }

    private fun logForRequest(request: Request, connection: Connection?) {
        val logBody = printLevel == Level.BODY
        val logHeaders = printLevel == Level.BODY || printLevel == Level.HEADERS
        val requestBody = request.body
        val hasRequestBody = requestBody != null
        val protocol = connection?.protocol() ?: Protocol.HTTP_1_1
        try {
            val requestStartMessage = "--> " + request.method + ' ' + request.url + ' ' + protocol
            httpLog(requestStartMessage)
            if (logHeaders) {
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
                    if (!"Content-Type".equals(name, ignoreCase = true) && !"Content-Length".equals(
                            name,
                            ignoreCase = true
                        )
                    ) {
                        httpLog("\t" + name + ": " + headers.value(i))
                    }
                    i++
                }
                httpLog(" ")
                if (logBody && hasRequestBody) {
                    if (HttpInterceptorUtils.isPlaintext(requestBody!!.contentType())) {
                        httpLog("\tbody:" + HttpInterceptorUtils.bodyToString(request))
                    } else {
                        httpLog("\tbody: maybe [binary body], omitted!")
                    }
                }
            }
        } catch (e: Exception) {
            log(e.toString())
        } finally {
            httpLog("--> END " + request.method)
        }
    }

    private fun logForResponse(response: Response, tookMs: Long): Response {
        val builder = response.newBuilder()
        val clone = builder.build()
        var responseBody = clone.body
        val logBody = printLevel == Level.BODY
        val logHeaders = printLevel == Level.BODY || printLevel == Level.HEADERS
        try {
            httpLog("<-- " + clone.code + ' ' + clone.message + ' ' + clone.request.url + " (" + tookMs + "ms）")
            if (logHeaders) {
                val headers = clone.headers
                var i = 0
                val count = headers.size
                while (i < count) {
                    httpLog("\t" + headers.name(i) + ": " + headers.value(i))
                    i++
                }
                httpLog(" ")
                if (logBody && clone.promisesBody()) {
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
            }
        } catch (e: Exception) {
            log(e.toString())
        } finally {
            httpLog("<-- END HTTP")
        }
        return response
    }

    private fun log(message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, message)
        }
    }

    enum class Level {
        NONE,  //不打印log
        BASIC,  //只打印 请求首行 和 响应首行
        HEADERS,  //打印请求和响应的所有 Header
        BODY //所有数据全部打印
    }

}
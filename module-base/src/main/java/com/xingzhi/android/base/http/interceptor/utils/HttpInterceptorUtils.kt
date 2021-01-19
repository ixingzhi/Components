package com.xingzhi.android.base.http.interceptor.utils

import okhttp3.MediaType
import okhttp3.Request
import okio.Buffer
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.*

/**
 * Created by xiedongdong on 2020/01/18
 */
object HttpInterceptorUtils {
    private val UTF8 = StandardCharsets.UTF_8

    fun getCharset(contentType: MediaType?): Charset {
        var charset = if (contentType != null) contentType.charset(UTF8) else UTF8
        if (charset == null) charset = UTF8
        return charset!!
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    fun isPlaintext(mediaType: MediaType?): Boolean {
        if (mediaType == null) return false
        mediaType.type
        if (mediaType.type == "text") {
            return true
        }
        var subtype = mediaType.subtype
        subtype = subtype.toLowerCase(Locale.ROOT)
        return subtype.contains("x-www-form-urlencoded") || subtype.contains("json") || subtype.contains("xml") || subtype.contains("html")
    }

    fun bodyToString(request: Request): String {
        return try {
            val copy = request.newBuilder().build()
            val body = copy.body ?: return ""
            val buffer = Buffer()
            body.writeTo(buffer)
            val charset = getCharset(body.contentType())
            buffer.readString(charset)
        } catch (e: Exception) {
            ""
        }
    }

    @Throws(IOException::class)
    fun toByteArray(input: InputStream): ByteArray {
        val output = ByteArrayOutputStream()
        write(input, output)
        output.close()
        return output.toByteArray()
    }

    @Throws(IOException::class)
    fun write(inputStream: InputStream, outputStream: OutputStream) {
        var len: Int
        val buffer = ByteArray(4096)
        while (inputStream.read(buffer).also { len = it } != -1) outputStream.write(buffer, 0, len)
    }

}
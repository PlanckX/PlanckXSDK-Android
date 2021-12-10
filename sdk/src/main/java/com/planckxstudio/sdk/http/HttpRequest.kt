package com.planckxstudio.sdk.http

import com.planckxstudio.sdk.Constants
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.*
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import javax.crypto.Mac
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import android.util.Base64
import android.util.Log

object HttpRequest {
    private const val GET = "GET"
    private const val POST = "POST"
    private val job = Job()

    /**
     * do get request
     * @param reqUrl String
     * @param params MutableMap<String, String>?
     * @param callback HttpCallback
     */
    fun doGet(reqUrl: String, params: MutableMap<String, String>?, callback: HttpCallback) {
        fetch(reqUrl, GET, params, null, callback)
    }

    /**
     * do get request
     * @param reqUrl String
     * @param params MutableMap<String, String>?
     * @param body MutableMap<String, String>?
     * @param callback HttpCallback
     */
    fun doPost(
        reqUrl: String,
        params: MutableMap<String, String>?,
        body: MutableMap<String, String>?,
        callback: HttpCallback
    ) {
        fetch(reqUrl, POST, params, body, callback)
    }

    /**
     * Check the init parameters
     */
    private fun checkParams() {
        if (Constants.apiKey.isNullOrBlank()) {
            throw PlanckxstudioException("apikey is null")
        }
        if (Constants.secretKey.isNullOrBlank()) {
            throw PlanckxstudioException("secretKey is null")
        }
    }

    /**
     * Build the header and assemble the signature parameters
     * @param params MutableMap<String, String>?
     * @return MutableMap<String, String>
     */
    private fun buildHeader(params: MutableMap<String, String>?): MutableMap<String, String> {
        val header = mutableMapOf<String, String>()
        params?.let { header.putAll(it) }
        Constants.apiKey?.let { header["access_key"] = it }
        header["timestamp"] = System.currentTimeMillis().toString()
        header["nonce"] = UUID.randomUUID().toString()
        var headerIds: List<Map.Entry<String, String>> =
            ArrayList<Map.Entry<String, String>>(header.entries)
        headerIds = headerIds.sortedBy { it.key }
        val builder = StringBuffer()
        for (item in headerIds) {
            builder.append(item.key).append("=").append(item.value).append("&")
        }
        Constants.secretKey?.let {
            val sign = createSign(builder.toString(), it)
            header["sign"] = sign
        }
        return header
    }

    /**
     *
     * @param reqUrl String
     * @param requestMethod String
     * @param params MutableMap<String, String>?
     * @param body MutableMap<String, String>?
     * @param callback HttpCallback
     */
    private fun fetch(
        reqUrl: String,
        requestMethod: String,
        params: MutableMap<String, String>?,
        body: MutableMap<String, String>?,
        callback: HttpCallback
    ) {
        CoroutineScope(job + Dispatchers.Main).launch {
            try {
                checkParams()
                callback.success(withContext(CoroutineScope(job + Dispatchers.Default).coroutineContext) {
                    request(reqUrl, requestMethod, buildHeader(params), params, body)
                })
            } catch (ex: Exception) {
                callback.error(ex)
            }
        }
    }

    /**
     * Performing HTTP requests
     * @param reqUrl String
     * @param requestMethod String
     * @param header MutableMap<String, String>
     * @param params MutableMap<String, String>?
     * @param body MutableMap<String, String>?
     * @return String
     */
    private fun request(
        reqUrl: String,
        requestMethod: String,
        header: MutableMap<String, String>,
        params: MutableMap<String, String>?,
        body: MutableMap<String, String>?
    ): String {
        var httpConn: HttpURLConnection? = null
        var isReader: InputStreamReader? = null
        var bufReader: BufferedReader? = null
        val readTextBuf = StringBuffer()
        try {
            val urlBuilder = StringBuilder(reqUrl)
            params?.let {
                if (reqUrl.indexOf("?") == -1) {
                    urlBuilder.append("?")
                }
                for ((index, item) in it.entries.withIndex()) {
                    urlBuilder.append(item.key).append("=").append(item.value)
                    if (index < it.entries.size - 1) {
                        urlBuilder.append("&")
                    }
                }
            }
            val url = URL(urlBuilder.toString())
            httpConn = url.openConnection() as HttpURLConnection
            httpConn.requestMethod = requestMethod
            httpConn.connectTimeout = 15000
            httpConn.readTimeout = 15000
            httpConn.useCaches = false
            httpConn.setRequestProperty("Content-Type", "application/json")
            httpConn.let {
                header.forEach { map ->
                    it.setRequestProperty(map.key, map.value)
                }
            }

            if (requestMethod == POST) {
                val jsonObject = JSONObject()
                body?.forEach {
                    jsonObject.put(it.key, it.value)
                }
                val wr = DataOutputStream(httpConn.outputStream)
                wr.writeBytes(jsonObject.toString())
            }
            val inputStream = httpConn.inputStream
            isReader = InputStreamReader(inputStream)
            bufReader = BufferedReader(isReader)
            var line = bufReader.readLine()
            while (line != null) {
                readTextBuf.append(line)
                line = bufReader.readLine()
            }
            return readTextBuf.toString()
        } catch (ex: Exception) {
            throw PlanckxstudioException(ex)
        } finally {
            try {
                bufReader?.let {
                    it.close()
                    bufReader = null
                }
                isReader?.let {
                    it.close()
                    isReader = null
                }

                httpConn?.let {
                    it.disconnect()
                    httpConn = null
                }
            } catch (ex: IOException) {
                throw PlanckxstudioException(ex)
            }
        }
    }

    private fun createSign(encryptText: String, encryptKey: String): String {
        val data: ByteArray = encryptKey.toByteArray()
        val secretKey: SecretKey = SecretKeySpec(data, "HmacSHA1")
        val mac: Mac = Mac.getInstance("HmacSHA1")
        mac.init(secretKey)
        val text: ByteArray = encryptText.toByteArray()
        val array = mac.doFinal(text)
        return String(Base64.encode(array, Base64.DEFAULT))
    }

}

interface HttpCallback {
    fun success(str: String)
    fun error(e: Throwable)
}

package com.xingzhi.android.biz.model

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonParser

import java.lang.reflect.Field
import java.lang.reflect.Type
import java.util.ArrayList

/**
 * Created by xiedongdong on 2020/11/28
 */
open class Model {

    override fun toString(): String {
        val result = StringBuilder()
        val newLine = System.getProperty("line.separator")

        result.append(this.javaClass.name)
        result.append(" Object {")
        result.append(newLine)

        // determine fields declared in this class only (no fields of superclass)
        val fields = this.javaClass.declaredFields

        // print field names paired with their values
        for (field in fields) {
            result.append("  ")
            try {
                result.append(field.name)
                result.append(": ")
                result.append(field.get(this))

            } catch (ex: IllegalAccessException) {
                ex.printStackTrace()
            }

            result.append(newLine)
        }
        result.append("}")

        return result.toString()
    }

    companion object {

        fun <T> create(json: JsonElement, classOfModel: Class<T>): T {
            return gson().fromJson(json, classOfModel)
        }

        fun <T> createList(json: JsonElement, classOfModel: Class<T>): List<T> {
            val list = ArrayList<T>()
            val array = json.asJsonArray
            val iterator = array.iterator()
            while (iterator.hasNext()) {
                list.add(create(iterator.next(), classOfModel))
            }
            return list
        }

        protected fun gson(): Gson {
            return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        }

        fun toJson(model: Any): String {
            val gson = gson()
            return gson.toJson(model)
        }

        fun <T> fromJson(json: String, classOfModel: Class<T>): T {
            val gson = gson()
            return gson.fromJson(json, classOfModel)
        }

        fun <T> fromJsonToList(json: String, classOfModel: Class<T>): List<T> {
            return createList(JsonParser().parse(json), classOfModel)
        }

        fun <K, V> fromJsonToMap(json: String, type: Type): Map<K, V> {
            return gson().fromJson(json, type)
        }
    }
}

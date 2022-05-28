package com.chocomiruku.homework11.util

import android.content.Context
import android.content.res.AssetManager
import com.chocomiruku.homework11.domain.Employee
import com.chocomiruku.homework11.domain.Position
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

enum class LoadingStatus { LOADING, ERROR, DONE }

fun AssetManager.readFile(fileName: String) = open(fileName)
    .bufferedReader()
    .use { it.readText() }

/* Thread.sleep(3000) для имитации загрузки - иначе ProgressBar просто не видно */
fun getPositionsFromJson(context: Context): List<Position> {
    Thread.sleep(3000)
    val jsonString = context.assets.readFile("positions.json")
    val typeToken = object : TypeToken<List<Position>>() {}.type
    return Gson().fromJson(jsonString, typeToken)
}

fun getEmployeesFromJson(context: Context): List<Employee> {
    Thread.sleep(3000)
    val jsonString = context.assets.readFile("employees.json")
    val typeToken = object : TypeToken<List<Employee>>() {}.type
    return Gson().fromJson(jsonString, typeToken)
}

fun Int.formatYears(): String {
    val years = this.toString()

    if (this in 10..20) {
        return years.plus(" лет")
    } else return when (years.takeLast(1)) {
        "1" -> years.plus(" год")
        "2", "3", "4" -> years.plus(" года")
        else -> years.plus(" лет")
    }
}
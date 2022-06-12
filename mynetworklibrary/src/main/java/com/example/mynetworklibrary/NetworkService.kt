package com.example.mynetworklibrary

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import java.io.Serializable

data class CustomUi (
    @SerializedName("heading-text")
    val heading_text: String,
    @SerializedName("logo-url")
    val logo_url: String,
    val uidata: List<Uidata>
):Serializable

data class Uidata(
    val hint: String,
    val key: String,
    val uitype: String,
    val value: String
): Serializable
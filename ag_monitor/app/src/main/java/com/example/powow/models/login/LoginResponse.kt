package com.example.powow.models.login

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("user_name")
    @Expose
    var userName: String? = null

    @SerializedName("company_id")
    @Expose
    var companyId: Int? = null

    @SerializedName("user_id")
    @Expose
    var userId: Int? = null

    @SerializedName("authorization_id")
    @Expose
    var authorizationId: Int? = null

    @SerializedName("tenant_code")
    @Expose
    var tenantCode: Any? = null

    @SerializedName("company_name")
    @Expose
    var companyName: String? = null

    @SerializedName("is_superuser")
    @Expose
    var isSuperuser: Boolean? = null

    @SerializedName("is_advisor")
    @Expose
    var isAdvisor: Boolean? = null

    @SerializedName("csrftoken")
    @Expose
    var csrftoken: String? = null

    @SerializedName("session_id")
    @Expose
    var sessionId: String? = null

    @SerializedName("firebase_jwt")
    @Expose
    var firebaseJwt: String? = null

    @SerializedName("default_map")
    @Expose
    var defaultMap: String? = null

    @SerializedName("account_choices")
    @Expose
    var accountChoices: Any? = null
}
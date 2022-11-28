package team.four.mys.domain.models

import com.google.gson.annotations.SerializedName

data class ValuteEnd(
    @SerializedName("ID") var ID: String? = null,
    @SerializedName("NumCode") var NumCode: String? = null,
    @SerializedName("CharCode") var CharCode: String? = null,
    @SerializedName("Nominal") var Nominal: Int? = null,
    @SerializedName("Name") var Name: String? = null,
    @SerializedName("Value") var Value: Double? = null,
    @SerializedName("Previous") var Previous: Double? = null

)

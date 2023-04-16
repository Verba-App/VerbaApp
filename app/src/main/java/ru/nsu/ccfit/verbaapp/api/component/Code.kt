package ru.nsu.ccfit.verbaapp.api.component

import com.google.gson.annotations.SerializedName

enum class Code(
    var httpStatus: String,
    var errorMessage: String
) {
    @SerializedName("OK")
    OK("OK", "УСПЕШНЫЙ ЗАПРОС"),

    @SerializedName("BAD_REQUEST")
    BAD_REQUEST(
        "BAD_REQUEST",
        "НЕВЕРНЫЙ ЗАПРОС"
    ),

    @SerializedName("UNAUTHORIZED")
    UNAUTHORIZED(
        "UNAUTHORIZED",
        "ПОЛЬЗОВАТЕЛЬ НЕ АВТОРИЗОВАН"
    ),

    @SerializedName("FORBIDDEN")
    FORBIDDEN("FORBIDDEN", "ЗАПРОС ЗАПРЕЩЕН"), NOT_FOUND(
        "NOT_FOUND",
        "ОТСУТСТВУЮТ ПРАВА ДОСТУПА"
    ),

    @SerializedName("INTERNAL_SERVER_ERROR")
    INTERNAL_SERVER_ERROR(
        "INTERNAL_SERVER_ERROR",
        "ВНУТРЕНЯЯ ОШИБКА СЕРВЕРА"
    ),

    @SerializedName("SERVICE_UNAVAILABLE")
    SERVICE_UNAVAILABLE("SERVICE_UNAVAILABLE", "СЕРВИС НЕДОСТУПЕН"), GATEWAY_TIMEOUT(
        "GATEWAY_TIMEOUT",
        "ВРЕМЯ ОЖИДАНИЯ ИСТЕКЛО"
    );


}

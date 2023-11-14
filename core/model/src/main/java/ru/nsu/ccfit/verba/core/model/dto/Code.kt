package ru.nsu.ccfit.verba.core.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
enum class Code(
    var httpStatus: String,
    var errorMessage: String
) {
    @SerialName("OK")
    OK("OK", "УСПЕШНЫЙ ЗАПРОС"),

    @SerialName("BAD_REQUEST")
    BAD_REQUEST(
        "BAD_REQUEST",
        "НЕВЕРНЫЙ ЗАПРОС"
    ),

    @SerialName("UNAUTHORIZED")
    UNAUTHORIZED(
        "UNAUTHORIZED",
        "ПОЛЬЗОВАТЕЛЬ НЕ АВТОРИЗОВАН"
    ),

    @SerialName("FORBIDDEN")
    FORBIDDEN("FORBIDDEN", "ЗАПРОС ЗАПРЕЩЕН"), NOT_FOUND(
        "NOT_FOUND",
        "ОТСУТСТВУЮТ ПРАВА ДОСТУПА"
    ),

    @SerialName("INTERNAL_SERVER_ERROR")
    INTERNAL_SERVER_ERROR(
        "INTERNAL_SERVER_ERROR",
        "ВНУТРЕНЯЯ ОШИБКА СЕРВЕРА"
    ),

    @SerialName("SERVICE_UNAVAILABLE")
    SERVICE_UNAVAILABLE("SERVICE_UNAVAILABLE", "СЕРВИС НЕДОСТУПЕН"), GATEWAY_TIMEOUT(
        "GATEWAY_TIMEOUT",
        "ВРЕМЯ ОЖИДАНИЯ ИСТЕКЛО"
    );


}

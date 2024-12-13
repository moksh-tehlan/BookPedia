package com.moksh.bookpedia.core.presentation

import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.disk_full
import cmp_bookpedia.composeapp.generated.resources.no_internet
import cmp_bookpedia.composeapp.generated.resources.request_timeout
import cmp_bookpedia.composeapp.generated.resources.serialization_error
import cmp_bookpedia.composeapp.generated.resources.server_error
import cmp_bookpedia.composeapp.generated.resources.too_many_request
import cmp_bookpedia.composeapp.generated.resources.unknown_error
import com.moksh.bookpedia.core.domain.DataError

fun DataError.toUiText(): UiText {
    val stringRes = when (this) {
        DataError.Local.DISK_FULL -> Res.string.disk_full
        DataError.Local.UNKNOWN -> Res.string.unknown_error
        DataError.Remote.REQUEST_TIMEOUT -> Res.string.request_timeout
        DataError.Remote.TOO_MANY_REQUEST -> Res.string.too_many_request
        DataError.Remote.NO_INTERNET -> Res.string.no_internet
        DataError.Remote.SERVER -> Res.string.server_error
        DataError.Remote.SERIALIZATION -> Res.string.serialization_error
        DataError.Remote.UNKNOWN -> Res.string.unknown_error
    }
    return UiText.StringResourceId(stringRes)
}
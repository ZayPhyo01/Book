package com.data.service

import com.tencent.mmkv.MMKV

object TokenService {
    var token: String?
        get() = MMKV.defaultMMKV().decodeString("access_token", null)
        set(value) {
            MMKV.defaultMMKV().encode("access_token", value)
        }
}
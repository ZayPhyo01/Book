package com.data.mapper

import com.data.db.entities.UserEntity
import com.domain.model.UserModel

fun UserModel.toEntity(): UserEntity = UserEntity(
    id = 1,
    userName = userName,
    phoneNumber = phoneNumber,
    email = email
)

fun UserEntity.toModel() = UserModel(
    userName = userName,
    phoneNumber = phoneNumber,
    email = email
)
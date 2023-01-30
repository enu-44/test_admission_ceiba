package com.admission.testceiba.data.mappers

import com.admission.testceiba.database.entities.UserEntity
import com.admission.testceiba.domain.model.UserDom
import com.admission.testceiba.network.dto.UserDto

fun UserDto.toDom(): UserDom {
    return UserDom(
        id= this.id,
        name = this.name,
        email = this.email,
        phone = this.phone
    )
}

fun UserEntity.toDom(): UserDom {
    return UserDom(
        id= this.id,
        name = this.name,
        email = this.email,
        phone = this.phone
    )
}

fun UserDom.toEntity(): UserEntity {
    return UserEntity(
        id= this.id,
        name = this.name,
        email = this.email,
        phone = this.phone
    )
}
package com.admission.testceiba.data.mappers

import com.admission.testceiba.domain.model.PostDom
import com.admission.testceiba.network.dto.PostDto

fun PostDto.toDom(): PostDom {
    return PostDom(
        title= this.title,
        body = this.body
    )
}

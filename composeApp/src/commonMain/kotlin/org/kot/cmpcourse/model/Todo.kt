package org.kot.cmpcourse.model

import kotlinx.serialization.Serializable

@Serializable
data class Todo (
    val id: String,
    val title: String,
    val isDone: Long
)
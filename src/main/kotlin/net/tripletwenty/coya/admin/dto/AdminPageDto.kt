package net.tripletwenty.coya.admin.dto

data class AdminPageDto (
    val key: Long,
    val id: String,
    val body: String,
    val options: List<OptionDto>
    )

// TODO add items/event changes
data class OptionDto (
    val id: String,
    val name: String,
    val target: String
    )

data class AdminPageCreateDto (
    val id: String,
    val body: String,
    val options: List<OptionDto>? = null
)

data class AdminPageUpdateDto (
    val id: String,
    val body: String? = null,
    val options: List<OptionDto>? = null
)
package net.tripletwenty.coya.player.dto

data class PageResponseDto (
    val body: String,
    val options: List<OptionDto>
)

data class OptionDto (
    val text: String,
    val url: String
)
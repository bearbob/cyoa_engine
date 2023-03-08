package net.tripletwenty.cyoa.player.dto

data class PageResponseDto(
    val body: String,
    val options: List<OptionDto>
)

package net.tripletwenty.coya.player.dto

import net.tripletwenty.coya.core.entities.NavigationOption

data class OptionDto(
    val text: String,
    val url: String
) {
    fun fromNavigationOption(
        navigationOption: NavigationOption
    ): OptionDto {
        return OptionDto(
            navigationOption.text
        )
    }
}

package com.gildedrose

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class GildedRoseTest {
    @ParameterizedTest
    @CsvSource(
        "1, 0", // has not expired
        "0, -1", // expiration day
        "-1, -2", // expired
    )
    fun `updateQuality decrements sellIn by 1`(initialSellIn: Int, expectedSellInt: Int) {
        val items = arrayOf(Item("normal item", initialSellIn, 7))
        GildedRose(items).updateQuality()
        assertThat(items[0].sellIn).isEqualTo(expectedSellInt)
    }
}



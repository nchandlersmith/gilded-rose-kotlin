package com.gildedrose

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class GildedRoseTest {
    @ParameterizedTest(name = "initialSellIn: {0} expectedSellIn: {1}")
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
    @ParameterizedTest(name = "sellIn: {0} initialQuality: {1} expectedQuality: {2}")
    @CsvSource(
        "1, 3, 2", // not expired
        "0, 3, 1", // expiration day
        "-1, 3, 1", // expired
        "1, 0, 0", // not expired quality cannot be below 0
        "0, 0, 0", // expiration day quality cannot be below 0
        "-1, 0, 0", // expired quality cannot be below 0
    )
    fun `updateQuality quality_decrements`(sellIn: Int, initialQuality: Int, expectedQuality: Int) {
        val items = arrayOf(Item("normal item", sellIn, initialQuality))
        GildedRose(items).updateQuality()
        assertThat(items.get(0).quality).isEqualTo(expectedQuality)
    }
}



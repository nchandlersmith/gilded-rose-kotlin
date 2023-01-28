package com.gildedrose

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class GildedRoseTest {
    @ParameterizedTest(name = "initialSellIn: {0} expectedSellIn: {1}")
    @CsvSource(
        "1, 0", // not expired
        "0, -1", // expiration day
        "-1, -2", // expired
    )
    fun `updateQuality normal item decrements sellIn`(initialSellIn: Int, expectedSellInt: Int) {
        val items = arrayOf(Item("normal item", initialSellIn, 7))
        GildedRose(items).updateQuality()
        assertThat(items[0].sellIn).isEqualTo(expectedSellInt)
    }
    @ParameterizedTest(name = "sellIn: {0} initialQuality: {1} expectedQuality: {2}")
    @CsvSource(
        "1, 3, 2", // not expired
        "0, 3, 1", // expiration day
        "-1, 3, 1", // expired
    )
    fun `updateQuality normal item quality_decreases`(sellIn: Int, initialQuality: Int, expectedQuality: Int) {
        val items = arrayOf(Item("normal item", sellIn, initialQuality))
        GildedRose(items).updateQuality()
        assertThat(items.get(0).quality).isEqualTo(expectedQuality)
    }
    @ParameterizedTest(name = "sellIn: {0} initialQuality: {1} expectedQuality: {2}")
    @CsvSource(
        "1, 0, 0", // not expired
        "0, 0, 0", // expiration day
        "-1, 0, 0", // expired
    )
    fun `updateQuality normal item quality cannot be negative`(sellIn: Int, initialQuality: Int, expectedQuality: Int) {
        val items = arrayOf(Item("normal item", sellIn, initialQuality))
        GildedRose(items).updateQuality()
        assertThat(items.get(0).quality).isEqualTo(expectedQuality)
    }
    @ParameterizedTest(name = "initialSellIn: {0} expectedQuality: {1}")
    @CsvSource(
        "1, 0", // not expired
        "0, -1", // expiration day
        "-1, -2", // expired
    )
    fun `updateQuality Aged Brie sellIn decrements`(initialSellIn: Int, expectedSellIn: Int) {
        val items = arrayOf(Item("Aged Brie", initialSellIn, 7))
        GildedRose(items).updateQuality()
        assertThat(items.get(0).sellIn).isEqualTo(expectedSellIn)
    }
}



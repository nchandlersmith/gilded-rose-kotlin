package com.gildedrose

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
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
    fun `updateQuality normal item quality decreases`(sellIn: Int, initialQuality: Int, expectedQuality: Int) {
        val items = arrayOf(Item("normal item", sellIn, initialQuality))
        GildedRose(items).updateQuality()
        assertThat(items.get(0).quality).isEqualTo(expectedQuality)
    }

    @ParameterizedTest(name = "sellIn: {0} initialQuality: {1} expectedQuality: {2}")
    @CsvSource(
        "1, 0, 0", // not expired
        "0, 0, 0", // expiration day
        "0, 1, 0", // expiration day
        "-1, 0, 0", // expired
        "-1, 1, 0", // expired
    )
    fun `updateQuality normal item quality cannot be negative`(sellIn: Int, initialQuality: Int, expectedQuality: Int) {
        val items = arrayOf(Item("normal item", sellIn, initialQuality))
        GildedRose(items).updateQuality()
        assertThat(items.get(0).quality).isEqualTo(expectedQuality)
    }

    @ParameterizedTest(name = "initialSellIn: {0} expectedSellIn: {1}")
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

    @ParameterizedTest(name = "sellIn: {0} initialQuality: {1} expectedQuality: {2}")
    @CsvSource(
        "1, 3, 4", // not expired
        "0, 3, 5", // expiration day
        "-1, 3, 5", // expired
    )
    fun `updateQuality aged brie quality increases`(sellIn: Int, initialQuality: Int, expectedQuality: Int) {
        val items = arrayOf(Item("Aged Brie", sellIn, initialQuality))
        GildedRose(items).updateQuality()
        assertThat(items.get(0).quality).isEqualTo(expectedQuality)
    }

    @ParameterizedTest(name = "sellIn: {0} initialQuality: {1} expectedQuality: {2}")
    @CsvSource(
        "1, 50, 50", // not expired
        "0, 49, 50", // expiration day
        "0, 50, 50", // expiration day
        "-1, 49, 50", // expired
        "-1, 50, 50", // expired
    )
    fun `updateQuality aged brie quality cannot be greater than 50`(
        sellIn: Int,
        initialQuality: Int,
        expectedQuality: Int
    ) {
        val items = arrayOf(Item("Aged Brie", sellIn, initialQuality))
        GildedRose(items).updateQuality()
        assertThat(items.get(0).quality).isEqualTo(expectedQuality)
    }

    @ParameterizedTest(name = "initialSellIn: {0} expectedQuality: {1}")
    @CsvSource(
        "11, 10",
        "10, 9",
        "9, 8",
        "6, 5",
        "5, 4",
        "4, 3",
        "1, 0",
        "0, -1",
        "-1, -2",
    )
    fun `updateQuality backstage passes sellIn decrements`(initialSellIn: Int, expectedSellIn: Int) {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, 7))
        GildedRose(items).updateQuality()
        assertThat(items.get(0).sellIn).isEqualTo(expectedSellIn)
    }

    @Test
    fun `updateQuality backstage passes quality increases by 1`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 11, 20))
        GildedRose(items).updateQuality()
        assertThat(items.get(0).quality).isEqualTo(21)
    }

    @ParameterizedTest(name = "sellIn: {0} initialQuality: {1} expectedQuality {2}")
    @CsvSource(
        "10, 20, 22",
        "9, 20, 22",
        "6, 20, 22"
    )
    fun `updateQuality backstage passes quality increases by 2`(
        sellIn: Int,
        initialQuality: Int,
        expectedQuality: Int
    ) {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", sellIn, initialQuality))
        GildedRose(items).updateQuality()
        assertThat(items.get(0).quality).isEqualTo(expectedQuality)
    }

    @ParameterizedTest(name = "sellIn: {0} initialQuality: {1} expectedQuality {2}")
    @CsvSource(
        "5, 20, 23",
        "4, 20, 23",
        "1, 20, 23",
    )
    fun `updateQuality backstage passes quality increases by 3`(
        sellIn: Int,
        initialQuality: Int,
        expectedQuality: Int
    ) {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", sellIn, initialQuality))
        GildedRose(items).updateQuality()
        assertThat(items.get(0).quality).isEqualTo(expectedQuality)
    }

    @ParameterizedTest(name = "sellIn: {0} initialQuality: {1} expectedQuality {2}")
    @CsvSource(
        "0, 20, 0",
        "-1, 20, 0",
    )
    fun `updateQuality backstage passes quality drops to 0`(sellIn: Int, initialQuality: Int, expectedQuality: Int) {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", sellIn, initialQuality))
        GildedRose(items).updateQuality()
        assertThat(items.get(0).quality).isEqualTo(expectedQuality)
    }

    @ParameterizedTest(name = "sellIn: {0} initialQuality: {1} expectedQuality {2}")
    @CsvSource(
        "11, 50, 50",
        "10, 50, 50",
        "10, 49, 50",
        "5, 50, 50",
        "5, 49, 50",
        "5, 48, 50",
    )
    fun `updateQuality backstage passes quality cannot be greater than 50`(
        sellIn: Int,
        initialQuality: Int,
        expectedQuality: Int
    ) {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", sellIn, initialQuality))
        GildedRose(items).updateQuality()
        assertThat(items.get(0).quality).isEqualTo(expectedQuality)
    }

    @Test
    fun `updateQuality sulfurus sellIn does not decrement`() {
        val items = arrayOf(Item("Sulfuras, Hand of Ragnaros", 10, 80))
        GildedRose(items).updateQuality()
        assertThat(items.get(0).sellIn).isEqualTo(10)
    }

    @Test
    fun `updateQuality sulfurus quality does not decrement`() {
        val items = arrayOf(Item("Sulfuras, Hand of Ragnaros", 10, 70))
        GildedRose(items).updateQuality()
        assertThat(items.get(0).quality).isEqualTo(70)
    }

    @Test
    fun `updateQuality preserves item name`() {
        val name = "some name"
        val items = arrayOf(Item(name, 10, 70))
        GildedRose(items).updateQuality()
        assertThat(items.get(0).name).isEqualTo(name)
    }
}



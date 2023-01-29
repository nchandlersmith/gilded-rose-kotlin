package com.gildedrose

class GildedRose(var items: Array<Item>) {
    fun updateQuality() {
        for (i in items.indices) {
            if (isSulfurus(i)) {
                Sulfurus(items[i]).apply()
                return
            }
            if (isAgedBrie(i)) {
                AgedBrie(items[i]).apply()
                return
            }
            if (isBackstagePasses(i)) {
                BackStagePasses(items[i]).apply()
                return
            }
            Normal(items[i]).apply()
        }
    }

    private fun isAgedBrie(i: Int) = "Aged Brie" == items[i].name
    private fun isBackstagePasses(i: Int) = "Backstage passes to a TAFKAL80ETC concert" == items[i].name
    private fun isSulfurus(i: Int) = "Sulfuras, Hand of Ragnaros" == items[i].name

}

class Sulfurus(var item: Item) {
    fun apply() {
    }
}

class AgedBrie(var item: Item) {
    fun apply() {
        item.quality += 1
        if (item.sellIn < 1) {
            item.quality += 1
        }
        item.sellIn -=1
        item.quality = minOf(50, item.quality)
    }
}

class BackStagePasses(var item: Item) {
    fun apply() {
        if (item.sellIn <= 0) {
            item.sellIn -= 1
            item.quality = 0
            return
        }
            item.quality += 1
            if (item.sellIn < 11) {
                item.quality += 1
            }
            if (item.sellIn < 6) {
                item.quality += 1
            }
            item.sellIn -= 1
            item.quality = minOf(50, item.quality)
    }
}

class Normal(var item: Item) {
    fun apply() {
        item.quality -= 1
        if (item.sellIn < 1) {
            item.quality -= 1
        }
        item.sellIn -=1
        item.quality = maxOf(0, item.quality)
    }
}


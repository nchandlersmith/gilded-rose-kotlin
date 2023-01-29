package com.gildedrose

class GildedRose(var items: Array<Item>) {
    fun updateQuality() {

        for (i in items.indices) {
            if (isDegradingItem(i)) {
                safeDecrementQuality(i)
            } else {
                safeIncrementQuality(i)
                if (isBackstagePasses(i) && items[i].sellIn < 11) {
                    safeIncrementQuality(i)
                }
                if (isBackstagePasses(i) && items[i].sellIn < 6) {
                    safeIncrementQuality(i)
                }
            }

            safeDecrementSellIn(i)

            if (isExpired(i)) {
                if (isAgedBrie(i)) {
                    safeIncrementQuality(i)
                } else if (isBackstagePasses(i)) {
                        setQualityTo0(i)
                } else {
                        safeDecrementQuality(i)
                }
            }
        }
    }

    private fun isDegradingItem(i: Int) = !isAgedBrie(i) && !isBackstagePasses(i) && !isSulfurus(i)

    private fun safeDecrementSellIn(i: Int) {
        if (!isSulfurus(i)) {
            decrementSellIn(i)
        }
    }

    private fun isExpired(i: Int) = items[i].sellIn < 0

    private fun decrementSellIn(i: Int) {
        items[i].sellIn -= 1
    }

    private fun safeIncrementQuality(i: Int) {
        if (items[i].quality < 50) {
            incrementQuality(i)
        }
    }

    private fun safeDecrementQuality(i: Int) {
        if (items[i].quality > 0) {
            decrementQuality(i)
        }
    }

    private fun incrementQuality(i: Int) {
        items[i].quality += 1
    }

    private fun setQualityTo0(i: Int) {
        items[i].quality = 0
    }

    private fun decrementQuality(i: Int) {
        items[i].quality -= 1
    }

    private fun isAgedBrie(i: Int) = "Aged Brie" == items[i].name
    private fun isBackstagePasses(i: Int) = "Backstage passes to a TAFKAL80ETC concert" == items[i].name
    private fun isSulfurus(i: Int) = "Sulfuras, Hand of Ragnaros" == items[i].name

}


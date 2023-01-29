package com.gildedrose.shopitem

import com.gildedrose.Item

class BackStagePasses(val item: Item): ShopItem {
    override fun apply() {
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
package com.gildedrose.shopitem

import com.gildedrose.Item

class Conjured(var item: Item): ShopItem {
    override fun apply() {
        if (item.sellIn > 0) item.quality -= 2 else item.quality -= 4
        item.quality = maxOf(0, item.quality)
        item.sellIn -= 1
    }
}
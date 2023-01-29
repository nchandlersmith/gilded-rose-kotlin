package com.gildedrose.shopitem

import com.gildedrose.Item


class Normal(var item: Item): ShopItem {
    override fun apply() {
        item.quality -= 1
        if (item.sellIn < 1) {
            item.quality -= 1
        }
        item.sellIn -=1
        item.quality = maxOf(0, item.quality)
    }
}
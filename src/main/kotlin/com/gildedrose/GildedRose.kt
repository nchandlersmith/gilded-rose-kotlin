package com.gildedrose

import com.gildedrose.shopitem.factory.ShopItemFactory

class GildedRose(var items: Array<Item>) {
    fun updateQuality() {
        val factory = ShopItemFactory()
        for (i in items.indices) {
            factory.create(items[i]).apply()
        }
    }
}


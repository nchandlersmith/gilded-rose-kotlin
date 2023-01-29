package com.gildedrose.shopitem.factory

import com.gildedrose.*
import com.gildedrose.shopitem.*

class ShopItemFactory {
    fun create(item: Item):ShopItem =
        when (item.name) {
            "Aged Brie" -> AgedBrie(item)
            "Backstage passes to a TAFKAL80ETC concert" -> BackStagePasses(item)
            "Sulfuras, Hand of Ragnaros" -> Sulfurus(item)
            else -> Normal(item)
        }
}
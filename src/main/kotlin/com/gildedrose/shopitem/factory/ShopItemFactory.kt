package com.gildedrose.shopitem.factory

import com.gildedrose.*
import com.gildedrose.shopitem.*

class ShopItemFactory {
    fun create(item: Item): ShopItem {
        if ("Aged Brie" == item.name) {
            return AgedBrie(item)
        }
        if ("Backstage passes to a TAFKAL80ETC concert" == item.name) {
            return BackStagePasses(item)
        }
        if ("Sulfuras, Hand of Ragnaros" == item.name) {
            return Sulfurus(item)
        }
        return Normal(item)
    }
}
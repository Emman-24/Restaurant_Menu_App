package com.example.restaurantmenuapp.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.example.restaurantmenuapp.ui.model.ItemModel

class MenuViewModel : ViewModel() {

    private val _menu = mutableStateListOf<ItemModel>(
        ItemModel(1, "Fettuccine", 5),
        ItemModel(2, "Risotto", 6),
        ItemModel(3, "Gnocchi", 4),
        ItemModel(4, "Spaghetti", 3),
        ItemModel(5, "Lasagna", 5),
        ItemModel(6, "Steak Parmigiana", 2)
    )

    val menu: List<ItemModel> = _menu

    private val _order = mutableStateMapOf<ItemModel, Int>()
    val order: Map<ItemModel, Int> = _order

    fun addOrder(item: ItemModel, count: Int) {
        _order[item] = count
    }

    fun removeOrder(item: ItemModel, count: Int) {
        if (count > 0) {
            _order[item] = count
        } else {
            _order.remove(item)
        }
    }

    fun clearOrder() {
        for ((item, count) in _order) {
            for (product in _menu) {
                if (product.id == item.id) {
                    product.stock -= count
                }
            }
        }
        _order.clear()
    }

    fun printOrder(): String {
        val result = StringBuilder("Ordered:\n")
        val itemCount = _order.size
        val sortedEntries = _order.entries.sortedBy { it.key.id }

        sortedEntries.forEachIndexed { index, (item, count) ->
            result.append("==> ${item.name}: $count")
            if (itemCount > 1 && index < itemCount - 1) {
                result.append("\n")
            }
        }

        return result.toString()
    }

}
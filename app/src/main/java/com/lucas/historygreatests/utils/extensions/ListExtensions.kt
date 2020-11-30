package com.lucas.historygreatests.utils.extensions

inline fun <reified T> List<T>.updateItemsValues(transform: (item: T) -> T): List<T> {
    val newList = this.toMutableList()
    newList.forEachIndexed { index, t ->
        newList[index] = transform(t)
    }

    return this
}

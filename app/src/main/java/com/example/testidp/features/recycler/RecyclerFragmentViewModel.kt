package com.example.testidp.features.recycler

import androidx.lifecycle.MutableLiveData
import com.example.testidp.base.BaseViewModel
import com.example.testidp.features.recycler.drag.DragAndDropItem

class RecyclerFragmentViewModel : BaseViewModel() {

    val itemsLD = MutableLiveData<List<DragAndDropItem>>()

    init {
        itemsLD.value = getData()
    }

    private fun getData(): List<DragAndDropItem> {
        return listOf(
            DragAndDropItem("Item 1"),
            DragAndDropItem("Item 2"),
            DragAndDropItem("Item 3"),
            DragAndDropItem("Item 4"),
            DragAndDropItem("Item 5"),
            DragAndDropItem("Item 6"),
            DragAndDropItem("Item 7"),
            DragAndDropItem("Item 8"),
        )
    }
}
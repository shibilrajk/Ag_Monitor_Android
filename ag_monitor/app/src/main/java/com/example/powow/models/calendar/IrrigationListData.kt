package com.example.powow.models.calendar

data class IrrigationListData (
    val count : String,
    val irrigationLIst : ArrayList<ItemData>
        )

data class ItemData (
    val items : String
        )

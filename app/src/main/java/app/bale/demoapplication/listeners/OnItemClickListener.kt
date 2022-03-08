package app.bale.demoapplication.listeners

import app.bale.demoapplication.data.model.Deal

interface OnItemClickListener {
    fun onItemClick(item: Deal?)
}
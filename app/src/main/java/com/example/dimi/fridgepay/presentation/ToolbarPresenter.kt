package com.example.dimi.fridgepay.presentation

import android.arch.lifecycle.LiveData
import com.example.dimi.fridgepay.model.ToolbarModel

interface ToolbarPresenter {
    fun getToolbarData(): LiveData<ToolbarModel>
}
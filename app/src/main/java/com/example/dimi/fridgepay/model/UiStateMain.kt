package com.example.dimi.fridgepay.model

class UiStateMain(val toolbarModel: ToolbarModel,
                  val viewDynamicStateMain: ViewDynamicStateMain)

class ViewDynamicStateMain(val basketCount: Int,
                           val buyButtonEnabled: Boolean)
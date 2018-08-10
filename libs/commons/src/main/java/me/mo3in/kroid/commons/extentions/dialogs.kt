package me.mo3in.kroid.commons.extentions

import android.content.Context
import android.content.DialogInterface

//typealias AlertBuilderFactory<D> = (Context) -> AlertBuilder<D>
//
//
//fun <D : DialogInterface> Context.alert(factory: AlertBuilderFactory<D>, message: String, title: String? = null, init: (AlertBuilder<D>.() -> Unit)? = null): AlertBuilder<D> {
//    return factory(this).apply {
//        if (title != null) {
//            this.title = title
//        }
//        this.message = message
//        if (init != null) init()
//    }
//}
//
//
//fun <D : DialogInterface> Context.alert(factory: AlertBuilderFactory<D>, messageResource: Int, titleResource: Int? = null, init: (AlertBuilder<D>.() -> Unit)? = null): AlertBuilder<D> {
//    return factory(this).apply {
//        if (titleResource != null) {
//            this.titleResource = titleResource
//        }
//        this.messageResource = messageResource
//        if (init != null) init()
//    }
//}
//
//fun <D : DialogInterface> Context.alert(factory: AlertBuilderFactory<D>, init: AlertBuilder<D>.() -> Unit): AlertBuilder<D> = factory(this).apply { init() }
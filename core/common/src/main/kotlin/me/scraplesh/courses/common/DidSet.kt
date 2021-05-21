package me.scraplesh.courses.common

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <T> didSet(crossinline onSet: T.() -> Unit) = object : ReadWriteProperty<Any?, T> {
    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException(
            "Property ${property.name} should be initialized before get."
        )
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (this.value != value) {
            this.value = value
            value.onSet()
        }
    }
}

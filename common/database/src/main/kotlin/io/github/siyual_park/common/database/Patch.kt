package io.github.siyual_park.common.database

interface Patch<T> {
    fun apply(entity: T): T
}

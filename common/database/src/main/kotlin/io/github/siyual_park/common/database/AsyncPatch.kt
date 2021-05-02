package io.github.siyual_park.common.database

interface AsyncPatch<T> {
    suspend fun apply(entity: T): T
}

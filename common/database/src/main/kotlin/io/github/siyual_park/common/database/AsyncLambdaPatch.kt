package io.github.siyual_park.common.database

class AsyncLambdaPatch<T>(
    private val patch: suspend (T) -> T
) : AsyncPatch<T> {
    override suspend fun apply(entity: T): T = this.patch(entity)

    companion object {
        @JvmName("fromByMapper")
        fun <T> from(patch: suspend (T) -> T) = AsyncLambdaPatch(patch)

        @JvmName("fromByApplier")
        fun <T> from(patch: suspend (T) -> Unit) = AsyncLambdaPatch<T> { entity ->
            entity.apply { patch(this) }
        }
    }
}

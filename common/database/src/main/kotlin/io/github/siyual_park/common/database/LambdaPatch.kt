package io.github.siyual_park.common.database

class LambdaPatch<T>(
    private val patch: (T) -> T
) : Patch<T> {
    override fun apply(entity: T): T = this.patch(entity)

    companion object {
        @JvmName("fromByMapper")
        fun <T> from(patch: (T) -> T) = LambdaPatch(patch)

        @JvmName("fromByApplier")
        fun <T> from(patch: (T) -> Unit) = LambdaPatch<T> { entity ->
            entity.apply { patch(this) }
        }
    }
}

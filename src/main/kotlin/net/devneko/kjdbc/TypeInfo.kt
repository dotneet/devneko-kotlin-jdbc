package net.devneko.kjdbc

/**
 * Borrow from injekt.
 * https://github.com/kohesive/injekt
 */

import java.lang.reflect.*
import java.lang.reflect.Array

@Suppress("UNCHECKED_CAST")
fun Type.erasedType(): Class<Any> {
    return when (this) {
        is Class<*> -> this as Class<Any>
        is ParameterizedType -> this.getRawType().erasedType()
        is GenericArrayType -> {
            // getting the array type is a bit trickier
            val elementType = this.getGenericComponentType().erasedType()
            val testArray = Array.newInstance(elementType, 0)
            testArray.javaClass
        }
        is TypeVariable<*> -> {
            // not sure yet
            throw IllegalStateException("Not sure what to do here yet")
        }
        is WildcardType -> {
            this.getUpperBounds()[0].erasedType()
        }
        else -> throw IllegalStateException("Should not get here.")
    }
}

inline fun <reified T: Any> typeRef(): FullTypeReference<T> = object: FullTypeReference<T>(){}
inline fun <reified T: Any> fullType(): FullTypeReference<T> = object: FullTypeReference<T>(){}

interface TypeReference<T> {
    public val type: Type
}

abstract class FullTypeReference<T> protected constructor() : TypeReference<T> {
    override public val type: Type = javaClass.getGenericSuperclass().let { superClass ->
        if (superClass is Class<*>) {
            throw IllegalArgumentException("Internal error: TypeReference constructed without actual type information")
        }
        (superClass as ParameterizedType).getActualTypeArguments()[0]
    }
}




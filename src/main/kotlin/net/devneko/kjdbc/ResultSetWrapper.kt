package net.devneko.kjdbc

import java.io.Closeable
import java.lang.reflect.Type
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.jvm.javaType

class ResultSetWrapper(
        private val statement: Statement,
        private val resultSet: ResultSet
) : ResultSet by resultSet, Closeable
{
    fun <T:Any> forEach(clazz: KClass<T>, callback:(T)->Unit) {
        use {
            while ( this.next() ) {
                val obj:T = read(clazz)
                callback.invoke(obj)
            }
        }
    }

    fun <T:Any> readAll(clazz: KClass<T>):List<T> {
        val result = arrayListOf<T>()
        use {
            while ( this.next() ) {
                result.add(read(clazz))
            }
        }
        return result
    }

    inline fun <reified T:Any> readOne():T {
        readOneOrNull<T>()?.let {
            return it
        }
        throw SQLException("record is not found.")
    }

    inline fun <reified T:Any> readOneOrNull():T? {
        if ( this.isClosed() || !this.next() ) {
            return null
        }
        val clazz = T::class
        return read(clazz)
    }

    fun <T:Any> read(clazz: KClass<T>):T {
        val constructor = clazz.constructors.singleOrNull()
        constructor ?: throw SQLException("constructor is not found.")

        val values = hashMapOf<KParameter, Any>();
        IntRange(1, this.metaData.columnCount).forEach {
            val columnName = this.metaData.getColumnName(it)
            val camelCaseName = underscoreSeparatedStringToCamelCase(columnName)

            constructor.parameters.forEach params@ {
                if ( camelCaseName.equals(it.name) ) {
                    val v = getByType(columnName,it.type.javaType)
                    values.put(it, v)
                    return@params
                }
            }
        }

        return constructor.callBy(values)
    }

    inline fun <reified T:Any> get(name:String):T? {
        val type = fullType<T>()
        return getByType(name,type.type) as T?
    }


    fun getByType(name:String, type: Type):Any {
        when ( type.typeName ) {
            "java.lang.Long" -> return this.getLong(name)
            "java.lang.Integer" -> return this.getInt(name)
            "java.lang.Short" -> return this.getShort(name)
            "java.lang.Byte" -> return this.getByte(name)
            "java.lang.Float" -> return this.getFloat(name)
            "java.lang.Double" -> return this.getDouble(name)
            "java.lang.String" -> return this.getString(name)
            "java.lang.Boolean" -> this.getBoolean(name)
            "kotlin.ByteArray" -> this.getBytes(name)
            "java.net.URL" -> this.getURL(name)
            "java.math.BigDecimal" -> return this.getBigDecimal(name)
            "java.util.Date" -> return Date(this.getDate(name).time)
            "java.sql.Blob" -> this.getBlob(name)
            "java.sql.Clob" -> this.getClob(name)
        }
        return this.getObject(name)
    }


    override fun close() {
        if ( !resultSet.isClosed ) {
            resultSet.close()
        }
        if ( !statement.isClosed ) {
            statement.close()
        }
    }

    fun underscoreSeparatedStringToCamelCase(str:String):String {
        return Regex("_([a-z])").replace(str, { r ->
            val g = r.groups[1]
            g?.value?.capitalize() ?: ""
        })
    }
}

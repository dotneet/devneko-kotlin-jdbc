package net.devneko.kjdbc

import java.io.Closeable
import java.lang.reflect.Type
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.jvm.javaType

class ResultSetWrapper(
        private val statement: Statement,
        private val resultSet: ResultSet
) : ResultSet by resultSet, Closeable
{

    fun forEach(callback:ResultSetWrapper.()->Unit) {
        use {
            while (this.next()) {
                this.callback()
            }
        }
    }

    fun <T:Any> forEach(clazz: KClass<T>, callback:(T)->Unit) {
        use {
            while ( this.next() ) {
                val obj:T = read(clazz)
                callback.invoke(obj)
            }
        }
    }

    fun <T:Any> map(callback:ResultSetWrapper.()->T):List<T> {
        val result = arrayListOf<T>()
        use {
            while (this.next()) {
                result.add(this.callback())
            }
        }
        return result
    }

    fun <T:Any> next(callback:ResultSetWrapper.()->T):T {
        this.next()
        return this.callback()
    }

    inline fun <reified T:Any> readAll():List<T> {
        val result = arrayListOf<T>()
        use {
            while ( this.next() ) {
                result.add(read(T::class))
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

        val values = hashMapOf<KParameter, Any?>();
        IntRange(1, this.metaData.columnCount).forEach {
            val columnName = this.metaData.getColumnName(it)
            val camelCaseName = underscoreSeparatedStringToCamelCase(columnName)

            constructor.parameters.forEach params@ {
                if ( columnName.equals(it.name) || camelCaseName.equals(it.name) ) {
                    val cls = classForName(it.type.javaType.typeName)
                    val annOrNull = cls?.getDeclaredAnnotation(EnumMappingField::class.java) ?: null
                    annOrNull?.let { ann ->
                        cls ?: throw IllegalStateException()
                        val dbValue =
                            when (ann.type) {
                                EnumMappingFieldType.INT ->
                                    getByType(columnName, Int::class.java)
                                EnumMappingFieldType.LONG ->
                                    getByType(columnName, Long::class.java)
                                EnumMappingFieldType.STRING ->
                                    getByType(columnName, String::class.java)
                            }
                        val valuesMethod = cls.getDeclaredMethod("values")
                        val list = valuesMethod.invoke(null) as Array<Any>
                        list.forEach { enumValue ->
                            val field = enumValue.javaClass.getDeclaredField(ann.fieldName)
                            field.isAccessible = true
                            val enumRawValue = field.get(enumValue)
                            if ( enumRawValue == dbValue ) {
                                values.put(it, enumValue)
                            }
                        }
                    } ?: run {
                        val v = getByType(columnName,it.type.javaType)
                        values.put(it, v)
                    }
                    return@params
                }
            }
        }

        return constructor.callBy(values)
    }

    private val classCache = hashMapOf<String,Class<*>?>()
    private fun classForName(name:String):Class<*>? {
        if ( classCache.keys.contains(name) ) {
            return classCache[name]
        }
        val clazz = try {
            Class.forName(name)
        } catch (e:ClassNotFoundException) {
            null
        }
        classCache[name] = clazz
        return clazz
    }

    inline fun <reified T:Any> get(name:String):T {
        val type = fullType<T>()
        return getByType(name,type.type) as T
    }

    fun getByType(name:String, type: Type):Any? {
        when ( type.typeName ) {
            "long" -> return this.getLong(name)
            "java.lang.Long" -> return this.getLong(name)
            "int" -> return this.getInt(name)
            "java.lang.Integer" -> return this.getInt(name)
            "short" -> return this.getShort(name)
            "java.lang.Short" -> return this.getShort(name)
            "byte" -> return this.getByte(name)
            "java.lang.Byte" -> return this.getByte(name)
            "float" -> return this.getFloat(name)
            "java.lang.Float" -> return this.getFloat(name)
            "double" -> return this.getDouble(name)
            "java.lang.Double" -> return this.getDouble(name)
            "java.lang.String" -> return this.getString(name)
            "boolean" -> return this.getBoolean(name)
            "java.lang.Boolean" -> return this.getBoolean(name)
            "kotlin.ByteArray" -> return this.getBytes(name)
            "java.net.URL" -> return this.getURL(name)
            "java.math.BigDecimal" -> return this.getBigDecimal(name)
            "java.util.Date" -> return Date(this.getDate(name).time)
            "java.time.ZonedDateTime" -> {
                val ts = this.getTimestamp(name)
                return ts?.let { it.toInstant().atZone(ZoneId.systemDefault()) } ?: null
            }
            "java.time.LocalDateTime" -> {
                val ts = this.getTimestamp(name)
                return ts?.let { it.toLocalDateTime().atZone(ZoneId.systemDefault()).toLocalDateTime() } ?: null
            }
            "java.sql.Blob" -> return this.getBlob(name)
            "java.sql.Clob" -> return this.getClob(name)
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
        return Regex("_([a-z])").replace(str.toLowerCase(), { r ->
            val g = r.groups[1]
            g?.value?.capitalize() ?: ""
        })
    }
}

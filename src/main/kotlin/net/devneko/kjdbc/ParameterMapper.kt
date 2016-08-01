package net.devneko.kjdbc

import java.io.InputStream
import java.io.Reader
import java.math.BigDecimal
import java.net.URL
import java.sql.*
import java.sql.Array
import java.sql.Date
import java.time.Instant
import java.time.ZonedDateTime
import java.util.*

class ParameterMapper
(
        private val nameIndex:Map<String,Int>,
        val statement: PreparedStatement
)
{
    fun set(name:String, value:Int) {
        statement.setInt(getIndex(name), value)
    }

    fun set(name:String, value:Long) {
        statement.setLong(getIndex(name), value)
    }

    fun set(name:String, value:String) {
        statement.setString(getIndex(name), value)
    }

    fun set(name:String, value:Short) {
        statement.setShort(getIndex(name), value)
    }

    fun set(name:String, value:Byte) {
        statement.setByte(getIndex(name), value)
    }

    fun set(name:String, value:ByteArray) {
        statement.setBytes(getIndex(name), value)
    }

    fun set(name:String, value:Boolean) {
        statement.setBoolean(getIndex(name), value)
    }

    fun set(name:String, value: Array) {
        statement.setArray(getIndex(name), value)
    }

    fun setAsciiStream(name:String, value: InputStream) {
        statement.setAsciiStream(getIndex(name), value)
    }

    fun setAsciiStream(name:String, value: InputStream, length:Int) {
        statement.setAsciiStream(getIndex(name), value, length)
    }

    fun setAsciiStream(name:String, value: InputStream, length:Long) {
        statement.setAsciiStream(getIndex(name), value, length)
    }

    fun set(name:String, value: BigDecimal) {
        statement.setBigDecimal(getIndex(name), value)
    }

    fun setBinaryStream(name:String, value: InputStream) {
        statement.setBinaryStream(getIndex(name), value)
    }

    fun setBinaryStream(name:String, value: InputStream, length:Int) {
        statement.setBinaryStream(getIndex(name), value, length)
    }

    fun setBinaryStream(name:String, value: InputStream, length:Long) {
        statement.setBinaryStream(getIndex(name), value, length)
    }

    fun setBlob(name:String, value: InputStream) {
        statement.setBlob(getIndex(name), value)
    }

    fun setBlob(name:String, value: InputStream, length:Long) {
        statement.setBlob(getIndex(name), value, length)
    }

    fun set(name:String, value: Reader) {
        statement.setCharacterStream(getIndex(name), value)
    }
    fun set(name:String, value: Reader, length:Int) {
        statement.setCharacterStream(getIndex(name), value, length)
    }
    fun set(name:String, value: Reader, length:Long) {
        statement.setCharacterStream(getIndex(name), value, length)
    }

    fun setClob(name:String, value: Clob) {
        statement.setClob(getIndex(name), value)
    }
    fun setClob(name:String, value: Reader) {
        statement.setClob(getIndex(name), value)
    }
    fun setClob(name:String, value: Reader, length:Long) {
        statement.setClob(getIndex(name), value, length)
    }
    fun set(name:String, value: Date) {
        statement.setDate(getIndex(name), value)
    }
    fun set(name:String, value: Date, cal: Calendar) {
        statement.setDate(getIndex(name), value, cal)
    }
    fun set(name:String, value: Instant) {
        val date = java.sql.Date(java.util.Date.from(value).time)
        statement.setDate(getIndex(name), date)
    }
    fun set(name:String, value: java.util.Date) {
        val date = Date(value.time)
        statement.setDate(getIndex(name), date)
    }
    fun set(name:String, value: ZonedDateTime) {
        val date = Date(java.util.Date.from(value.toInstant()).time)
        statement.setDate(getIndex(name), date)
    }
    fun set(name:String, value: Double) {
        statement.setDouble(getIndex(name), value)
    }
    fun set(name:String, value: Float) {
        statement.setFloat(getIndex(name), value)
    }
    fun setNCharacterStream(name:String, value: Reader) {
        statement.setNCharacterStream(getIndex(name), value)
    }
    fun setNCharacterStream(name:String, value: Reader, length:Long) {
        statement.setNCharacterStream(getIndex(name), value, length)
    }
    fun setNClob(name:String, value: NClob) {
        statement.setNClob(getIndex(name), value)
    }
    fun setNClob(name:String, value: Reader) {
        statement.setNClob(getIndex(name), value)
    }
    fun setNClob(name:String, value: Reader, length:Long) {
        statement.setNClob(getIndex(name), value, length)
    }
    fun setNull(name:String, sqlType:Int) {
        statement.setNull(getIndex(name), sqlType)
    }
    fun setNull(name:String, sqlType:Int, typeName:String) {
        statement.setNull(getIndex(name), sqlType, typeName)
    }
    fun setObject(name:String, value:Any) {
        statement.setObject(getIndex(name), value)
    }
    fun setObject(name:String, value:Any, sqlType:Int) {
        statement.setObject(getIndex(name), value, sqlType)
    }
    fun setObject(name:String, value:Any, sqlType: SQLType) {
        statement.setObject(getIndex(name), value, sqlType)
    }
    fun setObject(name:String, value:Any, sqlType: SQLType, scale:Int) {
        statement.setObject(getIndex(name), value, sqlType, scale)
    }
    fun setObject(name:String, value:Any, sqlType:Int, scale:Int) {
        statement.setObject(getIndex(name), value, sqlType, scale)
    }
    fun set(name:String, value: Ref) {
        statement.setRef(getIndex(name), value)
    }
    fun set(name:String, value: RowId) {
        statement.setRowId(getIndex(name), value)
    }
    fun set(name:String, value:()->ByteArray) {
        statement.setRowId(getIndex(name), value)
    }
    fun set(name:String, value: Time) {
        statement.setTime(getIndex(name), value)
    }
    fun setTime(name:String, value: Date) {
        val time = Time(value.time)
        statement.setTime(getIndex(name), time)
    }
    fun setTime(name:String, value: Time) {
        statement.setTime(getIndex(name), value)
    }
    fun set(name:String, value: Timestamp) {
        statement.setTimestamp(getIndex(name), value)
    }
    fun set(name:String, value: Timestamp, cal: Calendar) {
        statement.setTimestamp(getIndex(name), value, cal)
    }
    fun setTimestamp(name:String, value: java.util.Date) {
        val t = Timestamp(value.time)
        statement.setTimestamp(getIndex(name), t)
    }
    fun setTimestamp(name:String, value: java.util.Date, cal: Calendar) {
        val t = Timestamp(value.time)
        statement.setTimestamp(getIndex(name), t, cal)
    }
    fun setTimestamp(name:String, value: Timestamp) {
        statement.setTimestamp(getIndex(name), value)
    }
    fun setTimestamp(name:String, value: ZonedDateTime) {
        statement.setTimestamp(getIndex(name), Timestamp.from(value.toInstant()))
    }
    fun setTimestamp(name:String, value: Instant) {
        statement.setTimestamp(getIndex(name), Timestamp.from(value))
    }
    fun setTimestamp(name:String, value: Timestamp, cal: Calendar) {
        statement.setTimestamp(getIndex(name), value, cal)
    }
    fun set(name:String, value: URL) {
        statement.setURL(getIndex(name), value)
    }

    private fun getIndex(name:String):Int {
        return nameIndex[name] ?: throw SQLException("placeholder \":$name\" is not found in supplied sql.")
    }

}

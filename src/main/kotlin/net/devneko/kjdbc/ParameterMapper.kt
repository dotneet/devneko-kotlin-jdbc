package net.devneko.kjdbc

import java.io.InputStream
import java.io.Reader
import java.math.BigDecimal
import java.net.URL
import java.sql.*
import java.sql.Array
import java.sql.Date
import java.util.*

class ParameterMapper
(
        private val nameIndex:Map<String,Int>,
        private val preparedStatement: PreparedStatement
)
{
    fun set(name:String, value:Int) {
        preparedStatement.setInt(getIndex(name), value)
    }

    fun set(name:String, value:Long) {
        preparedStatement.setLong(getIndex(name), value)
    }

    fun set(name:String, value:String) {
        preparedStatement.setString(getIndex(name), value)
    }

    fun set(name:String, value:Short) {
        preparedStatement.setShort(getIndex(name), value)
    }

    fun set(name:String, value:Byte) {
        preparedStatement.setByte(getIndex(name), value)
    }

    fun set(name:String, value:ByteArray) {
        preparedStatement.setBytes(getIndex(name), value)
    }

    fun set(name:String, value:Boolean) {
        preparedStatement.setBoolean(getIndex(name), value)
    }

    fun set(name:String, value: Array) {
        preparedStatement.setArray(getIndex(name), value)
    }

    fun setAsciiStream(name:String, value: InputStream) {
        preparedStatement.setAsciiStream(getIndex(name), value)
    }

    fun setAsciiStream(name:String, value: InputStream, length:Int) {
        preparedStatement.setAsciiStream(getIndex(name), value, length)
    }

    fun setAsciiStream(name:String, value: InputStream, length:Long) {
        preparedStatement.setAsciiStream(getIndex(name), value, length)
    }

    fun set(name:String, value: BigDecimal) {
        preparedStatement.setBigDecimal(getIndex(name), value)
    }

    fun setBinaryStream(name:String, value: InputStream) {
        preparedStatement.setBinaryStream(getIndex(name), value)
    }

    fun setBinaryStream(name:String, value: InputStream, length:Int) {
        preparedStatement.setBinaryStream(getIndex(name), value, length)
    }

    fun setBinaryStream(name:String, value: InputStream, length:Long) {
        preparedStatement.setBinaryStream(getIndex(name), value, length)
    }

    fun setBlob(name:String, value: InputStream) {
        preparedStatement.setBlob(getIndex(name), value)
    }

    fun setBlob(name:String, value: InputStream, length:Long) {
        preparedStatement.setBlob(getIndex(name), value, length)
    }

    fun set(name:String, value: Reader) {
        preparedStatement.setCharacterStream(getIndex(name), value)
    }
    fun set(name:String, value: Reader, length:Int) {
        preparedStatement.setCharacterStream(getIndex(name), value, length)
    }
    fun set(name:String, value: Reader, length:Long) {
        preparedStatement.setCharacterStream(getIndex(name), value, length)
    }

    fun setClob(name:String, value: Clob) {
        preparedStatement.setClob(getIndex(name), value)
    }
    fun setClob(name:String, value: Reader) {
        preparedStatement.setClob(getIndex(name), value)
    }
    fun setClob(name:String, value: Reader, length:Long) {
        preparedStatement.setClob(getIndex(name), value, length)
    }
    fun set(name:String, value: Date) {
        preparedStatement.setDate(getIndex(name), value)
    }
    fun set(name:String, value: Date, cal: Calendar) {
        preparedStatement.setDate(getIndex(name), value, cal)
    }
    fun set(name:String, value: java.util.Date) {
        val date = Date(value.time)
        preparedStatement.setDate(getIndex(name), date)
    }
    fun set(name:String, value: Double) {
        preparedStatement.setDouble(getIndex(name), value)
    }
    fun set(name:String, value: Float) {
        preparedStatement.setFloat(getIndex(name), value)
    }
    fun setNCharacterStream(name:String, value: Reader) {
        preparedStatement.setNCharacterStream(getIndex(name), value)
    }
    fun setNCharacterStream(name:String, value: Reader, length:Long) {
        preparedStatement.setNCharacterStream(getIndex(name), value, length)
    }
    fun setNClob(name:String, value: NClob) {
        preparedStatement.setNClob(getIndex(name), value)
    }
    fun setNClob(name:String, value: Reader) {
        preparedStatement.setNClob(getIndex(name), value)
    }
    fun setNClob(name:String, value: Reader, length:Long) {
        preparedStatement.setNClob(getIndex(name), value, length)
    }
    fun setNull(name:String, sqlType:Int) {
        preparedStatement.setNull(getIndex(name), sqlType)
    }
    fun setNull(name:String, sqlType:Int, typeName:String) {
        preparedStatement.setNull(getIndex(name), sqlType, typeName)
    }
    fun setObject(name:String, value:Any) {
        preparedStatement.setObject(getIndex(name), value)
    }
    fun setObject(name:String, value:Any, sqlType:Int) {
        preparedStatement.setObject(getIndex(name), value, sqlType)
    }
    fun setObject(name:String, value:Any, sqlType: SQLType) {
        preparedStatement.setObject(getIndex(name), value, sqlType)
    }
    fun setObject(name:String, value:Any, sqlType: SQLType, scale:Int) {
        preparedStatement.setObject(getIndex(name), value, sqlType, scale)
    }
    fun setObject(name:String, value:Any, sqlType:Int, scale:Int) {
        preparedStatement.setObject(getIndex(name), value, sqlType, scale)
    }
    fun set(name:String, value: Ref) {
        preparedStatement.setRef(getIndex(name), value)
    }
    fun set(name:String, value: RowId) {
        preparedStatement.setRowId(getIndex(name), value)
    }
    fun set(name:String, value:()->ByteArray) {
        preparedStatement.setRowId(getIndex(name), value)
    }
    fun set(name:String, value: Time) {
        preparedStatement.setTime(getIndex(name), value)
    }
    fun setTime(name:String, value: Date) {
        val time = Time(value.time)
        preparedStatement.setTime(getIndex(name), time)
    }
    fun setTime(name:String, value: Time) {
        preparedStatement.setTime(getIndex(name), value)
    }
    fun set(name:String, value: Timestamp) {
        preparedStatement.setTimestamp(getIndex(name), value)
    }
    fun set(name:String, value: Timestamp, cal: Calendar) {
        preparedStatement.setTimestamp(getIndex(name), value, cal)
    }
    fun setTimestamp(name:String, value: java.util.Date) {
        val t = Timestamp(value.time)
        preparedStatement.setTimestamp(getIndex(name), t)
    }
    fun setTimestamp(name:String, value: java.util.Date, cal: Calendar) {
        val t = Timestamp(value.time)
        preparedStatement.setTimestamp(getIndex(name), t, cal)
    }
    fun setTimestamp(name:String, value: Timestamp) {
        preparedStatement.setTimestamp(getIndex(name), value)
    }
    fun setTimestamp(name:String, value: Timestamp, cal: Calendar) {
        preparedStatement.setTimestamp(getIndex(name), value, cal)
    }
    fun set(name:String, value: URL) {
        preparedStatement.setURL(getIndex(name), value)
    }

    private fun getIndex(name:String):Int {
        return nameIndex[name] ?: throw SQLException("placeholder \":$name\" is not found in supplied sql.")
    }

}

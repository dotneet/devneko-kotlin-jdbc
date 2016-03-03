package net.devneko.kjdbc

import java.io.InputStream
import java.io.Reader
import java.math.BigDecimal
import java.net.URL
import java.sql.*
import java.sql.Date
import java.util.*

open class UpdateParameterBuilder {
    protected val _nameList = arrayListOf<String>()
    public val nameList:List<String>
        get() = _nameList.toList()

    protected val _setList = arrayListOf<(ParameterMapper)->Unit>()



    fun parameterMapper(): ParameterMapper.()->Unit {
        return {
           _setList.forEach { it(this) }
        }
    }

    fun set(name:String, value:Int) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }

    fun set(name:String, value:Long) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }

    fun set(name:String, value:String) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }

    fun set(name:String, value:Short) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }

    fun set(name:String, value:Byte) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }

    fun set(name:String, value:ByteArray) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }

    fun set(name:String, value:Boolean) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }

    fun set(name:String, value:java.sql.Array) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }

    fun setAsciiStream(name:String, value: InputStream) {
        _nameList.add(name)
        _setList.add({ m -> m.setAsciiStream(name, value) })
    }

    fun setAsciiStream(name:String, value: InputStream, length:Int) {
        _nameList.add(name)
        _setList.add({ m -> m.setAsciiStream(name, value, length) })
    }

    fun set(name:String, value: BigDecimal) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }

    fun setBinaryStream(name:String, value: InputStream) {
        _nameList.add(name)
        _setList.add({ m -> m.setBinaryStream(name, value) })
    }

    fun setBinaryStream(name:String, value: InputStream, length:Int) {
        _nameList.add(name)
        _setList.add({ m -> m.setBinaryStream(name, value, length) })
    }

    fun setBinaryStream(name:String, value: InputStream, length:Long) {
        _nameList.add(name)
        _setList.add({ m -> m.setBinaryStream(name, value, length) })
    }

    fun setBlob(name:String, value: InputStream) {
        _nameList.add(name)
        _setList.add({ m -> m.setBlob(name, value) })
    }

    fun setBlob(name:String, value: InputStream, length:Long) {
        _nameList.add(name)
        _setList.add({ m -> m.setBlob(name, value, length) })
    }

    fun set(name:String, value: Reader) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }
    fun set(name:String, value: Reader, length:Int) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value, length) })
    }
    fun set(name:String, value: Reader, length:Long) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value, length) })
    }

    fun setClob(name:String, value: Clob) {
        _nameList.add(name)
        _setList.add({ m -> m.setClob(name, value) })
    }
    fun setClob(name:String, value: Reader) {
        _nameList.add(name)
        _setList.add({ m -> m.setClob(name, value) })
    }
    fun setClob(name:String, value: Reader, length:Long) {
        _nameList.add(name)
        _setList.add({ m -> m.setClob(name, value, length) })
    }
    fun set(name:String, value: java.sql.Date) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }
    fun set(name:String, value: java.sql.Date, cal:Calendar) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value, cal) })
    }
    fun set(name:String, value: java.util.Date) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }
    fun set(name:String, value: Double) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }
    fun set(name:String, value: Float) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }
    fun setNCharacterStream(name:String, value: Reader) {
        _nameList.add(name)
        _setList.add({ m -> m.setNCharacterStream(name, value) })
    }
    fun setNCharacterStream(name:String, value: Reader, length:Long) {
        _nameList.add(name)
        _setList.add({ m -> m.setNCharacterStream(name, value, length) })
    }
    fun setNClob(name:String, value: NClob) {
        _nameList.add(name)
        _setList.add({ m -> m.setNClob(name, value) })
    }
    fun setNClob(name:String, value: Reader) {
        _nameList.add(name)
        _setList.add({ m -> m.setNClob(name, value) })
    }
    fun setNClob(name:String, value: Reader, length:Long) {
        _nameList.add(name)
        _setList.add({ m -> m.setNClob(name, value, length) })
    }
    fun setNull(name:String, sqlType:Int) {
        _nameList.add(name)
        _setList.add({ m -> m.setNull(name, sqlType) })
    }
    fun setNull(name:String, sqlType:Int, typeName:String) {
        _nameList.add(name)
        _setList.add({ m -> m.setNull(name, sqlType, typeName) })
    }
    fun setObject(name:String, value:Any) {
        _nameList.add(name)
        _setList.add({ m -> m.setObject(name, value) })
    }
    fun setObject(name:String, value:Any, sqlType:Int) {
        _nameList.add(name)
        _setList.add({ m -> m.setObject(name, value, sqlType) })
    }
    fun setObject(name:String, value:Any, sqlType:SQLType) {
        _nameList.add(name)
        _setList.add({ m -> m.setObject(name, value, sqlType) })
    }
    fun setObject(name:String, value:Any, sqlType:SQLType, scale:Int) {
        _nameList.add(name)
        _setList.add({ m -> m.setObject(name, value, sqlType,scale) })
    }
    fun setObject(name:String, value:Any, sqlType:Int, scale:Int) {
        _nameList.add(name)
        _setList.add({ m -> m.setObject(name, value, sqlType, scale) })
    }
    fun set(name:String, value:Ref) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }
    fun set(name:String, value:RowId) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }
    fun set(name:String, value:()->ByteArray) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }
    fun set(name:String, value:Time) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }
    fun setTime(name:String, value:Date) {
        _nameList.add(name)
        _setList.add({ m -> m.setTime(name, value) })
    }
    fun setTime(name:String, value:Time) {
        _nameList.add(name)
        _setList.add({ m -> m.setTime(name, value) })
    }
    fun set(name:String, value:Timestamp) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }
    fun set(name:String, value:Timestamp, cal:Calendar) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value,cal) })
    }
    fun setTimestamp(name:String, value:java.util.Date) {
        _nameList.add(name)
        _setList.add({ m -> m.setTimestamp(name, value) })
    }
    fun setTimestamp(name:String, value:java.util.Date, cal:Calendar) {
        _nameList.add(name)
        _setList.add({ m -> m.setTimestamp(name, value, cal) })
    }
    fun setTimestamp(name:String, value:Timestamp) {
        _nameList.add(name)
        _setList.add({ m -> m.setTimestamp(name, value) })
    }
    fun setTimestamp(name:String, value:Timestamp, cal:Calendar) {
        _nameList.add(name)
        _setList.add({ m -> m.setTimestamp(name, value, cal) })
    }
    fun set(name:String, value: URL) {
        _nameList.add(name)
        _setList.add({ m -> m.set(name, value) })
    }

}

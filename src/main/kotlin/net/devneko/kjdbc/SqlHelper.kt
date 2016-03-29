package net.devneko.kjdbc

import net.devneko.kjdbc.*
import java.sql.Connection
import java.sql.PreparedStatement

open class SqlHelper
(
        val connection:Connection
)
{
    fun query(sql:String,block: (ParameterMapper.()->Unit)? = null): ResultSetWrapper {
        val analyzeResult = SqlAnalyzer.analyze(sql)
        val ps = connection.prepareStatement(analyzeResult.sql)
        val mapper = ParameterMapper(analyzeResult.nameIndex, ps)
        block?.let {
            mapper.it()
        }
        return ResultSetWrapper(ps, ps.executeQuery())
    }

    fun insert(tableName:String, block:UpdateParameterBuilder.()->Unit):Int {
        val builder = UpdateParameterBuilder()
        builder.block()
        val nameList = builder.nameList
        val sql = "INSERT INTO $tableName(" +
                nameList.map{ "`$it`" }.joinToString(",") +
                ") VALUES(" +
                nameList.map{ ":$it" }.joinToString(",") +
                ")"
        return updateSql(sql, builder.parameterMapper())
    }

    fun update(tableName:String, block:UpdateParameterAndConditionBulder.()->Unit):Int {
        val builder = UpdateParameterAndConditionBulder()
        builder.block()
        val nameList = builder.nameList

        val setItems = nameList.map{ "`$it` = :$it" }.joinToString(",")
        val whereClause = if ( builder.condition.isNotEmpty() ) "WHERE ${builder.condition}" else ""
        val sql = """
            UPDATE $tableName SET $setItems $whereClause
        """
        return updateSql(sql, builder.parameterMapper())
    }

    fun delete(tableName:String, condition:String, block: ParameterMapper.()->Unit):Int {
        val sql = "DELETE FROM $tableName WHERE $condition";
        return updateSql(sql, block)
    }

    fun prepare(sql:String, block: ParameterMapper.()->Unit):PreparedStatement {
        val analyzeResult = SqlAnalyzer.analyze(sql)
        val ps = connection.prepareStatement(analyzeResult.sql)
        val mapper = ParameterMapper(analyzeResult.nameIndex, ps)
        mapper.block()
        return ps
    }

    fun updateSql(sql:String, block: ParameterMapper.()->Unit):Int {
        val ps = prepare(sql, block)
        try {
            return ps.executeUpdate()
        } finally {
            ps.close()
        }
    }

}

package com.kotring.repository

import net.devneko.kjdbc.ParameterMapper
import net.devneko.kjdbc.ResultSetWrapper
import net.devneko.kjdbc.SqlAnalyzer
import java.sql.Connection

open class SqlHelper
(
        private val connection:Connection
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

    fun update(sql:String, block:ParameterMapper.()->Unit):Int {
        val analyzeResult = SqlAnalyzer.analyze(sql)
        val ps = connection.prepareStatement(analyzeResult.sql)
        val mapper = ParameterMapper(analyzeResult.nameIndex, ps)
        try {
            mapper.block()
            return ps.executeUpdate()
        } finally {
            ps.close()
        }
    }

}

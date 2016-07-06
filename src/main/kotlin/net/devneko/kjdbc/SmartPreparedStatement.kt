package net.devneko.kjdbc

import java.sql.PreparedStatement

class SmartPreparedStatement(private val mapper:ParameterMapper) : PreparedStatement by mapper.statement {

    fun execute(block:ParameterMapper.()->Unit) {
        mapper.block()
        execute()
    }

    fun executeUpdate(block:ParameterMapper.()->Unit):Int {
        mapper.block()
        return executeUpdate()
    }
}

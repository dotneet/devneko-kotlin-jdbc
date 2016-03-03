package net.devneko.kjdbc

import java.sql.SQLException

public object SqlAnalyzer {
    private val namePlaceHolderRegex = Regex(":[a-zA-Z0-9_-]+")

    fun analyze(sql:String): SqlAnalyzeResult {
        val nameIndex = hashMapOf<String,Int>()

        namePlaceHolderRegex.findAll(sql).forEachIndexed { i, matchResult ->
            val name = matchResult.value.substring(1)
            if ( nameIndex.containsKey(name) ) {
                throw SQLException("parameter name \":$name\" is duplicated in supplied sql.")
            }
            nameIndex += (name to (i + 1))
        }

        val newSql = if ( nameIndex.size == 0 ) sql else namePlaceHolderRegex.replace(sql, "?")

        return SqlAnalyzeResult(newSql, nameIndex)
    }
}

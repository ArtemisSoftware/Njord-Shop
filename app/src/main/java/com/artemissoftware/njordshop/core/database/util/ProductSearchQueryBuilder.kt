package com.artemissoftware.njordshop.core.database.util

import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

fun buildSearchQuery(terms: List<String>): SupportSQLiteQuery {
    val base = StringBuilder("SELECT * FROM products WHERE ")
    val args = mutableListOf<Any>()

    terms.forEachIndexed { index, term ->
        if (index > 0) base.append(" AND ")
        base.append("(LOWER(title) LIKE ? OR LOWER(description) LIKE ?)")
        val wildcard = "%${term.lowercase()}%"
        args.add(wildcard)
        args.add(wildcard)
    }

    return SimpleSQLiteQuery(base.toString(), args.toTypedArray())
}
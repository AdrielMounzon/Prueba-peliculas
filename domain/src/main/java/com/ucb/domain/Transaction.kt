package com.ucb.domain
import java.util.Date

sealed class Transaction {
    data class Income(
        val id: String,
        val nombre: String,
        val precio: Double,
        val descripcion: String,
        val fecha: Date
    ) : Transaction()

    data class Expense(
        val id: String,
        val nombre: String,
        val precio: Double,
        val descripcion: String,
        val fecha: Date
    ) : Transaction()
}

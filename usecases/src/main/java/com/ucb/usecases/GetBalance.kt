package com.ucb.usecases

import com.ucb.data.TransactionRepository
import com.ucb.domain.Balance
import com.ucb.domain.Transaction

class GetBalance(
    private val transactionRepository: TransactionRepository
) {
    suspend fun invoke(): Balance {
        val transactions = transactionRepository.getAllTransactions()
        val totalIncome = transactions.filterIsInstance<Transaction.Income>().sumOf { it.precio }
        val totalExpenses = transactions.filterIsInstance<Transaction.Expense>().sumOf { it.precio }
        val netBalance = totalIncome - totalExpenses
        return Balance(totalIncome, totalExpenses, netBalance)
    }
}
package com.ucb.data

import com.ucb.data.transactions.ITransactionLocalDataSource
import com.ucb.domain.Transaction
import com.ucb.domain.Transaction.Expense
import com.ucb.domain.Transaction.Income

class TransactionRepository(
    val localDataSource: ITransactionLocalDataSource
) {

    suspend fun registerIncome(income: Income) {
        localDataSource.registerIncome(income)
    }

    suspend fun registerExpense(expense: Expense) {
        localDataSource.registerExpense(expense)
    }

    suspend fun getAllTransactions(): List<Transaction> {
        return localDataSource.getAllTransactions().sortedByDescending { it.fecha }
    }

    suspend fun deleteTransaction(transactionId: String) {
        localDataSource.deleteTransaction(transactionId)
    }

    suspend fun getTransactions(): List<Transaction> {
        return localDataSource.getTransactions()
    }
}
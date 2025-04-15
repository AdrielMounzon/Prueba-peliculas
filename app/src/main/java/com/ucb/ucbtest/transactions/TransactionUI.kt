package com.ucb.ucbtest.transactions

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucb.domain.Transaction
import com.ucb.ucbtest.R
import java.util.Date

@Composable
fun TransactionUI(viewModel: TransactionViewModel = hiltViewModel()) {
    var incomeName by remember { mutableStateOf("") }
    var incomePrice by remember { mutableStateOf("") }
    var incomeDescription by remember { mutableStateOf("") }
    var expenseName by remember { mutableStateOf("") }
    var expensePrice by remember { mutableStateOf("") }
    var expenseDescription by remember { mutableStateOf("") }

    val transactionState by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Register Income or Expense")

            Spacer(modifier = Modifier.height(16.dp))

            // Income Input Fields
            OutlinedTextField(
                value = incomeName,
                onValueChange = { incomeName = it },
                label = { Text("Income Name") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            )
            OutlinedTextField(
                value = incomePrice,
                onValueChange = { incomePrice = it },
                label = { Text("Income Price") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            )
            OutlinedTextField(
                value = incomeDescription,
                onValueChange = { incomeDescription = it },
                label = { Text("Income Description") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            )
            Button(onClick = {
                viewModel.registerIncomeTransaction(Transaction.Income(0, incomeName, incomePrice.toDouble(), incomeDescription, Date()))
            }) {
                Text("Register Income")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Expense Input Fields
            OutlinedTextField(
                value = expenseName,
                onValueChange = { expenseName = it },
                label = { Text("Expense Name") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            )
            OutlinedTextField(
                value = expensePrice,
                onValueChange = { expensePrice = it },
                label = { Text("Expense Price") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            )
            OutlinedTextField(
                value = expenseDescription,
                onValueChange = { expenseDescription = it },
                label = { Text("Expense Description") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            )
            Button(onClick = {
                viewModel.registerExpenseTransaction(Transaction.Expense(0, expenseName, expensePrice.toDouble(), expenseDescription, Date()))
            }) {
                Text("Register Expense")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Transaction List and Deletion
            when (val state = transactionState) {
                is TransactionViewModel.TransactionState.Success -> {
                    state.transactions.forEach { transaction ->
                        Column {
                            Text("Name: ${transaction.nombre}")
                            Text("Price: ${transaction.precio}")
                            Text("Description: ${transaction.descripcion}")
                            Text("Date: ${transaction.fecha}")
                            Button(onClick = {
                                viewModel.deleteTransaction(transaction.id.toString())
                            }) {
                                Text("Delete")
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
                is TransactionViewModel.TransactionState.Error -> {
                    Text("Error: ${state.message}")
                }
                is TransactionViewModel.TransactionState.Init -> {
                    Text("No transactions available")
                }
            }
        }
    }
}

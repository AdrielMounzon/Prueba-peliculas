package com.ucb.framework.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TransactionEntity::class], version = 1, exportSchema = false)
abstract class TransactionRoomDatabase : RoomDatabase() {

    abstract fun transactionDAO(): ITransactionDAO

    companion object {
        @Volatile
        private var INSTANCE: TransactionRoomDatabase? = null

        fun getDatabase(context: Context): TransactionRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    TransactionRoomDatabase::class.java,
                    "transaction_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}

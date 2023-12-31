package be.ucll.tasklist

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "investment_database_table")
data class Database__C_Asset(
    @PrimaryKey(autoGenerate = true)
    val investmentId: Long = 0L,
    val userID: Long,
    val accountID: Long = 0L,
    val investmentType: String,
    val name: String,
    val ticker: String,
    val quantity: Int,
    val lastValue: Double,
    val lastUpdated: String
)
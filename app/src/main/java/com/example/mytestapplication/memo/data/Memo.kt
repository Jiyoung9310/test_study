package com.example.mytestapplication.memo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by jiyoung on 16/12/2019
 */
@Entity(tableName = "memo")
data class Memo(
    @PrimaryKey @ColumnInfo(name = "id") val memoId: String,
    val title: String,
    val category: String,
    val description: String,
    val writeDt: Calendar = Calendar.getInstance()
    )
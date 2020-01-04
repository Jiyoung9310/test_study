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
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val memoId: Long = 0,
    val title: String,
    val category: String, //할일, 아이디어, 기타
    val description: String,
    val writeDt: Calendar = Calendar.getInstance()
)
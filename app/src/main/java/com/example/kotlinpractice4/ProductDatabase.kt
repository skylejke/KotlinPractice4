package com.example.kotlinpractice4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    companion object {

        fun getDataBase(context: Context): ProductDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ProductDatabase::class.java,
                "products.db"
            ).build()
        }

    }

}

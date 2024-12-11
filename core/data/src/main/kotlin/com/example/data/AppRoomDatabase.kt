package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.model.Genre
import com.example.model.Movie
import com.example.model.MovieGenreCrossRef
import com.example.model.User
import com.example.model.UserGenreCrossRef

@Database(entities = [User::class, Movie::class, Genre::class, MovieGenreCrossRef::class, UserGenreCrossRef::class], version = 3)
abstract class AppRoomDatabase: RoomDatabase() {
    abstract fun userDao(): IUserDao
    abstract fun movieDao(): IMovieDao

    companion object {
        @Volatile
        private var Instance: AppRoomDatabase? = null
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
            CREATE TABLE user_table_new (
                userId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                username TEXT NOT NULL,
                birthDate TEXT NOT NULL,
                email TEXT NOT NULL,
                password TEXT NOT NULL
            )
        """.trimIndent())

                // Copiar los datos de la tabla anterior a la nueva
                database.execSQL("""
            INSERT INTO user_table_new (userId, username, birthDate, email, password)
            SELECT id, username, birthDate, email, password FROM user_table
        """.trimIndent())

                // Eliminar la tabla anterior
                database.execSQL("DROP TABLE user_table")

                // Renombrar la nueva tabla al nombre original
                database.execSQL("ALTER TABLE user_table_new RENAME TO user_table")

                // Crear la nueva tabla UserGenreCrossRef
                database.execSQL("""
            CREATE TABLE UserGenreCrossRef (
                genreId INTEGER NOT NULL,
                userId INTEGER NOT NULL,
                PRIMARY KEY (userId, genreId)
            )
        """.trimIndent())
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
                 ALTER TABLE movie_table ADD COLUMN isFavorite INTEGER NOT NULL DEFAULT 0
            """.trimIndent())
            }
        }

        fun getDatabase(context: Context): AppRoomDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    "item_database"
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
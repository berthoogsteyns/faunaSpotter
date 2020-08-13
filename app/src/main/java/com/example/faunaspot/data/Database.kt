package com.example.faunaspot.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class SQLiteHelper(context: Context?) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    ) {
    override fun onCreate(db: SQLiteDatabase) {
        // SQL statement to create favorite table
        val CREATE_FAVORITES_TABLE = "CREATE TABLE spots ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "href TEXT, " +
                "ingredients TEXT, " +
                "thumbnail TEXT )"
        db.execSQL(CREATE_FAVORITES_TABLE)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        // Drop older favorites table if existed
        db.execSQL("DROP TABLE IF EXISTS favorites")

        // create fresh favorites table
        onCreate(db)
    }

    fun addFavoriteMeal(meal: Meal) {
        Log.d("Adding favorite meal: ", meal.toString())

        //Get reference to writable DB
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_TITLE, meal.getTitle())
        values.put(KEY_HREF, meal.getHref())
        values.put(KEY_INGREDIENTS, meal.ingredients)
        values.put(KEY_THUMBNAIL, meal.getThumbnail())
        db.insert(TABLE_FAVORITES, null, values)
        db.close()
    }

    val allFavoriteMeals: List<Any?>
        get() {
            val meals: MutableList<Meal?> = ArrayList<Meal?>()
            val query = "SELECT * FROM $TABLE_FAVORITES"
            val db = this.writableDatabase
            val cursor: Cursor = db.rawQuery(query, null)
            var meal: Meal? = null
            if (cursor.moveToFirst()) {
                do {
                    meal = Meal()
                    meal.setId(cursor.getString(0).toInt())
                    meal.setTitle(cursor.getString(1))
                    meal.setHref(cursor.getString(2))
                    meal.setIngredients(cursor.getString(3))
                    meal.setThumbnail(cursor.getString(4))
                    meals.add(meal)
                } while (cursor.moveToNext())
            }
            Log.d("getAllFavoriteMeals()", meals.toString())
            return meals
        }

    fun deleteFavoriteMeal(meal: Meal) {
        val db = this.writableDatabase
        db.delete(
            TABLE_FAVORITES,
            "$KEY_TITLE =?",
            arrayOf(meal.getTitle())
        )
        db.close()
        Log.d("deleteFavoriteMeal", meal.toString())
    }

    fun isFavoriteMeal(meal: Meal): Boolean {
        val db = this.writableDatabase
        val selectString =
            "SELECT * FROM $TABLE_FAVORITES WHERE $KEY_TITLE =?"
        val cursor: Cursor = db.rawQuery(selectString, arrayOf(meal.getTitle()))
        if (cursor.getCount() <= 0) {
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "MealsDB"
        private const val TABLE_FAVORITES = "favorites"

        // Favorite Table Columns names
        private const val KEY_ID = "id"
        private const val KEY_TITLE = "title"
        private const val KEY_HREF = "href"
        private const val KEY_INGREDIENTS = "ingredients"
        private const val KEY_THUMBNAIL = "thumbnail"
        private val COLUMNS = arrayOf(
            KEY_ID,
            KEY_TITLE,
            KEY_HREF,
            KEY_INGREDIENTS,
            KEY_THUMBNAIL
        )
    }
}
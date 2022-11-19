package com.example.receiver

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.os.Looper
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val TAG = "TextProvider"

class TextProvider : ContentProvider() {

    private val textIndex = 0
    private val authority = "com.example.receiver.provider"

    private val uriMatcher by lazy {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(authority, "text", textIndex)
        matcher
    }

    override fun getType(uri: Uri) = when (uriMatcher.match(uri)) {
        textIndex -> "vnd.android.cursor.dir/vnd.com.example.receiver.provider.text"
        else -> null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val textClass = TextClass(text = values?.getAsString("text") ?: return null)
        CoroutineScope(Job()).launch(Dispatchers.Main) {
            Toast.makeText(context, textClass.text, Toast.LENGTH_SHORT).show()
        }
        Log.d(TAG, "match result ${uriMatcher.match(uri)}")
        when (uriMatcher.match(uri)) {
            textIndex -> {
                TextDatabase.get().textClassDao().insertAll(textClass)
            }
        }
        return null
    }


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        TODO("Implement this to handle query requests from clients.")
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}
package me.scraplesh.courses.data.database

import androidx.room.TypeConverter
import me.scraplesh.courses.data.model.ChapterData
import me.scraplesh.courses.domain.common.ChapterState
import me.scraplesh.courses.domain.model.Chapter
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ChaptersConverter {
    @TypeConverter
    fun fromChapters(chapters: List<ChapterData>): String = chapterToJson(chapters).toString()

    @TypeConverter
    fun toChapters(data: String): List<ChapterData> {
        return try {
            JSONArray(data).run {
                (0..length()).mapNotNull { index ->
                    (get(index) as? JSONObject)?.run {
                        ChapterData(
                            title = getString(NAME_TITLE),
                            state = ChapterState.valueOf(getString(NAME_STATE)),
                            chapters = toChapters(getJSONArray(NAME_CHAPTERS).toString())
                        )
                    }
                }
            }
        } catch (e: JSONException) {
            emptyList()
        }
    }

    private fun chapterToJson(chapters: List<Chapter>): JSONArray {
        return JSONArray().apply {
            chapters.forEachIndexed { index, chapter ->
                put(
                    index,
                    JSONObject().apply {
                        put(NAME_TITLE, chapter.title)
                        put(NAME_STATE, chapter.state.toString())
                        put(NAME_CHAPTERS, chapterToJson(chapter.chapters))
                    }
                )
            }
        }
    }

    private companion object {
        const val NAME_TITLE = "title"
        const val NAME_STATE = "state"
        const val NAME_CHAPTERS = "chapters"
    }
}

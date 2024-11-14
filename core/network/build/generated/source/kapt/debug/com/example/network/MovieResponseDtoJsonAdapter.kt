// Code generated by moshi-kotlin-codegen. Do not edit.
@file:Suppress("DEPRECATION", "unused", "UNUSED_PARAMETER", "ClassName", "REDUNDANT_PROJECTION",
    "RedundantExplicitType", "LocalVariableName", "RedundantVisibilityModifier",
    "PLATFORM_CLASS_MAPPED_TO_KOTLIN", "IMPLICIT_NOTHING_TYPE_ARGUMENT_IN_RETURN_POSITION")

package com.example.network

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.`internal`.Util
import java.lang.NullPointerException
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.emptySet
import kotlin.text.buildString

public class MovieResponseDtoJsonAdapter(
  moshi: Moshi,
) : JsonAdapter<MovieResponseDto>() {
  private val options: JsonReader.Options = JsonReader.Options.of("id", "title", "overview",
      "poster_path", "genre_ids", "vote_average")

  private val stringAdapter: JsonAdapter<String> = moshi.adapter(String::class.java, emptySet(),
      "id")

  private val listOfIntAdapter: JsonAdapter<List<Int>> =
      moshi.adapter(Types.newParameterizedType(List::class.java, Int::class.javaObjectType),
      emptySet(), "genre_ids")

  private val doubleAdapter: JsonAdapter<Double> = moshi.adapter(Double::class.java, emptySet(),
      "vote_average")

  public override fun toString(): String = buildString(38) {
      append("GeneratedJsonAdapter(").append("MovieResponseDto").append(')') }

  public override fun fromJson(reader: JsonReader): MovieResponseDto {
    var id: String? = null
    var title: String? = null
    var overview: String? = null
    var poster_path: String? = null
    var genre_ids: List<Int>? = null
    var vote_average: Double? = null
    reader.beginObject()
    while (reader.hasNext()) {
      when (reader.selectName(options)) {
        0 -> id = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull("id", "id", reader)
        1 -> title = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull("title", "title",
            reader)
        2 -> overview = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull("overview",
            "overview", reader)
        3 -> poster_path = stringAdapter.fromJson(reader) ?:
            throw Util.unexpectedNull("poster_path", "poster_path", reader)
        4 -> genre_ids = listOfIntAdapter.fromJson(reader) ?: throw Util.unexpectedNull("genre_ids",
            "genre_ids", reader)
        5 -> vote_average = doubleAdapter.fromJson(reader) ?:
            throw Util.unexpectedNull("vote_average", "vote_average", reader)
        -1 -> {
          // Unknown name, skip it.
          reader.skipName()
          reader.skipValue()
        }
      }
    }
    reader.endObject()
    return MovieResponseDto(
        id = id ?: throw Util.missingProperty("id", "id", reader),
        title = title ?: throw Util.missingProperty("title", "title", reader),
        overview = overview ?: throw Util.missingProperty("overview", "overview", reader),
        poster_path = poster_path ?: throw Util.missingProperty("poster_path", "poster_path",
            reader),
        genre_ids = genre_ids ?: throw Util.missingProperty("genre_ids", "genre_ids", reader),
        vote_average = vote_average ?: throw Util.missingProperty("vote_average", "vote_average",
            reader)
    )
  }

  public override fun toJson(writer: JsonWriter, value_: MovieResponseDto?): Unit {
    if (value_ == null) {
      throw NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.")
    }
    writer.beginObject()
    writer.name("id")
    stringAdapter.toJson(writer, value_.id)
    writer.name("title")
    stringAdapter.toJson(writer, value_.title)
    writer.name("overview")
    stringAdapter.toJson(writer, value_.overview)
    writer.name("poster_path")
    stringAdapter.toJson(writer, value_.poster_path)
    writer.name("genre_ids")
    listOfIntAdapter.toJson(writer, value_.genre_ids)
    writer.name("vote_average")
    doubleAdapter.toJson(writer, value_.vote_average)
    writer.endObject()
  }
}

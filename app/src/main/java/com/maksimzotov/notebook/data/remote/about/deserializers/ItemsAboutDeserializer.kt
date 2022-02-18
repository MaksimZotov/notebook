package com.maksimzotov.notebook.data.remote.about.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.maksimzotov.notebook.domain.entities.itemabout.ItemAbout
import java.lang.reflect.Type
import javax.inject.Inject

class ItemsAboutDeserializer @Inject constructor() : JsonDeserializer<ItemsAboutWrapper> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ItemsAboutWrapper {

        json
            ?: throw IllegalArgumentException("json must not be null")

        val articles = json
            .asJsonObject
            .get("articles")
            .asJsonArray

        val itemAboutList = mutableListOf<ItemAbout>()

        articles.forEach { article ->
            val jsonObject = article.asJsonObject
            val title = jsonObject.get("title").toString().trim('\"')
                .replace("\\\"", "\"")
            val urlToImage = jsonObject.get("urlToImage").toString().trim('\"')
            val urlToWebPage = jsonObject.get("url").toString().trim('\"')
            itemAboutList.add(ItemAbout(title, urlToImage, urlToWebPage))
        }

        return ItemsAboutWrapper(itemAboutList)
    }
}
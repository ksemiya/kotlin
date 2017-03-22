package kss.tasks.pdf2.practice.task2_1

import java.util.*


data class Book(
        val title: String,
        val authors: List<Author>,
        val publisher: Publisher,
        val publishedAt: Date,
        val city: City,
        val pagesCount: Int,
        val paperType: PaperType,
        val coverType: CoverType,
        val isbn: ISBN,
        val format: Format,
        val language: String,
        val themeTags: List<String>
)
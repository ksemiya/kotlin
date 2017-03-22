package kss.tasks.pdf2.practice.task2_1

import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.net.URL
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory

class BookLoader (val apiKey: String) {

    /* Сделана только половина задачи
    И загуглила удобненький сайт, который возвращает (почти) все, что нам надо.
    */

    fun loadBook(isbn: String): Book {

        val yahoo = URL("http://isbndb.com/api/v2/xml/$apiKey/book/$isbn")
        val yc = yahoo.openConnection()
        val inputStream = yc.getInputStream()
        val dbFactory = DocumentBuilderFactory.newInstance()
        val dBuilder = dbFactory.newDocumentBuilder()
        val doc = dBuilder.parse(inputStream)

        fun NodeList.asList(): List<Node> {
            val list = this
            return object : Iterable<Node> {
                override fun iterator(): Iterator<Node> {
                    return object : Iterator<Node> {
                        var index = 0
                        override fun next(): Node {
                            index++
                            return list.item(index - 1)
                        }

                        override fun hasNext(): Boolean {
                            return index < list.length
                        }
                    }
                }
            }.toList()
        }

        val titleNodes = doc.getElementsByTagName("title")
        val titleNode0 = titleNodes.item(0)
        val title = titleNode0.textContent
        val authors = doc.getElementsByTagName("author_data")
                .asList()
                .map { it as Element }
                .map { it.getElementsByTagName("name") }
                .map { it.item(0) }
                .map { it.textContent }
                .map(::Author)
        val publisher = Publisher(doc.getElementsByTagName("publisher_name").item(0).textContent)
        val publishedAt = Date()
        val city = City("")
        val pagesCount = 1
        val paperType = PaperType.hard
        val coverType = CoverType.hard
        val format = Format.A5
        val language = doc.getElementsByTagName("language").item(0).textContent
        val themeTags = doc.getElementsByTagName("subject_ids")
                .asList()
                .map { it.textContent }

        return Book(title,
                authors,
                publisher,
                publishedAt,
                city,
                pagesCount,
                paperType,
                coverType,
                ISBN(isbn),
                format,
                language,
                themeTags
        )


    }

}
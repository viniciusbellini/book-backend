package bellini.vinicius.bookbackend.repositories

import bellini.vinicius.bookbackend.models.Book
import org.springframework.data.mongodb.repository.MongoRepository

interface BookRepository: MongoRepository<Book, String> {
    fun findByAuthorId(id: String): List<Book>
    fun findByNameRegex(name: String): List<Book>
}
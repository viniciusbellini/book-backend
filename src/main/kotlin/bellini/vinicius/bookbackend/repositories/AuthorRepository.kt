package bellini.vinicius.bookbackend.repositories

import bellini.vinicius.bookbackend.models.Author
import org.springframework.data.mongodb.repository.MongoRepository

interface AuthorRepository: MongoRepository<Author, String>
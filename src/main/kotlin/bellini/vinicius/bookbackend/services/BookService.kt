package bellini.vinicius.bookbackend.services

import bellini.vinicius.bookbackend.models.Book
import bellini.vinicius.bookbackend.repositories.AuthorRepository
import bellini.vinicius.bookbackend.repositories.BookRepository
import bellini.vinicius.bookbackend.util.BasicCrud
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class BookService(
        val bookRepository: BookRepository,
        private val authorRepository: AuthorRepository): BasicCrud<String, Book> {

    override fun getAll(pageable: Pageable): Page<Book> = bookRepository.findAll(pageable)

    override fun getById(id: String): Optional<Book> = bookRepository.findById(id)

    override fun insert(obj: Book): Book = bookRepository.insert(obj.apply { this.author = authorRepository.findById(obj.author.id!!).get() })

    @Throws(Exception::class)
    override fun update(obj: Book): Book{
        return if(bookRepository.existsById(obj.isbn)){
            bookRepository.save(obj.apply { this.author = authorRepository.findById(obj.author.id!!).get() })
        }else{
            throw object: Exception("Book not found"){}
        }
    }

    override fun deleteById(id: String): Optional<Book> {
        return bookRepository.findById(id).apply {
            this.ifPresent { bookRepository.delete(it) }
        }
    }
}
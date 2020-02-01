package bellini.vinicius.bookbackend.controllers

import bellini.vinicius.bookbackend.models.Book
import bellini.vinicius.bookbackend.services.BookService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
@RequestMapping("api/book")
class BookController (private val bookService: BookService){

    @GetMapping
    fun getAll(pageable: Pageable): Page<Book> = bookService.getAll(pageable)

    @GetMapping("{isbn}")
    fun getByIsbn(@PathVariable isbn:String): Optional<Book> = bookService.getById(isbn)

    @GetMapping("/byName/{regex}")
    fun getByName(@PathVariable regex: String): List<Book> = bookService.bookRepository.findByNameRegex(regex)

    @PostMapping
    fun insert(@RequestBody book: Book) = bookService.insert(book)

    @PutMapping
    fun update(@RequestBody book: Book) = bookService.update(book)

    @DeleteMapping("{isbn}")
    fun deleteById(@PathVariable isbn: String): Optional<Book> = bookService.deleteById(isbn)

}
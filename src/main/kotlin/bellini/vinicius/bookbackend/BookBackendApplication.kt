package bellini.vinicius.bookbackend

import bellini.vinicius.bookbackend.models.Author
import bellini.vinicius.bookbackend.models.Book
import bellini.vinicius.bookbackend.repositories.AuthorRepository
import bellini.vinicius.bookbackend.repositories.BookRepository
import bellini.vinicius.bookbackend.util.toLocalDate
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookBackendApplication(
		private val bookRepository: BookRepository,
		private val authorRepository: AuthorRepository): ApplicationRunner{

	override fun run(args: ApplicationArguments?) {
		if(bookRepository.count()<1) this.createBooks()
	}

	private fun createBooks(){
		this.cleanCollections()
		val george = authorRepository.insert(Author(name = "George R. R. Martin",birthDate =  "20-09-1948".toLocalDate()))
		val tolkien = authorRepository.insert(Author(name = "J. R. R. Tolkien", birthDate = "03-01-1892".toLocalDate()))

		val books = listOf(
				Book(isbn = "9780553573428",name = "A Storm of Swords", publishedYear = 2011, author = george),
				Book(isbn = "9780553579901", name = "A clash of kings", publishedYear = 2005, author = george),
				Book(isbn = "9780618260553", name = "The Return of the King", publishedYear = 2002, author = tolkien)
		)
		bookRepository.insert(books)
	}

	private fun cleanCollections() {
		authorRepository.deleteAll()
		bookRepository.deleteAll()
	}

}

fun main(args: Array<String>) {
	runApplication<BookBackendApplication>(*args)
}

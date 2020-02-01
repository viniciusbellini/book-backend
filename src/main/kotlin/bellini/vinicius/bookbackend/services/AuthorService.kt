package bellini.vinicius.bookbackend.services

import bellini.vinicius.bookbackend.models.Author
import bellini.vinicius.bookbackend.repositories.AuthorRepository
import bellini.vinicius.bookbackend.repositories.BookRepository
import bellini.vinicius.bookbackend.util.BasicCrud
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.Optional

@Service
class AuthorService (
        private val authorRepository: AuthorRepository,
        private val bookRepository: BookRepository): BasicCrud<String, Author> {

    override fun getAll(pageable: Pageable): Page<Author> = authorRepository.findAll(pageable)

    override fun getById(id: String): Optional<Author> = authorRepository.findById(id)

    override fun insert(obj: Author): Author = authorRepository.insert(obj)

    @Throws(Exception::class)
    override fun update(obj: Author): Author {
        return if (authorRepository.existsById(obj.id.toString())){
            authorRepository.save(obj).apply {
                obj.id?.let {
                    bookRepository.saveAll(bookRepository.findByAuthorId(it).map { it.also { it.author = this } })
                }
            }
        }else{
            throw object : Exception("The author does not exists"){}
        }
    }

    override fun deleteById(id: String): Optional<Author> {
        return authorRepository.findById(id).apply {
            this.ifPresent {
                authorRepository.delete(it)
            }
        }
    }
}
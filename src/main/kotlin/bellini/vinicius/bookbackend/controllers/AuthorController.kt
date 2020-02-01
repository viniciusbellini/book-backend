package bellini.vinicius.bookbackend.controllers

import bellini.vinicius.bookbackend.models.Author
import bellini.vinicius.bookbackend.services.AuthorService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import java.util.Optional

@RestController
@RequestMapping("api/author")
class AuthorController (private val authorService: AuthorService){
    @GetMapping
    fun getAll(pageable: Pageable): Page<Author> = authorService.getAll(pageable)

    @GetMapping("{id}")
    fun getById(@PathVariable id: String): Optional<Author> = authorService.getById(id)

    @PostMapping
    fun insert(@RequestBody author: Author): Author = authorService.insert(author)

    @PutMapping
    fun update(@RequestBody author: Author): Author = authorService.update(author)

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable id: String): Optional<Author>  = authorService.deleteById(id)
}
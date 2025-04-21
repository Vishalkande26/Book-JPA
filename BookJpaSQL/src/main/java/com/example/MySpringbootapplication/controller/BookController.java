package com.example.MySpringbootapplication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MySpringbootapplication.modal.Book;
import com.example.MySpringbootapplication.repository.BookRepository;

@RestController
public class BookController 
{
	@Autowired
	private BookRepository bookrepository;
	
	@PostMapping("/bookSave")
	public String insertBook(@RequestBody Book book)
	{
		try {
            Book savedBook = bookrepository.save(book);
            return "Book saved successfully with ID: "+savedBook;
        } catch (Exception e) {
            return "Failed to save book: " + e.getMessage();
        }
    }
	
	@PutMapping("/bookUpdate")
	public String updateBook(@RequestBody Book book)
	{
		try {
			Book updateBook = bookrepository.save(book);
			return "Book update successfully with ID: "+updateBook;
		} catch (Exception e) {
			return "Failed to update book: " + e.getMessage();
		}
	}
			
	
	@PostMapping("/multipleBookSave")
	public String insertBook(@RequestBody List<Book> book)
	{
		bookrepository.saveAll(book);
		return "Record Save All Successfully!!!";		
	}
	
	@GetMapping("/getAllBook")
	public List<Book> getBook()
	{
		return (List<Book>) bookrepository.findAll();		
	}
	@GetMapping("/getByBookName/{name}")
	public List<Book> getBook(@PathVariable("name") String bookName)
	{
		return (List<Book>) bookrepository.findBybookName(bookName);
	}
	@GetMapping("/getByBookId/{bookid}")
	public Optional<Book> getBook(@PathVariable("bookid") Long id)
	{
		return bookrepository.findById(id);
	}

	@DeleteMapping("/deleteBook/{bookid}")
	public String deleteBook(@PathVariable("bookid") Long id) {
	    if (bookrepository.existsById(id)) {
	        bookrepository.deleteById(id);
	        return "Book deleted successfully with ID: " + id;
	    } else {
	        return "Book not found with ID: " + id;
	    }
	}

}

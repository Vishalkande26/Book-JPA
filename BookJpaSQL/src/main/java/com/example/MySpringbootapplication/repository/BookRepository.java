package com.example.MySpringbootapplication.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MySpringbootapplication.modal.Book;
public interface BookRepository extends JpaRepository<Book, Long>

{
	public List<Book> findBybookName(String bookName);
}

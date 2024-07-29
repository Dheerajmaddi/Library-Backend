package com.dmaddi.spring_boot_library.dao;

import com.dmaddi.spring_boot_library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

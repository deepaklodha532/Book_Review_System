package com.coding.visit.repositories;

import com.coding.visit.entities.Book;
import com.coding.visit.entities.Review;
import com.coding.visit.entities.User;
import jakarta.persistence.SecondaryTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ReviewRepository extends JpaRepository<Review,String> {
    Optional<Review> findByBookAndUser(Book book, User user) ;
    Set<Review> findByBook(Book book ) ;
}

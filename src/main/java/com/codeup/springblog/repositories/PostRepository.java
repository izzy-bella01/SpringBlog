package com.codeup.springblog.repositories;

import com.codeup.springblog.services.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findById(long id);
}

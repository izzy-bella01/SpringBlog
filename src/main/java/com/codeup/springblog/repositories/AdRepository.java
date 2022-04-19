package com.codeup.springblog.repositories;

import com.codeup.springblog.services.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {
    Ad findByTitle(String title);

}

package com.cbstd.rssr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbstd.rssr.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

}

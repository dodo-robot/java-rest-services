package com.dodo.webservice.rest.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dodo.webservice.rest.example.model.Post; 

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

}

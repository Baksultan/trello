package com.example.spring_trello.repository;

import com.example.spring_trello.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

}

package com.second.version.character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FigureRepository extends JpaRepository<Figure,Long> {
    public Figure findByName(String name);
}

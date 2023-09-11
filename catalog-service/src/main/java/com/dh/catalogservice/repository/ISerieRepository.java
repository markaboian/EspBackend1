package com.dh.catalogservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.dh.catalogservice.model.Serie;
import java.util.List;

@Repository
public interface ISerieRepository extends MongoRepository<Serie, Long>{
    List<Serie> findAllByGenre(String genre);
}

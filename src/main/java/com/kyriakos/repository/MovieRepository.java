package com.kyriakos.repository;

import com.kyriakos.entity.MovieEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, Integer> {


    /* Custom query that finds movies in the database by their name. */
    @Query(
            value = "SELECT * FROM MOVIE WHERE name = :name",
            nativeQuery = true)
    MovieEntity findByName(@Param("name") String movieName);

}

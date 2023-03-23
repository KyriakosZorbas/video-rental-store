package com.kyriakos.repository;

import com.kyriakos.Application;
import com.kyriakos.entity.MovieEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = Application.class)
public class MovieEntityRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void testFindById() {
        MovieEntity movieEntity = getMovieEntity();
        movieRepository.save(movieEntity);
        MovieEntity result = movieRepository.findById(movieEntity.getId()).get();
        assertEquals(movieEntity.getId(), result.getId());
    }

    @Test
    public void testFindByName() {
        MovieEntity movieEntity = getMovieEntity();
        movieRepository.save(movieEntity);
        assertEquals(movieEntity.getName(), movieRepository.findByName(movieEntity.getName()).getName());
    }

    @Test
    public void testFindAll() {
        MovieEntity movieEntity = getMovieEntity();
        movieRepository.save(movieEntity);
        List<MovieEntity> result = new ArrayList<>();
        movieRepository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 5);
    }

    @Test
    public void testSave() {
        MovieEntity movieEntity = getMovieEntity();
        movieRepository.save(movieEntity);
        MovieEntity found = movieRepository.findById(movieEntity.getId()).get();
        assertEquals(movieEntity.getId(), found.getId());
    }

    @Test
    public void testDeleteById() {
        MovieEntity movieEntity = getMovieEntity();
        movieRepository.save(movieEntity);
        movieRepository.deleteById(movieEntity.getId());
        List<MovieEntity> result = new ArrayList<>();
        movieRepository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 4);
    }

    private MovieEntity getMovieEntity() {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(1);
        movieEntity.setName("The Batman");
        movieEntity.setType("new");
        movieEntity.setPrice(30);
        return movieEntity;
    }
}
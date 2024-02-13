package com.marcinl.service.category;

import com.marcinl.model.Genres;
import com.marcinl.repository.BookshopRepository;

import java.util.List;

public class CategoryManagerImpl implements CategoryManager {
    BookshopRepository repository;

    public CategoryManagerImpl(BookshopRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addBookCategory(Genres genres) {
        List<Genres> genresList = repository.getGenresList();
        genresList.add(genres);
        repository.setGenresList(genresList);
    }
}

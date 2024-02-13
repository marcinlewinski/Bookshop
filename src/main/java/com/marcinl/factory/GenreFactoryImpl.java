package com.marcinl.factory;

import com.marcinl.model.Genres;

import java.util.UUID;

public class GenreFactoryImpl implements GenreFactory {
    @Override
    public Genres createGenre(String genreName) {
        return new Genres(UUID.randomUUID(), genreName);
    }
}

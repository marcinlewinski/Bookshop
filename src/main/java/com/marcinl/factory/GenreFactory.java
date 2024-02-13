package com.marcinl.factory;

import com.marcinl.model.Genres;

public interface GenreFactory {
    Genres createGenre(String genreName);
}

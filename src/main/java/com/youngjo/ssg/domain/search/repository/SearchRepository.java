package com.youngjo.ssg.domain.search.repository;

import com.youngjo.ssg.domain.search.domain.Search;

public interface SearchRepository {
    void saveSearch(Search search);

    Search findSearch(String query);
}

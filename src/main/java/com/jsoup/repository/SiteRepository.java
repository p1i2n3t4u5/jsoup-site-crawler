package com.jsoup.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.jsoup.model.Site;

public interface SiteRepository extends ElasticsearchRepository<Site, String> {

}

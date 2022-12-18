package com.example.myshoturl.Repository;

import com.example.myshoturl.Url;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface URLRepo extends CrudRepository<Url,Long> {
    Url findByShortUrl (String shortUrl);

    Url findByLongUrl(String longUrl);

    Object findByShortUrlLike(String shortUrl);
}

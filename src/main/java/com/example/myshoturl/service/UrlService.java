package com.example.myshoturl.service;
import com.example.myshoturl.Repository.URLRepo;
import com.example.myshoturl.Url;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.net.MalformedURLException;


@Service
public class UrlService {
    @Autowired
    private URLRepo urlRepo;


    public  void setShortUrl(String randomChar, String longUrl) throws MalformedURLException {
        String shortUrl = null;
        shortUrl = ("http://localhost:8081/s/"+randomChar);
        Url url = new Url(longUrl,shortUrl);
        urlRepo.save(url);
    }


    public static String getRandomChars() {
            StringBuilder randomStr = new StringBuilder();
            String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            for (int i = 0; i < 5; i++)
                randomStr.append(possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length())));
            return randomStr.toString();
    }
    public void getLongUrl(HttpServletResponse response, String shortUrl) throws IOException {
               response.sendRedirect(urlRepo.findByShortUrl(shortUrl).getLongUrl());
    }

}

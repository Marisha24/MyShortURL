    package com.example.myshoturl.service;
    import com.example.myshoturl.Repository.URLRepo;
    import com.example.myshoturl.entity.Url;
    import jakarta.servlet.http.HttpServletResponse;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.io.IOException;
    import java.net.MalformedURLException;


    @Service
    public class UrlService {
        @Autowired
        private URLRepo urlRepo;

    //метод генерации короткой ссылки
        public  void setShortUrl(String randomChar, String longUrl) throws MalformedURLException {
            String shortUrl = null;
            shortUrl = ("http://localhost:8081/s/"+randomChar);
            Url url = new Url(longUrl,shortUrl);
            //проверка наличия сгенерируемой ссылки в БД
            //если такой ссылки нет - сохраняем в БД
            //если есть опять генерируем ее
            if (shortUrl!=urlRepo.findByShortUrlLike(shortUrl)) urlRepo.save(url);
            else setShortUrl(randomChar,longUrl);
        }
        // метод для проверки наличия длинного URL уже в БД
        //если такую ссылку еще не сокращали вызывается метод setShortUrl
        public void checkOnDatabase(String longUrl, String randomChar) throws MalformedURLException {
            if (urlRepo.findByLongUrl(longUrl) == null) {
               setShortUrl(randomChar, longUrl);
            }
        }
    //метод генерации рандомной части ссылки
        public static String getRandomChars() {
                StringBuilder randomStr = new StringBuilder();
                String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                for (int i = 0; i < 5; i++)
                    randomStr.append(possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length())));
                return randomStr.toString();
        }
        //метод перенаправления короткой ссылки на длинную
        public void redirect(HttpServletResponse response, String shortUrl) throws IOException {
                   response.sendRedirect(urlRepo.findByShortUrl(shortUrl).getLongUrl());
        }

    }

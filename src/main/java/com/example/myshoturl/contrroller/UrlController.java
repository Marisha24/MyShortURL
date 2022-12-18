package com.example.myshoturl.contrroller;

import com.example.myshoturl.Repository.URLRepo;
import com.example.myshoturl.Url;

import com.example.myshoturl.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.MalformedURLException;


@Controller
public class UrlController {
  @Autowired
  private URLRepo urlRepo;
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }


    @RequestMapping( path = "/", method = RequestMethod.GET)
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainPage");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addUrl(@RequestParam("longUrl") String longUrl) throws MalformedURLException {
        String randomChar = urlService.getRandomChars();
        ModelAndView modelAndView = new ModelAndView();
        Iterable<Url> urls ;
        if (longUrl != null){
            urlService.checkOnDatabase(longUrl, randomChar);
            modelAndView.addObject("urls", urlRepo.findByLongUrl(longUrl));
        }
        modelAndView.setViewName("mainPage");
        return modelAndView;
    }
//контроллер перенаправления с генерируемой ссылки на длинную
    @RequestMapping(value="/s/{randomstring}", method=RequestMethod.GET)
    public void getLongUrl(HttpServletResponse response, @PathVariable("randomstring") String shortUrl) throws IOException {
        shortUrl = ("http://localhost:8081/s/"+shortUrl);
        urlService.redirect(response,shortUrl);
    }

}

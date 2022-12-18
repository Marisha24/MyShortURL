package com.example.myshoturl;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    //  Атрибут LongUrl — это URL-адрес, на который мы должны перенаправить, как только пользователь перейдет по короткой ссылке
    @Column(nullable = false)
    private String longUrl;

    @Column
    private String shortUrl;

    public Url(String longUrl, String shortUrl) {

        this.longUrl = longUrl;
        this.shortUrl = shortUrl;

    }

}

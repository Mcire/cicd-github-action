package com.che.cicdgithubaction.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CheController {
    @GetMapping("/gitAction")
    public String getPhrase() {
        return "La vie est plus belle lorsqu'on est ivre , On veut tous une belle mort!";
    }
}

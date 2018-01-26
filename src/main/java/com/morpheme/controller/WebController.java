package com.morpheme.controller;

import com.morpheme.POJO.Input;
import com.morpheme.model.Kkma;
import com.morpheme.model.Komoran_morp;
import com.morpheme.model.Twitter;
import com.morpheme.POJO.model;
import com.twitter.penguin.korean.KoreanTokenJava;
import kr.co.shineware.nlp.komoran.model.Token;
import org.snu.ids.kkma.ma.Eojeol;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/morp")
public class WebController {

    Input sample = new Input();

    @PostMapping(value = "/input")
    public Input postInput(@RequestBody Input input) {
        sample = input;
        return input;
    }

    @GetMapping(value = "/input/kkma")
    public model getKkma() {

        String input_str = sample.getInput_str();

        if(input_str != "") {
            return Kkma.kkma_output(input_str);
        } else {
            return null;
        }
    }

    @GetMapping(value = "/input/twitter")
    public model getTwit() {

        String input_str = sample.getInput_str();

        if(input_str != "") {
            return Twitter.twitter_output(input_str);
        } else {
            return null;
        }
    }

    @GetMapping(value = "/input/komoran")
    public model getKomoran() {

        String input_str = sample.getInput_str();
        System.out.println(input_str);
        if(input_str != "") {
            return Komoran_morp.komoran_output(input_str);
        } else {
            return null;
        }
    }
}

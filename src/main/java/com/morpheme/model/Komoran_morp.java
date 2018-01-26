package com.morpheme.model;

import com.morpheme.POJO.model;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;

import java.util.List;

public class Komoran_morp  {

    public static model komoran_output(String input) {

        double startTime = System.currentTimeMillis();

        Komoran komoran = new Komoran("models_light");

        KomoranResult analyzeResultList = komoran.analyze(input);
        List<Token> tokenList = analyzeResultList.getTokenList();

        System.out.println("==========print 'getNouns()'==========");
        System.out.println(analyzeResultList.getNouns());

        List<Token> output_morp = tokenList;
        List<String> output_noun = analyzeResultList.getNouns();
        double time_interval = System.currentTimeMillis() - startTime;

        return new model(output_morp, output_noun, time_interval);
    }
}


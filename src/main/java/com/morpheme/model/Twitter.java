package com.morpheme.model;

import com.twitter.penguin.korean.KoreanTokenJava;
import scala.collection.Seq;
import com.morpheme.POJO.model;

import com.twitter.penguin.korean.TwitterKoreanProcessor;
import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.phrase_extractor.KoreanPhraseExtractor;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;

import java.util.ArrayList;
import java.util.List;

public class Twitter {

    public static model twitter_output(String input) {

        double startTime = System.currentTimeMillis();

        CharSequence normalized = TwitterKoreanProcessorJava.normalize(input);
        Seq<KoreanTokenizer.KoreanToken> tokens = TwitterKoreanProcessorJava.tokenize(normalized);

        /* 형태소 분석 구간 */
        System.out.println("====== Tokenized =====");
        List<KoreanTokenJava> output_morp = TwitterKoreanProcessorJava.tokensToJavaKoreanTokenList(tokens);

        /* 명사 추출 구간 */
        System.out.println("====== Phrased =====");
        List<KoreanPhraseExtractor.KoreanPhrase> phrase = TwitterKoreanProcessorJava.extractPhrases(tokens, true, true);
        List<Object> output_noun = new ArrayList<>();
        for (KoreanPhraseExtractor.KoreanPhrase elem: phrase) {
            output_noun.add(elem.text());
            System.out.println(elem.text() + "\n");
        }
        double time_interval = System.currentTimeMillis() - startTime;

        return new model(output_morp, output_noun, time_interval);

    }
}
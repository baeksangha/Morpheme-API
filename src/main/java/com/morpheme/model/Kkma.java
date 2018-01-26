package com.morpheme.model;

import com.morpheme.POJO.model;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.snu.ids.kkma.ma.Eojeol;
import org.snu.ids.kkma.ma.MExpression;
import org.snu.ids.kkma.ma.MorphemeAnalyzer;
import org.snu.ids.kkma.ma.Sentence;
import org.snu.ids.kkma.util.Timer;

import java.util.ArrayList;
import java.util.List;

public class Kkma {

    public static model kkma_output(String input) {

        double time = System.currentTimeMillis();


        try {
            /* 형태소 분석 구간 */
            MorphemeAnalyzer ma = new MorphemeAnalyzer();
            ma.createLogger(null);
            List<MExpression> ret = ma.analyze(input);

            ret = ma.postProcess(ret);
            ret = ma.leaveJustBest(ret);

            List<Sentence> stl = ma.divideToSentences(ret);
            List<Object> return_value = new ArrayList<>();

            for( int i = 0; i < stl.size(); i++ ) {
                Sentence st = stl.get(i);
                for( int j = 0; j < st.size(); j++ ) {
                    return_value.add(st.get(j));
                }
            }

            /* 명사 추출 구간 */
            KeywordExtractor ke = new KeywordExtractor();
            KeywordList kl = ke.extractKeyword(input, true);
            List<Object> keywords = new ArrayList<>();
            Keyword kwrd;

            for( int i = 0; i < kl.size(); i++ ) {
                kwrd = kl.get(i);
                keywords.add(kwrd);
            }

            return new model(return_value, keywords, System.currentTimeMillis()-time);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}



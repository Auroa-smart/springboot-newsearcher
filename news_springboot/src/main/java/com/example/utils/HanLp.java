package com.example.utils;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HanLp {
     public String process(String newsContent){
         if(newsContent == null){
             return null;
         }else {
             // 加载停用词列表
             Set<String> stopWords = loadStopWords("src/main/java/com/example/utils/stopwords.txt");
             // 分词
             List<String> tokens = tokenize(newsContent);

             // 停用词过滤
             List<String> filteredTokens = filterStopWords(tokens, stopWords);

             // 提取特征值（词袋模型）
             Map<String, Integer> featureVector = extractFeatureVector(filteredTokens);

             // 输出处理后的文本和特征值
//        System.out.println("原始文本: " + newsContent);
//        System.out.println("处理后的文本: " + String.join(" ", filteredTokens));
//        System.out.println("特征值: " + featureVector);
             return String.join(" ", filteredTokens);
         }
    }

    /* 加载停用词列表 */
    private static Set<String> loadStopWords(String filePath){
             Set<String> stopWords = new HashSet<>();
             try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                 String line;
                 while ((line = reader.readLine()) != null) {
                     stopWords.add(line.trim());
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
             return stopWords;
    }

    /* 使用HanLP进行分词 */
    private static List<String> tokenize(String text) {
        List<String> tokens = new ArrayList<>();
        List<Term> termList = HanLP.segment(text);
        for (Term term : termList) {
            tokens.add(term.word);
        }
        return tokens;
    }

    /* 停用词过滤 */
    private static List<String> filterStopWords(List<String> tokens, Set<String> stopWords) {
        List<String> filteredTokens = new ArrayList<>();
        for (String token : tokens) {
            if (!stopWords.contains(token)) {
                filteredTokens.add(token);
            }
        }
        return filteredTokens;
    }

    /* 提取特征值（词袋模型）*/
    private static Map<String, Integer> extractFeatureVector(List<String> tokens) {
        Map<String, Integer> featureVector = new HashMap<>();
        for (String token : tokens) {
            featureVector.put(token, featureVector.getOrDefault(token, 0) + 1);
        }
        return featureVector;
    }
}

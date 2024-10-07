package com.example.utils;

import com.example.pojo.Category;
import com.example.pojo.News;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.Clusterer;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.commons.math3.random.JDKRandomGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class NewsClustering {
    private final HanLp hanLp = new HanLp();
    public HashSet<Category> sort(HashSet<News> newsSet){
        HashSet<Category> categoryHashSet = new HashSet<>();
        for (News news : newsSet) {
            String processedContent = hanLp.process(news.getContent());
            news.setContent(processedContent);
        }
        List<News> newsList = new ArrayList<>(newsSet);

        // 构建词汇表（这里简化为示例）
        List<String> vocabulary = buildVocabulary(newsList);

        // 将newsList转化为特征向量
        List<MyClusterable> featureVectors = convertToFeatureVectors(newsList, vocabulary);

        // 执行K均值聚类
        int k = 10; // 设置簇的数量
        List<Cluster<MyClusterable>> clusters = performKMeansClustering(featureVectors, k);

        // 分析并输出结果
        for (int i = 0; i < clusters.size(); i++) {
            System.out.println("Cluster " + (i + 1));
            int cluster = i + 1;
            List<MyClusterable> clusterPoints = clusters.get(i).getPoints();
            for (int j = 0; j < clusterPoints.size(); j++) {
                News news = newsList.get(featureVectors.indexOf(clusterPoints.get(j)));
                System.out.println("id"+ news.getTitle());
                categoryHashSet.add(new Category(0,news.getNewsId(),cluster));
            }
            System.out.println();
        }
        return categoryHashSet;

    }
    /* 构建词汇表 包含在所有新闻中出现过的不同的词语 */
    private static List<String> buildVocabulary(List<News> newsList) {
        List<String> vocabulary = new ArrayList<>();
        for (News news : newsList) {
            String content = news.getContent();
            if(content == null){
                System.out.println("有空的内容");
                continue;
            }
            // 进行分词和处理文本
            String[] words = content.split("\\s+"); // 简化分词
            for (String word : words) {
                if (!vocabulary.contains(word)) {
                    vocabulary.add(word);
                }
            }
        }
        return vocabulary;
    }

    /* 构建特征向量 将新闻内容表示为一个特征向量，其中每个维度对应词汇表中的一个词语，而每个值表示该词语在新闻中出现的次数。 */
    private static List<MyClusterable> convertToFeatureVectors(List<News> newsList, List<String> vocabulary) {
        List<MyClusterable> featureVectors = new ArrayList<>();
        for (News news : newsList) {
            String content = news.getContent();
            if(content == null){
                continue;
            }
            String[] words = content.split("\\s+");
            RealVector vector = new ArrayRealVector(vocabulary.size());
            for (int i = 0; i < vocabulary.size(); i++) {
                int count = 0;
                for (String word : words) {
                    if (vocabulary.get(i).equals(word)) {
                        count++;
                    }
                }
                vector.setEntry(i, count);
            }
            featureVectors.add(new MyClusterable(vector));
        }
        return featureVectors;
    }

    /* 方法执行K均值聚类。采用特征向量和簇的数量K作为参数，并返回一个包含K个簇的列表。 */
    private static List<Cluster<MyClusterable>> performKMeansClustering(List<MyClusterable> featureVectors, int k) {
        Clusterer<MyClusterable> clusterer = new KMeansPlusPlusClusterer<>(k, -1, new EuclideanDistance(), new JDKRandomGenerator());
        List<?> clusters = clusterer.cluster(featureVectors); // 使用通配符捕获泛型类型
        return (List<Cluster<MyClusterable>>) clusters;
    }
}





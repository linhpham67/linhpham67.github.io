import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import java.util.*;
import java.util.List;


public class NewsCategoryClassification {
    private static final Map<String, List<String>> categories = new HashMap<>();

    static {
        categories.put("Technology", Arrays.asList("advanced", "computer science", "machine learning", "AI", "innovation"));
        categories.put("Business", Arrays.asList("finance", "stock market", "economy", "entrepreneur"));
        categories.put("Sports", Arrays.asList("soccer", "NBA", "World Cup", "baseball", "athlete", "swim"));
        categories.put("Entertainment", Arrays.asList("movie", "tv", "Kardashians", "tiktok"));
    }

    public static String classifyCategory(String article) {
        Annotation annotation = new Annotation(article);
        StanfordCoreNLP pipeline = new StanfordCoreNLP();
        pipeline.annotate(annotation);

        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        StringBuilder articleText = new StringBuilder();

        for (CoreMap sentence : sentences) {
            articleText.append(sentence.toString().toLowerCase()).append(" ");
        }

        for (Map.Entry<String, List<String>> entry : categories.entrySet()) {
            String category = entry.getKey();
            List<String> keywords = entry.getValue();

            for (String keyword : keywords) {
                if (articleText.toString().toLowerCase().contains(keyword.toLowerCase())) {
                    return category;
                }
            }
        }
        return "Uncategorized";
    }

    public static void main(String[] args) {

        String newsArticle1 = "This is a sample news article about machine learning and innovation.";
        String newsArticle2 = "The Kardashians and Hailey Bieber have feud";

        String category1 = classifyCategory(newsArticle1);
        String category2 = classifyCategory(newsArticle2);
        System.out.println("News Category 1: " + category1);
        System.out.println("News Category 2: " + category2);
    }


}

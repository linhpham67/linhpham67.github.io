import java.util.HashMap;
import java.util.Map;


public class TFIDF {
    public static HashMap<String, Double> calculateTF(HashMap<String, Integer> termFrequencies) {
        HashMap<String, Double> tfMap = new HashMap<>();
        double totalTerms = termFrequencies.values().stream().mapToDouble(Integer::doubleValue).sum();
        // formula to calculate tf
        termFrequencies.forEach((term, tf) -> {
            double tfValue = tf / totalTerms;
            tfMap.put(term, tfValue);
        });
        return tfMap;
    }

    public static HashMap<String, Double> calculateIDF(HashMap<String, HashMap<String, Integer>> documentTermFrequencies) {
        HashMap<String, Double> idfMap = new HashMap<>();//store calculated idf values
        int totalDocuments = documentTermFrequencies.size(); //total no of docs


        //calculate doc frequency
        HashMap<String, Integer> documentFrequency = new HashMap<>();
        for (HashMap<String, Integer> doc : documentTermFrequencies.values()) {
            for (String term : doc.keySet()) {
                documentFrequency.merge(term, 1, Integer::sum);
            }
        }
        //calculate idf
        for (var entry : documentFrequency.entrySet()) {
            String term = entry.getKey();
            int df = entry.getValue();


            double idfValue = Math.log((double) totalDocuments / (1 + df));
            idfMap.put(term, idfValue);
        }
        return idfMap;
    }


    public static HashMap<String, Double> calculateTFIDF(HashMap<String, Integer> termFrequencies,
                                                         HashMap<String, Double> idfMap) {
        HashMap<String, Double> tfidfMap = new HashMap<>();


        // Calculate TF
        HashMap<String, Double> tfMap = calculateTF(termFrequencies);


        // Calculate TF-IDF
        tfMap.forEach((term, tfValue) -> {
            double idfValue = idfMap.getOrDefault(term, 0.0);
            double tfidfValue = tfValue * idfValue;
            tfidfMap.put(term, tfidfValue);
        });


        return tfidfMap;
    }
    public static void main(String[] args) {
        // Example usage:
        HashMap<String, Integer> termFrequencies = new HashMap<>();
        termFrequencies.put("apple", 3);
        termFrequencies.put("orange", 2);
        termFrequencies.put("banana", 5);


        HashMap<String, HashMap<String, Integer>> documentTermFrequencies = new HashMap<>();
        HashMap<String, Integer> doc1 = new HashMap<>();
        doc1.put("apple", 2);
        doc1.put("orange", 1);
        doc1.put("banana", 3);
        documentTermFrequencies.put("doc1", doc1);


        HashMap<String, Integer> doc2 = new HashMap<>();
        doc2.put("apple", 1);
        doc2.put("orange", 3);
        doc2.put("banana", 2);
        documentTermFrequencies.put("doc2", doc2);


        HashMap<String, Double> tfMap = calculateTF(termFrequencies);
        HashMap<String, Double> idfMap = calculateIDF(documentTermFrequencies);
        HashMap<String, Double> tfidfMap = calculateTFIDF(termFrequencies, idfMap);


        System.out.println("TF Values:");
        tfMap.forEach((term, tf) -> System.out.println(term + ": " + tf));


        // Print IDF values
        System.out.println("IDF Values:");
        idfMap.forEach((term, idf) -> System.out.println(term + ": " + idf));


        // Print TF-IDF values
        System.out.println("TF-IDF Values:");
        tfidfMap.forEach((document, tfidfValues) -> System.out.println(document + ": " + tfidfValues));
    }
}




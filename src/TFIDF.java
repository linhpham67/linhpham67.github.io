import java.util.*;


public class TFIDF {
    public void calculateTF(HashMap<String, Integer> termFrequencies, String[] terms) {
        for (String term : terms) {
            if (termFrequencies.containsKey(term)) {
                termFrequencies.put(term, termFrequencies.get(term)+1);
            } else {
                termFrequencies.put(term, 1);
            }
        }

//        HashMap<String, Double> tfMap = new HashMap<>();
//        double totalTerms = termFrequencies.values().stream().mapToDouble(Integer::doubleValue).sum();
//        // formula to calculate tf
//        termFrequencies.forEach((term, tf) -> {
//            double tfValue = tf / totalTerms;
//            tfMap.put(term, tfValue);
//        });
//        return tfMap;
    }

    public void calculateIDF(HashMap<String, Double> idfMap, HashMap<String, ArrayList<String>> documentTermFrequencies, int totalDocuments) {
        for (String term : documentTermFrequencies.keySet()) {
            int documentFrequency = documentTermFrequencies.get(term).size();
            double idf = Math.log((double) totalDocuments / documentFrequency);
            idfMap.put(term, idf);
        }
    }

    public void calculateTFIDF(HashMap<String, Double> tfidfMap, HashMap<String, Integer> termFrequencies, HashMap<String, Double> idfMap) {
        for (String term : termFrequencies.keySet()) {
            double tf = termFrequencies.get(term);
            double idf = idfMap.get(term);
            double tfidf = tf * idf;
            tfidfMap.put(term, tfidf);
        }
    }

//    public static HashMap<String, Double> calculateIDF(HashMap<String, HashMap<String, Integer>> documentTermFrequencies) {
//        HashMap<String, Double> idfMap = new HashMap<>();//store calculated idf values
//        int totalDocuments = documentTermFrequencies.size(); //total no of docs
//        Set<String> allTerms = new HashSet<>();
//
//        for (Map<String, Integer> terms : documentTermFrequencies.values()) {
//            allTerms.addAll(terms.keySet());
//        }
//        //calculate IDF
//        for (String term : allTerms) {
//            long documentCount = documentTermFrequencies.values().stream()
//                    .filter(d -> d.containsKey(term))
//                    .count();
//            double idfValue = Math.log((double) totalDocuments / documentCount);
//            idfMap.put(term, idfValue);
//        }
//        return idfMap;
//    }


//    public static HashMap<String, Double> calculateTFIDF(HashMap<String, Integer> termFrequencies,
//                                                         HashMap<String, Double> idfMap) {
//        HashMap<String, Double> tfidfMap = new HashMap<>();
//
//
//        // Calculate TF
//        HashMap<String, Double> tfMap = calculateTF(termFrequencies);
//
//
//        // Calculate TF-IDF
//        tfMap.forEach((term, tfValue) -> {
//            double idfValue = idfMap.getOrDefault(term, 0.0);
//            double tfidfValue = tfValue * idfValue;
//            tfidfMap.put(term, tfidfValue);
//        });
//
//
//        return tfidfMap;
//    }
    public static void main(String[] args) {
        TFIDF calculator = new TFIDF();

        String[] documents = {
                "Document 1 about the importance of artificial intelligence",
                "Document 2 about the advantages of natural language processing",
                "Document 3 about the impact of machine learning on society"
        };

        HashMap<String, ArrayList<String>> documentTermFrequencies = new HashMap<>();
        HashMap<String, Integer> termFrequencies = new HashMap<>();
        HashMap<String, Double> idfMap = new HashMap<>();
        HashMap<String, Double> tfidfMap = new HashMap<>();

        int totalDocuments = documents.length;

        for (String document : documents) {
            String[] terms = document.split(" ");
            calculator.calculateTF(termFrequencies, terms);

            for (String term : terms) {
                if (documentTermFrequencies.containsKey(term)) {
                    documentTermFrequencies.get(term).add(document);
                } else {
                    ArrayList<String> documentList = new ArrayList<>();
                    documentList.add(document);
                    documentTermFrequencies.put(term, documentList);
                }
            }
        }

        calculator.calculateIDF(idfMap, documentTermFrequencies, totalDocuments);
        calculator.calculateTFIDF(tfidfMap, termFrequencies, idfMap);

        System.out.println("TFIDF: " + tfidfMap);
    }
}




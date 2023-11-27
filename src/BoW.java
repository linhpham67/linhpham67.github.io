import java.util.*;

public class BoW {

    //tokenization: splits the sentence and adds each word in a set called
    //vocabulary
    private static Set<String> buildVocabulary(String[] documents) {
        Set<String> vocabulary = new HashSet<>();

        for (String document : documents) {
            String[] words = document.toLowerCase().split("\\s+");

            for (String word : words) {
                vocabulary.add(word);
            }
        }
        return vocabulary;
    }

    //get index of the word in vocabulary set
    private static int getIndex(String word, Set<String> vocabulary) {
        int index = 0;

        for (String term : vocabulary) {
            if (term.equals(word)) {
                return index;
            }
            index++;
        }
        return 0;
    }

    //represent documents as vectors
    private static Map<Integer, Integer>[] getDocsVectors(String[] documents, Set<String> vocabulary) {
        Map<Integer, Integer>[] docVectors = new HashMap[documents.length];

        for (int i = 0; i < documents.length; i++) {
            String[] words = documents[i].toLowerCase().split("\\s+");
            docVectors[i] = new HashMap<>();

            for (String word : words) {
                int index = getIndex(word,vocabulary);

                if (index != -1) {
                    docVectors[i].put(index, docVectors[i].getOrDefault(index,0)+1);
                }
            }
        }
        return docVectors;
    }

    public static void main(String[] args) {
        String[] documents = {
                "We saw three birds and three cats today",
                "The two birds flew away and two cats ran away",
                "And the last bird stays",
                "We heard five birds and five cats will come",
        };
        Set<String> vocabulary = buildVocabulary(documents);
        Map<Integer, Integer>[] docVectors = getDocsVectors(documents, vocabulary);

        System.out.println("Vocab: "+ vocabulary);

        System.out.println("Document Vectors: ");
        for (int i = 0; i < docVectors.length; i++) {
            System.out.println("Document " + (i + 1) + ": " + docVectors[i]);
        }
    }
//five, heard, will, come, birds, we, cats, and

}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AutocompleteTrie {
    public class TrieNode{
        HashMap<Character, TrieNode> children = new HashMap<>();
        char c;
        boolean isCompleteWord = false;

        public TrieNode(char c) {
            this.c = c;
        }
        public TrieNode() {
            children = new HashMap<>();
        }
    }

    public class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) { //insert word into trie
            TrieNode node = root; //initialise
            for (char c : word.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode(c));
                }
                node = node.children.get(c);
            }
            node.isCompleteWord = true;
        }

        public List<String> autocomplete(String prefix) { //returns a list of search suggestions given prefix
            TrieNode node = root;
            List<String> suggestions = new ArrayList<>();

            for (char c : prefix.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    return suggestions; //empty
                }
                node = node.children.get(c); //point to the current char of the prefix in trie
            }

            HelperAuto(node, prefix, suggestions);
            return suggestions;

        }

        private void HelperAuto(TrieNode node, String prefix, List<String> suggestions) {
            if (node.isCompleteWord) {
                suggestions.add(prefix);
            }

            for (char c : node.children.keySet()) {
                HelperAuto(node.children.get(c), prefix + c, suggestions);

            }
        }

    }
    public static void main(String[] args) {
        AutocompleteTrie t = new AutocompleteTrie();
        AutocompleteTrie.Trie trie = t.new Trie();

        List<String> dataset = loadDatasetFromCSV("/Users/lnn/Desktop/mymoviedb.csv");

        for (String word : dataset) {
            trie.insert(word);
        }

        System.out.println(trie.autocomplete("Hell Baby"));

    }

    private static List<String> loadDatasetFromCSV(String filePath) {
        List<String> dataset = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the CSV line into words or phrases (adjust delimiter if needed)
                String[] words = line.split(","); // Assuming a comma (,) is the delimiter

                // Add each word or phrase to the dataset
                for (String word : words) {
                    dataset.add(word.trim()); // Trim to remove leading/trailing spaces
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataset;
    }


}








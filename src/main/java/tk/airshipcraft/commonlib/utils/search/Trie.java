package tk.airshipcraft.commonlib.utils.search;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Implements a trie (prefix tree) data structure that provides fast retrieval of strings based on their prefixes.
 * It is commonly used for autocomplete systems, spell checkers, and other applications that require quick searches
 * of a large collection of strings.</p>
 *
 * <p>This trie supports inserting words and searching for all words that match a given prefix. It can also be used to
 * suggest completions for partial words.</p>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-03-30
 */
public final class Trie {

    private Map<Character, Trie> children;
    private String word;
    // instead of storing only the suffix and re-concatenating the original word for
    // every lookup we always store the full word and depth as a relative offset
    private int depth;
    //whether this non-leaf also represents the end of a word
    private boolean isEnd;

    private Trie(String word, int depth) {
        this.word = word;
        this.depth = depth;
        this.isEnd = false;
    }

    /**
     * Factory method to create a new Trie with initialized children.
     *
     * @return A new instance of a Trie with an empty string and depth 0.
     */
    public static Trie getNewTrie() {
        Trie trie = new Trie("", 0);
        trie.children = new HashMap<>();
        return trie;
    }

    /**
     * Checks if this node of the trie is a leaf node (does not have any children).
     *
     * @return True if the node is a leaf, false otherwise.
     */
    public boolean isLeaf() {
        return children == null;
    }

    /**
     * Inserts a word into the trie. If the word already exists, no changes are made.
     * The insertion process constructs the necessary nodes to represent the word within the trie.
     *
     * @param wordToInsert The word to be inserted into the trie.
     */
    public void insert(String wordToInsert) {
        if (isLeaf()) {
            if (word.equals(wordToInsert)) {
                return;
            }
            children = new HashMap<>();
            // insert current suffix
            if (word.length() > depth) {
                children.put(word.charAt(depth), new Trie(word, depth + 1));
                this.word = word.substring(0, depth);
            } else {
                isEnd = true;
            }
        }
        Trie targetNode = children.computeIfAbsent(wordToInsert.charAt(depth), c -> new Trie(wordToInsert, depth + 1));
        targetNode.insert(wordToInsert);
    }

    /**
     * Searches the trie for words that match a given prefix.
     *
     * @param prefix The prefix to match against words in the trie.
     * @return A list of words that match the given prefix.
     */
    public List<String> match(String prefix) {
        List<String> result = new ArrayList<>();
        matchWord(prefix, result);
        return result;
    }

    /**
     * Provides auto-completion suggestions based on the input arguments, treating them as space-separated parts of a prefix.
     * This can be useful for command-line applications or chat interfaces where users type in commands or queries.
     *
     * @param args An array of strings representing parts of a command or query.
     * @return A list of auto-completion suggestions based on the provided arguments.
     */
    public List<String> complete(String[] args) {
        String full = String.join(" ", args);
        List<String> matches = match(full);
        if (args.length < 2) {
            return matches;
        }
        int elementsToRemove = args.length - 1;
        for (int i = 0; i < args.length; i++) {
            String mod = matches.get(i);
            int startingSpot = StringUtils.ordinalIndexOf(mod, " ", elementsToRemove) + 1;
            matches.set(i, mod.substring(startingSpot));
        }
        return matches;
    }

    /**
     * Helper method to recursively search for and collect words in the trie that match the given word or prefix.
     * This method contributes to the implementation of the {@link #match(String)} method.
     *
     * @param wordToMatch The word or prefix to match.
     * @param result The list to collect matching words.
     */
    private void matchWord(String wordToMatch, List<String> result) {
        if (isLeaf()) {
            if (wordToMatch.length() <= this.word.length()) {
                result.add(word);
                return;
            }
            if (wordToMatch.length() > this.word.length()) {
                //we can not be a prefix if we are shorter
                return;
            }
            for (int i = depth; i < this.word.length(); i++) {
                if (wordToMatch.charAt(i) != this.word.charAt(i)) {
                    return;
                }
            }
            result.add(this.word);
        } else {
            if (isEnd) {
                result.add(this.word);
            }
            if (wordToMatch.length() <= depth) {
                //valid prefix from here on and deeper, so deep search and add everything below
                for (Trie subTrie : children.values()) {
                    subTrie.matchWord(wordToMatch, result);
                }
                return;
            }
            Trie deeperNode = children.get(wordToMatch.charAt(depth));
            if (deeperNode != null) {
                deeperNode.matchWord(wordToMatch, result);
            }
        }
    }
}

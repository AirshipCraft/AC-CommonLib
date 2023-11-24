/**
 * <p>The {@code tk.airshipcraft.commonlib.utils.search} package contains implementations
 * of various string search and distance algorithms. These are essential tools for performing
 * text analysis, enabling features like search optimization, string comparison, and pattern matching.</p>
 *
 * <h2>Classes and Interfaces:</h2>
 * <ul>
 *   <li><b>{@link tk.airshipcraft.commonlib.utils.search.DukeJaroWinklerAlgorithm}</b>: An optimized implementation of the Jaro-Winkler similarity algorithm.</li>
 *   <li><b>{@link tk.airshipcraft.commonlib.utils.search.NormalizedLevenshteinAlgorithm}</b>: An implementation of the Levenshtein distance algorithm, normalized to a 0-1 scale.</li>
 *   <li><b>{@link tk.airshipcraft.commonlib.utils.search.SearchAlgorithm}</b>: An enumeration that provides a selection of different search algorithms for easy usage.</li>
 *   <li><b>{@link tk.airshipcraft.commonlib.utils.search.StringDistance}</b>: An interface defining a method for calculating distances or similarities between strings.</li>
 *   <li><b>{@link tk.airshipcraft.commonlib.utils.search.Trie}</b>: A data structure for efficient string retrieval and pattern matching.</li>
 * </ul>
 *
 * <h3>Usage Examples:</h3>
 *
 * <h4>DukeJaroWinklerAlgorithm:</h4>
 * <pre>{@code
 * StringDistance jaroWinkler = new DukeJaroWinklerAlgorithm();
 * double similarity = jaroWinkler.calculate("string1".getBytes(), "string2".getBytes());
 * System.out.println("Similarity: " + similarity);
 * }</pre>
 *
 * <h4>NormalizedLevenshteinAlgorithm:</h4>
 * <pre>{@code
 * StringDistance levenshtein = new NormalizedLevenshteinAlgorithm();
 * double distance = levenshtein.calculate("string1".getBytes(), "string2".getBytes());
 * System.out.println("Distance: " + distance);
 * }</pre>
 *
 * <h4>SearchAlgorithm:</h4>
 * <pre>{@code
 * double dukeScore = SearchAlgorithm.DUKE_JARO_WINKLER.calculate("string1".getBytes(), "string2".getBytes());
 * System.out.println("Duke Jaro-Winkler Score: " + dukeScore);
 * }</pre>
 *
 * <h4>Trie:</h4>
 * <pre>{@code
 * Trie trie = Trie.getNewTrie();
 * trie.insert("hello");
 * trie.insert("world");
 * List<String> matches = trie.match("he");
 * System.out.println("Matches: " + matches);
 * }</pre>
 *
 * <p>Each class and interface is designed to be interoperable where suitable, and can be integrated
 * into larger systems requiring text search and processing capabilities.</p>
 *
 * @since 1.0
 */
package tk.airshipcraft.commonlib.utils.search;

import javax.swing.*;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanCoding {

    public static HashMap<Character, String> getCodes(HuffmanNode root) {
        HashMap<Character, String> huffmanCodes = new HashMap<>();
        getCodesHelper(root, "", huffmanCodes);
        return huffmanCodes;
    }

    private static void getCodesHelper(HuffmanNode node, String code, HashMap<Character, String> huffmanCodes) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null && node.data != '$') {
            huffmanCodes.put(node.data, code);
            return;
        }
        getCodesHelper(node.left, code + "0", huffmanCodes);
        getCodesHelper(node.right, code + "1", huffmanCodes);
    }

    public static String encode(String text, HashMap<Character, String> huffmanCodes) {
        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(c));
        }
        return encodedText.toString();
    }

    public static String decode(String encodedText, HuffmanNode root) {
        StringBuilder decodedText = new StringBuilder();
        HuffmanNode current = root;
        for (char bit : encodedText.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else if (bit == '1') {
                current = current.right;
            }
            if (current.left == null && current.right == null) {
                decodedText.append(current.data);
                current = root;
            }
        }
        return decodedText.toString();
    }

    public static void main(String[] args) {
        //String text = "abracadabra";
        //String text = "aabbbaaccd";
        //String text = "Huffman encoding is a lossless data compression algorithm that assigns variable-length binary codes to input characters based on their frequencies of occurrence. The process begins by calculating the frequency of each character in the data, then building a binary tree—called the Huffman tree—where each leaf node represents a character and its frequency. Characters that occur more frequently are placed closer to the root, resulting in shorter codes, while less frequent characters are deeper in the tree and receive longer codes. The codes generated are prefix codes, meaning no code is a prefix of another, which ensures unambiguous decoding. To encode data, each character is replaced by its corresponding binary code from the tree. Decoding is performed by traversing the same tree: starting at the root, each bit in the encoded message determines whether to move left (for 0) or right (for 1) until a leaf node (character) is reached. This efficient method reduces the overall size of the data by using fewer bits for common characters and more bits for rare ones, making Huffman encoding widely used in file compression and data transmission.";
        String text = "cat";

        // Calculate character frequencies
        HashMap<Character, Integer> frequencies = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }

        // Create priority queue
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (char c : frequencies.keySet()) {
            pq.add(new HuffmanNode(c, frequencies.get(c)));
        }

        // Build Huffman tree
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            //SwingUtilities.invokeLater(() -> new HuffmanBinaryTreeGUI(left, "From Priority Queue: Next Left Tree is: "));
            //WaitForInput.WaitSeconds(3);
            //Thread.sleep(3000);
            HuffmanNode right = pq.poll();
            //SwingUtilities.invokeLater(() -> new HuffmanBinaryTreeGUI(right, "From Priority Queue: Next Right Tree is: "));
            //WaitForInput.WaitSeconds(3);
            // Compute and show the frequency
            int newfrequency = left.frequency + right.frequency;
            //HuffmanNode parent = new HuffmanNode('$', left.frequency + right.frequency);
            HuffmanNode parent = new HuffmanNode('$', left.frequency + right.frequency);

            parent.left = left;
            parent.right = right;
            pq.add(parent);
            //SwingUtilities.invokeLater(() -> new HuffmanBinaryTreeGUI(parent, "Added this tree to priority queue: "));
            //WaitForInput.WaitSeconds(3);
        }
        HuffmanNode root = pq.poll();

        SwingUtilities.invokeLater(() -> new HuffmanBinaryTreeGUI(root, "The final Huffman Tree is: "));

        // Generate Huffman codes
        HashMap<Character, String> huffmanCodes = getCodes(root);
        System.out.println("Huffman Codes: " + huffmanCodes);

        // Encode the text
        String encodedText = encode(text, huffmanCodes);
        System.out.println("Encoded Text: " + encodedText);

        // Decode the text
        String decodedText = decode(encodedText, root);
        System.out.println("Decoded Text: " + decodedText);
    }
}
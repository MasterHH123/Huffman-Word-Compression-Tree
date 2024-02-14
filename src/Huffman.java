import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {
    public class Node{
        char ch;
        int freq;
        public Node left = null;
        public Node right = null;

        Node(char ch, int freq){
            this.ch = ch;
            this.freq = freq;
        }

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

    }

    public static boolean isLeaf(Node root){
        return root.left == null && root.right == null;
    }

    public static void encodeData(Node root, String str, Map<Character, String> huffman) {
        if(root == null){
            return;
        }
        if(isLeaf(root)){
            huffman.put(root.ch, str.length() > 0 ? str : "1");
        }
        encodeData(root.left, str + "0", huffman);
        encodeData(root.right, str + "1",huffman);
    }

    public static int decodeData(Node root, int index, StringBuilder newString) {
        if(root == null){
            return index;
        }
        if(isLeaf(root)){
            System.out.println(root.ch);
            return index;
        }

        index++;
        root = (newString.charAt(index) == '0') ? root.left : root.right;
        index = decodeData(root, index, newString);

        return index;
    }

    public static void createHuffmanTree(String text) {
        if(text == null || text.length() == 0){
            return;
        }

        HashMap<Character, Integer> count = new HashMap<>();

        for(char ch : text.toCharArray()) {
            count.put(ch, count.getOrDefault(ch, 0)+1);
        }

        Huffman huffman = new Huffman();
        //Priority queue to store the nodes
        PriorityQueue<Node> pq1 = new PriorityQueue<>(Comparator.comparingInt(I -> I.freq));
        for(Map.Entry<Character, Integer> data : count.entrySet()) {
            pq1.add(huffman.new Node(data.getKey(), data.getValue()));
        }

        //highest priority = lowest frequency
        while(pq1.size() != 1){
            Node left = pq1.poll();
            Node right = pq1.poll();

            int freqSum = left.freq + right.freq;
            pq1.add(huffman.new Node('\0', freqSum, left, right));
        }

        Node root = pq1.peek();

        HashMap<Character, String> huffmanEncoding = new HashMap<>();
        encodeData(root, "", huffmanEncoding);

        System.out.println("Huffman codes are: " + huffmanEncoding);
        System.out.println("String is: " + text);

        StringBuilder sb = new StringBuilder();

        for(char c : text.toCharArray()){
            sb.append(huffmanEncoding.get(c));
        }

        System.out.println("The encoded string is: " + sb);
        System.out.println("The decoded string is: ");

        if(isLeaf(root)){
            while(root.freq --> 0) {
                System.out.println(root.ch);
            }
        } else {
            int index = -1;
            while(index < sb.length()-1) {
                index = decodeData(root, index, sb);
            }
        }

    }

    public static void main(String[] args) {
        String text1 = "Oasis";
        String text2 = "Look around at all the plastic people\r\n" +
                "Who live without a care\r\n";
        String text3 = "Lately did you ever feel the pain\r\n" + "In the morning rain, as it soaks you to the bone\r\n" +
                "Maybe I just want to fly\r\n" + "Want to live, I don't want to die\r\n" + "Maybe I just want to breathe\r\n" +
                "Maybe I just don't believe\r\n" + "Maybe you're the same as me\r\n" + "We see things they'll never see\r\n" +
                "You and I are gonna live forever";
        String text4 = "I need to be myself\r\n" + "I can't be no one else\r\n" + "I'm feeling supersonic, give me gin and tonic\r\n" +
                "You can have it all, but how much do you want it?\r\n";

        createHuffmanTree(text1);
        System.out.println("\n");
        createHuffmanTree(text2);
        System.out.println("\n");
        createHuffmanTree(text3);
        System.out.println("\n");
        createHuffmanTree(text4);
    }
}

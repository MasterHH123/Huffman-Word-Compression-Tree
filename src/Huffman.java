import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {
    private class Node{
        char ch;
        int freq;
        Node left = null;
        Node right = null;

        Node(char ch, int freq){
            this.ch = ch;
            this.freq = freq;
        }

    }
}

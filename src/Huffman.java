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


}

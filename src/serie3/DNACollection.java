package serie3;

import java.util.HashMap;

public class DNACollection {
    class Node {
        // [A, C, T, G]
        boolean flag = false;
        Node[] children = new Node[4];
        public Node(){}
    }
    Node root;
    final HashMap<Character, Integer> char_position = new HashMap<Character, Integer>(){{
        put('A', 0);
        put('C', 1);
        put('T', 2);
        put('G', 3);
    }};
    public DNACollection() {
        this.root = new Node();
    }

    public void add(String fragment){
        Node curr = root;
        char c;
        for (int i = 0; i < fragment.length(); i++){
            c = fragment.charAt(i);
            if (char_position.containsKey(c)) {
                if (curr.children[char_position.get(c)] == null){
                    curr.children[char_position.get(c)] = new Node();
                }
                curr = curr.children[char_position.get(c)];
              } else {
                  return;
              }
        }
        curr.flag = true;
    }

    public int prefixCount(String prefix){
        int count = 0, i;
        Node curr = root;
        char c;
        for (i = 0; i < prefix.length(); i++){
            if (curr == null) break;
            c = prefix.charAt(i);
            if (char_position.containsKey(c)) {
                curr = curr.children[char_position.get(c)];
            } else {
                break;
            }
        }
        if (curr == null) return count;
        for (Node node: curr.children){
            count += count(node);
        }
        return curr.flag ? ++count : count;
    }

    public int count(Node root){
        if (root == null) return 0;
        int count = 0;
        for (Node node: root.children) {
            if (node != null) {
                count += count(node);
            }
        }
        return root.flag ? ++count : count;
    }
}


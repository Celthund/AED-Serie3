package serie3;

import java.util.HashMap;

public class DNACollection {
    class Node {
        // [A, C, T, G]
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
    }

    public int prefixCount(String prefix){
        int count = 0;
        Node curr = root;
        char c;
        for (int i = 0; i < prefix.length(); i++){
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
        return count;
    }

    public int count(Node root){
        if (root == null) return 0;
        int count = 0;
        boolean no_children = true;
        for (Node node: root.children) {
            if (node != null) {
                count += count(node);
                no_children = false;
            }
        }
        return no_children ? ++count : count;
    }
}


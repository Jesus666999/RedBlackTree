//Reynoso Garcia Jesus Salvador 22310400    4P
package Principal;
public class run {
    public static void main(String[] args) {
        Node root = new Node((int) (Math.random() * (500 - 1) + 1));
        Tree arbol = new Tree();
        arbol.setRoot(root);
        
        arbol.insert((int) (Math.random() * (500 - 1) + 1));
        arbol.insert((int) (Math.random() * (500 - 1) + 1));
        arbol.insert((int) (Math.random() * (500 - 1) + 1));
        arbol.insert((int) (Math.random() * (500 - 1) + 1));
        arbol.insert((int) (Math.random() * (500 - 1) + 1));
        arbol.insert((int) (Math.random() * (500 - 1) + 1));
        arbol.insert((int) (Math.random() * (500 - 1) + 1));
        arbol.insert((int) (Math.random() * (500 - 1) + 1));
        arbol.insert((int) (Math.random() * (500 - 1) + 1));
        arbol.insert((int) (Math.random() * (500 - 1) + 1));
        
        root = arbol.getRoot(root);
        System.out.println("Root: "+arbol.getRoot(root).getValue());
        System.out.println("Preorder");
        arbol.preOrder(root);
        System.out.println("_________________________________\nPostorder\n");
        arbol.postOrder(root);
        System.out.println("_________________________________\nIn order\n");
        arbol.inOrder(root);
        System.out.println("_________________________________\n");
        arbol.printLevelOrder(root);
    }
    
}

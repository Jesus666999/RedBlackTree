//Reynoso Garcia Jesus Salvador 22310400    4P
package Principal;

public class Tree {

    Node root;

    public Node getRoot(Node node) {
        boolean found = false;
        while (found == false){
            if (node.getParent() == null){
                found = true;
            }else{
                node = node.getParent();
            }
        }
        return node;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
    
    public void rotateRight(Node node) {
        Node parent = node.getParent();
        Node leftChild = node.getLeft();
        node.left = leftChild.getRight();

        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }
        leftChild.right = node;
        node.parent = leftChild;
        replaceParentChild(parent, node, leftChild);
    }

    public void replaceParentChild(Node parent, Node anterior, Node nuevo) {
        if (parent == null) {
            root = nuevo;
        } else if (parent.left == anterior) {
            parent.left = nuevo;
        } else if (parent.right == anterior) {
            parent.right = nuevo;
        } else {
            throw new IllegalStateException("El nodo no es hijo del padre");
        }
        if (nuevo != null) {
            nuevo.parent = parent;
        }
    }

    public void rotateLeft(Node node) {
        Node parent = node.getParent();
        Node rightChild = node.getRight();

        node.right = rightChild.getLeft();

        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }
        rightChild.left = node;
        node.parent = rightChild;
        replaceParentChild(parent, node, rightChild);
    }

    public void insert(int valor) {
        Node node = root;
        Node parent = null;

        while (node != null) {
            parent = node;
            if (valor < node.getValue()) {
                node = node.left;
            } else if (valor > node.getValue()) {
                node = node.right;
            } else {
                throw new IllegalStateException("Ya existe un nodo con valor " + valor);
            }
        }

        Node newNode = new Node(valor);
        newNode.setColor(true);
        if (parent == null) {
            root = newNode;
        } else if (valor < parent.getValue()) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }
        newNode.setParent(parent);
        fix(newNode);
    }

    public void fix(Node node) {
        Node parent = node.parent;

        if (parent == null) {
            node.setColor(false);
            return;
        }

        if (parent.color == false) {
            return;
        }

        Node grand = parent.getParent();

        if (grand == null) {
            parent.setColor(false);
            return;
        }

        Node uncle = getUncle(parent);

        if (uncle != null && uncle.isColor() == true) {
            parent.setColor(false);
            grand.setColor(true);
            uncle.setColor(false);
            fix(grand);
        } else if (parent == grand.getLeft()) {
            if (node == parent.getRight()) {
                rotateLeft(parent);
                parent = node;
            }
            rotateRight(grand);
            parent.setColor(false);
            grand.setColor(true);
        } else {
            if (node == parent.getLeft()) {
                rotateRight(parent);
                parent = node;
            }
            rotateLeft(grand);
            parent.setColor(false);
            grand.setColor(true);
        }
    }

    public Node getUncle(Node parent) {
        Node grandparent = parent.getParent();
        if (grandparent.left == parent) {
            return grandparent.right;
        } else if (grandparent.right == parent) {
            return grandparent.left;
        } else {
            throw new IllegalStateException("El padre no es hijo del abuelo");
        }
    }

    public void preOrder(Node node) {
        if (node == null){
            return;
        }
        System.out.println(node.getValue());
        preOrder(node.left);
        preOrder(node.right);
    }
    
    public void postOrder(Node node) {
        if (node == null){
            return;
        }
        preOrder(node.left);
        preOrder(node.right);
        System.out.println(node.getValue());
    }
    
    public void inOrder(Node node){
        if (node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.getValue());
        inOrder(node.right);
    }
    
    public void printLevelOrder(Node root) {
        int h = height(root);
        for (int i = 1; i <= h; i++) {
            System.out.print("Nivel: " + i + "\n");
            printCurrentLevel(root, i);
            System.out.println("\n");
        }

    }

    public int height(Node root) {
        if (root == null) {
            return 0;
        } else {
            int lheight = height(root.left);
            int rheight = height(root.right);
            if (lheight > rheight) {
                return (lheight + 1);
            } else {
                return (rheight + 1);
            }
        }
    }

    public void printCurrentLevel(Node root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            System.out.print(root.getValue() + ": "+root.isColor() + "|| ");
        } else if (level > 1) {
            printCurrentLevel(root.left, level - 1);
            printCurrentLevel(root.right, level - 1);
        }

    }

}

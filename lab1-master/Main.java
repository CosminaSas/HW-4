class Node {
    int key, height;
    Node left, right, parent;

    public Node ()
    {
        height = 0;
    }
    public Node(int d)
    {
        key = d;
        height = 1;
    }
}

class AVLTree {

    Node root;

    //left rotate
    Node leftRotate(AVLTree T, Node x){
        Node y = x.right; //keep the right part in an auxiliar node y

        //turn y's left subtree into x's right subtree
        x.right = y.left;

        if(y.left != null)
            y.left.parent = x;
        y.parent = x.parent; //link x's parent to y

        if(x.parent == null)
            T.root = y;
        else
        {
            if (x == x.parent.left)
                x.parent.left = y;
            else
                x.parent.right = y;
        }

        y.left = x; //put x on y's left
        x.parent = y;

        return y;
    }

    Node rightRotate (AVLTree T, Node y){
        Node x = y.left;

        //turn x's right subtree into y's left subtree
        y.left = x.right;

        if(x.right != null)
            x.right.parent = y;
        x.parent = y.parent; //link y's parent to x

        if(y.parent == null)
            T.root = x;
        else
        {
            if (y == y.parent.right)
                y.parent.right = x;
            else
                y.parent.left = x;
        }

        x.right = y; //put y on x's right
        y.parent = x;

        return x;

    }

    //absolute difference of 2 numbers
    int abs(int x, int y)
    {
        if( x > y)
            return x-y;
        return y-x;
    }

    //balance the tree => implement the rotations needed
    Node balance (AVLTree T, Node x)
    {

        if(x.left != null && x.right!= null)
        {
            if (abs(x.left.height, x.right.height) <= 1)
                return x;
            else
            if (x.left.height > x.right.height)
            {
                //case left-right
                Node y = x.left;
                if(y.left.height < y.right.height)
                    leftRotate(T, y);
                return rightRotate(T, x); //case left-left
            }
            else {
                Node y = x.right;
                //case right-left
                if(y.left.height > y.right.height)
                    rightRotate(T, y);
                return leftRotate(T, x); //case left-left
            }
        }
        return x;
    }

    //return the new root of T
    Node insert (AVLTree T, Node x, Node z)
    {
        if ( x == null) //z is the first node added => T.root = z
        {
            z.height = 1;
            return z;
        }

        //insert in the left part
        if(z.key <= x.key)
        {
            Node y = insert(T, x.left, z);
            x.left = y;
            y.parent = x;
            x.height = y.height + 1; //update the height
        }
        //insert in the right part
        else
        {
            Node y = insert(T, x.right, z);
            x.right = y;
            y.parent = x;
            x.height = y.height + 1; //update the height
        }

        x = balance(T, x); //the root

        return x;

    }

    void preOrder (Node node) {
        if (node != null) {
            System.out.print(node.key + " ");

            preOrder(node.left);

            preOrder(node.right);

        }
    }
}

public class Main {
    public static void main (String[] args){
        AVLTree tree = new AVLTree();



        //powerpoint - pag 22
        tree.root = tree.insert(tree, tree.root, new Node(10));
        tree.root = tree.insert(tree, tree.root, new Node (2));
        tree.root = tree.insert(tree, tree.root, new Node (15));
        tree.root = tree.insert(tree, tree.root, new Node (13));
        tree.root = tree.insert(tree, tree.root, new Node (20));
        tree.root = tree.insert(tree, tree.root, new Node (25));



        /*

        //powerpoint - pag 24
        tree.root = tree.insert(tree, tree.root, new Node(5));
        tree.root = tree.insert(tree, tree.root, new Node (3));
        tree.root = tree.insert(tree, tree.root, new Node (8));
        tree.root = tree.insert(tree, tree.root, new Node (7));
        tree.root = tree.insert(tree, tree.root, new Node (15));
        tree.root = tree.insert(tree, tree.root, new Node (6));


         */

        System.out.println("Preorder tree : ");
        tree.preOrder(tree.root);
    }
}

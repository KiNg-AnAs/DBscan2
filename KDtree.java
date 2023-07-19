//anas el malki
//300248697

public class KDtree {
     private KDnode root;
    // construct empty tree
    public KDtree() {
   
   root= null;
    }
    public KDnode getroot(){
      return this.root;
    }
    public KDnode insert(Point3D P, KDnode node, int axis) { //the insert method that inserts nuumbers in our tree
        if (node == null){           
            node = new KDnode(P, axis);
        }
        else if (P.get(axis) <= node.value){
            node.left = insert(P, node.left, (axis+1) % 3);
        }
        else node.right = insert(P, node.right, (axis+1) % 3);
        
        return node;
    }

    public void add(Point3D point){
        root = insert(point, root, 0); // we start inserting our numbers from the root node
    }
        
}   
//anas el malki
//300248697
import java.util.ArrayList;
import java.util.List;



public class NearestNeighborsKD {
    
    private KDtree kdtree;

    public NearestNeighborsKD(List<Point3D> list) {
        kdtree = new KDtree();
       

            for (Point3D p : list) {
        kdtree.add(p); // the add method should call the
        // insert method given in pseudo-code
         }
        // plus possibly other initializations
        }
        
        //The search for neighbors in the k-d tree is done by the following method (called by the original rangeQuery method).
    public void rangeQuery(Point3D p, double eps,List<Point3D> N, KDnode node){
        if (node == null){
            return;//if the node is empty we stop here
        }
        if (p.distance(node.point) < eps){
            N.add(node.point);//if the point is in the range we need we add it to the list of neighbors
        }
        if (p.get(node.axis) - eps <= node.value){
        rangeQuery(p, eps, N, node.left);
        }
        if (p.get(node.axis) + eps > node.value){
        rangeQuery(p, eps,N, node.right);
        }
        return;
    }
    //a rangeQuery method that finds the nearest neighbors of a 3D point
    public List<Point3D> rangeQuery(Point3D p, double eps) {
        List<Point3D> neighbors= new ArrayList<>();
        rangeQuery(p, eps, neighbors, kdtree.getroot());// we add the neighbors and the root so we can call the second 
        return neighbors;
        }
}

//anas el malki
//300248697
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Exp3 {
    /**
 * @param args
 * @throws IOException
 */
public static void main(String[] args) throws IOException {
    //now we get the args from the terminal and convert the filename to String, the epsto Double and the minpts to int
            String filename=args[0];
            Double eps =Double.parseDouble(args[1]);
            int minpts = Integer.parseInt(args[2]);
            List dbscan = new ArrayList<>();
            long startTime = System.nanoTime();
            dbscan  = (DBScan.read(filename+".csv"));
            
            DBScan Scan;
            Scan = new DBScan(dbscan);
            Scan.setEps(eps);
            Scan.setMinPts(minpts);
            Scan.findClusters();
           
            Scan.save(filename);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime)/1000000 ;// in milliseconds.
            System.out.println("le temps pour trouver les voisins est : "+duration);
        }
            
        
        
}

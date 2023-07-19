//anas el malki
//300248697
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class DBScan{

    List<Point3D> DB, N;
    Point3D Q;
    double eps;
    int minPts, C;
    NearestNeighborsKD Nb;
    MyStack<Point3D> S = new MyStack<Point3D>();
    


    
    public DBScan(List<Point3D> DB){
        this.DB = DB;
        this.C = 0;

    }

    public void setEps(double eps){
        this.eps=eps;

    }

    public void setMinPts(int minPts){
        this.minPts=minPts;

    }

    public void findClusters(){
        Nb = new NearestNeighborsKD(DB);

        for ( Point3D P: DB){
            

            int Cluster = P.getcluster();
            if(Cluster != 0) continue;// to chack if it s not already processed

             N = Nb.rangeQuery(P, eps);// finding neighbors

            if (N.size() < minPts){ //checking if the number of neighbors is less than the minimum to not be classified as noise
                P.label= -1; // -1 means noise
                continue;}
            C ++; //next cluster
            P.label= C;
            
            for(int i = 0; i< N.size(); i++){
                S.push(N.get(i));                // expanding the neighbors
            }
            while (!S.empty()){
                Q = S.pop();   //processing the point Q
                int cl = Q.getcluster();

                if (cl== -1){//noise becomes border point
                    cl = C;}

                if (cl != 0){//check if it s previously processed
                    continue;}
                Q.label = C;
                N = Nb.rangeQuery(Q, eps);
                if (N.size() >= minPts){
                    for(int i = 0; i< N.size(); i++){
                        S.push(N.get(i));} //adding the new neighbors to the stack
                }

            }
        }
       
  
    }
//getters
    public int getNumberOfClusters(){

        return C;

    }

    public List<Point3D> getPoints(){
        return DB;

    }

//method to read the csv file
    public static List<Point3D> read(String filename) throws IOException 
    {			
           // used to import data from the CSV
          List<Point3D> reading = new ArrayList<Point3D>();

          try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            reader.readLine();//skips first line
            reader.readLine();//skips second line
            String line = reader.readLine();
             //check lines that arent null or x,y,z
            while (line!= null){
                String[] cords = line.split(",");
                reading.add(new Point3D(Double.parseDouble(cords[0]), Double.parseDouble(cords[1]), Double.parseDouble(cords[2])));// separates x y and z
                line = reader.readLine();//go to next line
                
            }
           
          }

          catch (FileNotFoundException e){
            e.printStackTrace();
          }
         
          return reading;

    }
// method to generate a random color for each cluster label
    private double[][] randomcolor(){
        int nbofclusters = getNumberOfClusters();
        double[][] valeur = new double[nbofclusters][3];
        Random rd = new Random(); // creating Random object
        
        for (int i =0; i< nbofclusters; i++){
            valeur[i][0] = Math.round(rd.nextDouble()*100.0)/100.0;
            valeur[i][1] = Math.round(rd.nextDouble()*100.0)/100.0;
            valeur[i][2] = Math.round(rd.nextDouble()*100.0)/100.0;
        }     
        return valeur;
    }
// method to save all the points with their clusters
    public void save(String filename) throws FileNotFoundException{
     
        double[][] RGB = randomcolor();
        OutputStreamWriter out = null;
        
        try{
            int x;
            out = new OutputStreamWriter(new FileOutputStream(filename+"_clusters_"+eps+"_"+minPts+"_"+getNumberOfClusters()+".csv"));

            out.write("x,y,z,C,R,G,B\n");

            for (Point3D pt3d : DB){
                if (pt3d.getcluster() == -1){
                    
                    out.write(pt3d.toString()+",0.0,0.0,0.0\n");
                }
                else {
                    x = pt3d.getcluster()-1;
                    
                    out.write(pt3d.toString()+","+RGB[x][0]+","+RGB[x][1]+","+RGB[x][2]+"\n");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}






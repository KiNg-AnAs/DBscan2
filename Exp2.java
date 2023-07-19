//anas el malki
//300248697
import java.util.List;
import java.util.ArrayList;
import java.io.*;  
import java.util.Scanner;  

public class Exp2 {
  
  // reads a csv file of 3D points (rethrow exceptions!)
  public static List<Point3D> read(String filename) throws Exception {
	  
    List<Point3D> points= new ArrayList<Point3D>(); 
	double x,y,z;
	
	Scanner sc = new Scanner(new File(filename));  
	// sets the delimiter pattern to be , or \n \r
	sc.useDelimiter(",|\n|\r");  

	// skipping the first line x y z
	sc.next(); sc.next(); sc.next();
	
	// read points
	while (sc.hasNext())  
	{  
		x= Double.parseDouble(sc.next());
		y= Double.parseDouble(sc.next());
		z= Double.parseDouble(sc.next());
		points.add(new Point3D(x,y,z));  
	}   
	
	sc.close();  //closes the scanner  
	
	return points;
  }
  
 
  public static void main(String[] args) throws Exception {  
  
    // not reading args[0]
	double eps= Double.parseDouble(args[1]); //on prend eps des arguments
    List<Point3D> points= Exp2.read(args[2]);  // on lit les points du csv et on les met dans une liste
	Integer anas = Integer.parseInt(args[3]); //anas est le multiple que l'on prend "par exemple 10 dans l'enoncé"
  
	
    
    NearestNeighborsKD nn= new NearestNeighborsKD(points);
    NearestNeighbors nn2= new NearestNeighbors(points);
    long startTime = System.nanoTime();//debut du compteue
	for(int i=0;i<points.size();i++){
        
		if(i%anas==0){
            Point3D query= new Point3D(points.get(i).getX(),points.get(i).getY(),points.get(i).getZ());
            List<Point3D> neighbors;
            
            if (args[0].length()==2){//ca veut dire que c'est kd
                 neighbors= nn.rangeQuery(query,eps);}
                 
                else{// si ce n' est pas kd donc c'est lin
                neighbors= nn2.rangeQuery(query,eps);
                
                }
        }
			
	}
    long endTime = System.nanoTime();//fin du compteur
    long duration = (endTime - startTime)/1000000 ;// in milliseconds.
    System.out.println("le temps pour trouver les voisins est : "+duration);
}

	
  }   


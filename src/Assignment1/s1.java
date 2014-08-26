package Assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class s1 {
	
	 public static ArrayList<String> readTxtFile(String filePath){
	        try {
	                String encoding="ASCII";
	                File file=new File(filePath);
	                if(file.isFile() && file.exists()){ //check if there is the file
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//consider the encoding type
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    ArrayList<String> text = new ArrayList<String>();
	                    while((lineTxt = bufferedReader.readLine()) != null){
	                    	text.add(lineTxt);
	                //        System.out.println(lineTxt);
	                      
	                    }
	                    read.close();
	                    return text;
	        }else{
	            System.out.println("Can't find your file");
	            return null;
	        }
	        } catch (Exception e) {
	            System.out.println("There is an error when read your file");
	            e.printStackTrace();
	            return null;
	        }
			
			
	     
	    }
	 
	 	public static PointPackage changeList(ArrayList<String> text) {
			String s1 = text.get(0);
			String[] num = s1.split(" ");
			int numRed = Integer.parseInt(num[0]);
			int numBlue = Integer.parseInt(num[1]);
			int intersect = Integer.parseInt(num[2]);
			
			PointPackage res = new PointPackage();
			
			ArrayList<vector> redPoint = new ArrayList<vector>();
			ArrayList<vector> bluePoint = new ArrayList<vector>();
			
			for (int i=1; i<=numRed; i++) {
				String s2 = text.get(i);
				String[] point = s2.split(" ");
				
				vector r = new vector();
				
				r.begin = new point(Integer.parseInt(point[0]), Integer.parseInt(point[1]));
				r.end = new point(Integer.parseInt(point[2]), Integer.parseInt(point[3]));
				
				redPoint.add(r);
				
			}
			
			for (int i=numRed+1; i<numRed + numBlue; i++) {
				String s2 = text.get(i);
				String[] point = s2.split(" ");
				
				vector b = new vector();
				
				b.begin = new point(Integer.parseInt(point[0]), Integer.parseInt(point[1]));
				b.end = new point(Integer.parseInt(point[2]), Integer.parseInt(point[3]));
				
				bluePoint.add(b);
			}
			
			res.intersect = intersect;
			res.bluePoint = bluePoint;
			res.redPoint = redPoint;
			
			return res;
	
		}
	 	
	 	private static boolean intersect(point p1, point p2, point p3, point p4) {
	 		
	 		int d1 = direction(p3,p4,p1);
	 		int d2 = direction(p3,p4,p2);
	 		int d3 = direction(p1,p2,p3);
	 		int d4 = direction(p1,p2,p4);
	 		
	 				
	 	    if ((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0) || (d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0)) {
	 	    	return true;
	 	    }
	 	    else 
	 	    	return false;
	 		
	 	}
	 	
	 	private static int direction(point p1, point p2, point p3) {
	 	//	return (p3-p1  *  p2 - p1)
	 	
	 		return   (p3.x - p1.x)*(p2.y - p1.y) - (p2.x - p1.x)*(p3.y - p1.y);
	 	}
	 	
	 	public static void validate(PointPackage res) {
	 		int test = res.intersect;
	 		int count = 0;
	 		
	 		ArrayList<vector> redPoint = new ArrayList<vector>();
			ArrayList<vector> bluePoint = new ArrayList<vector>();
			
			redPoint = res.redPoint;
		    bluePoint = res.bluePoint;
		    
		    for (vector r: redPoint) {
		    	point rbegin = r.begin;
		    	point rend = r.end;
		    	for (vector b: bluePoint) {
		    		point bbegin = b.begin;
		    		point bend = b.end;
		    		
		    		if (intersect(rbegin,rend,bbegin,bend))
		    			count ++;
		    	}
		    	
		    }
			
		    if (count == test)
		    	System.out.println("verified");
		    else 
		    	System.out.println("you have a error");
	 		
	 	}
	     
	    public static void main(String args[]){
	    	BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));      
	    	      System.out.print("Enter your test file name:");
//"C:\\Users\\Mao\\workspace\\COMP550_Algorithm\\src\\Assignment1\\"
	        String filePath = null;
			try {
				
				filePath = System.getProperty("user.dir")+"\\src\\Assignment1\\" + stdin.readLine();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				 System.out.println("There is an error when read your file");
				e.printStackTrace();
			}

	        ArrayList<String> text = readTxtFile(filePath);
	        validate(changeList(text));
	    }
	     
}

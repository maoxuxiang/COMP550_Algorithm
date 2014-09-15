package Assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
	 
	 	public static POINTPackage changeList(ArrayList<String> text) {
			String s1 = text.get(0);
			String[] num = s1.split(" ");
			int numRed = Integer.parseInt(num[0]);
			int numBlue = Integer.parseInt(num[1]);
			int intersect = Integer.parseInt(num[2]);
			
			POINTPackage res = new POINTPackage();
			
			ArrayList<SEGMENT> redPOINT = new ArrayList<SEGMENT>();
			ArrayList<SEGMENT> bluePOINT = new ArrayList<SEGMENT>();
			
			for (int i=1; i<=numRed; i++) {
				String s2 = text.get(i);
				String[] POINT = s2.split(" ");
				
				SEGMENT r = new SEGMENT();
				
				r.begin = new POINT(Integer.parseInt(POINT[0]), Integer.parseInt(POINT[1]));
				r.end = new POINT(Integer.parseInt(POINT[2]), Integer.parseInt(POINT[3]));
				
				redPOINT.add(r);
				
			}
			
			for (int i=numRed+1; i<numRed + numBlue; i++) {
				String s2 = text.get(i);
				String[] POINT = s2.split(" ");
				
				SEGMENT b = new SEGMENT();
				
				b.begin = new POINT(Integer.parseInt(POINT[0]), Integer.parseInt(POINT[1]));
				b.end = new POINT(Integer.parseInt(POINT[2]), Integer.parseInt(POINT[3]));
				
				bluePOINT.add(b);
			}
			
			res.intersect = intersect;
			res.bluePOINT = bluePOINT;
			res.redPOINT = redPOINT;
			
			return res;
	
		}
	 	
	 	private static boolean intersect(POINT p1, POINT p2, POINT p3, POINT p4) {
	 		
	 		int d1 = p3.direction(p4,p1);
	 		int d2 = p3.direction(p4,p2);
	 		int d3 = p1.direction(p2,p3);
	 		int d4 = p1.direction(p2,p4);
	 		
	 				
	 	    if ((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0) || (d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0)) {
	 	    	return true;
	 	    }
	 	    else 
	 	    	return false;
	 		
	 	}
	 	

	 	
	 	public static void BFxuxiang(POINTPackage res) {
	 		int test = res.intersect;
	 		int count = 0;
	 		
	 		ArrayList<SEGMENT> redPOINT = new ArrayList<SEGMENT>();
			ArrayList<SEGMENT> bluePOINT = new ArrayList<SEGMENT>();
			
			redPOINT = res.redPOINT;
		    bluePOINT = res.bluePOINT;
		    
		    for (SEGMENT r: redPOINT) {
		    	POINT rbegin = r.begin;
		    	POINT rend = r.end;
		    	for (SEGMENT b: bluePOINT) {
		    		POINT bbegin = b.begin;
		    		POINT bend = b.end;
		    		
		    		if (intersect(rbegin,rend,bbegin,bend))
		    			count ++;
		    	}
		    	
		    }
			
		    if (count == test)
		    	System.out.println("VERIFIED");
		    else 
		    	System.out.println("you have a error");
	 		
	 	}
	     
	    public static void main(String args[]){
	    	      
	        String filePath = null;

				
				filePath = System.getProperty("user.dir")+"/" + args[0];
				


	        ArrayList<String> text = readTxtFile(filePath);
	        BFxuxiang(changeList(text));
	    }
	     
}

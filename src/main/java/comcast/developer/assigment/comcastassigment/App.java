package comcast.developer.assigment.comcastassigment;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App 
{
	
	/**
	 * Method that will start the program
	 * @param args
	 */
    public static void main( String[] args )
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("Provide Origen x: ");
        int x=sc.nextInt();
        System.out.println("Provide Origen y: ");
        int y=sc.nextInt();

        App app=new App();
        System.out.println("Output:");

    	System.out.println(app.findCoordinates(x,y));
    	
    	sc.close();
        
    }
    
    /**
     * Method that returns the coordinates ordered by distance to new origin
     * @param x: x coordinate of new origin
     * @param y: y coordinate of new origin
     * @return String representation of the coordinates
     */
    public String findCoordinates(int x, int y){
    	 JSONParser parser = new JSONParser();

         try {
         	
         	Object obj = parser.parse(new FileReader("coordinates.json"));
         	JSONArray jsonArray = (JSONArray) obj;
         	int jsonArraySize=jsonArray.size();
         	Coordinate[] coorArr=new Coordinate[26];
         	
         	for(int i=0;i<jsonArraySize;i++){
         		JSONObject jobj=(JSONObject) jsonArray.get(i);
         		char id=jobj.get("id").toString().charAt(0);
         		String value=jobj.get("value").toString();
         		Coordinate cObj=new Coordinate(id,value);

         		cObj.setDistance(getDist(cObj.getValue(),x,y));
  
         		coorArr[i]=cObj;
         		
         	} 

     		sort(coorArr, 0, jsonArraySize-1);

     		return getJason(coorArr);

         }catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         } catch (ParseException e) {
             e.printStackTrace();
         }
         
         return null;
    }
    
    /**
     * Method that parses the array of coordinates to Jason
     * @param coorArr
     * @return  json object
     */
    public String getJason(Coordinate[] coorArr){
 	    GsonBuilder gsonBuilder = new GsonBuilder();
	      Gson gson = gsonBuilder.create();
	     return gson.toJson(coorArr);
	
    }
    
    /**
     * Method gets the distance of the coordinate to the origin 
     * @param iniVal: coordinates of one point
     * @param x: x coordinate of new origin
     * @param y: y coordinate of new origin
     * @return distance to the origin
     */
    public double getDist(String iniVal, int x, int y){
    	double dist;
    	String[] values =  iniVal.split(",");
    	dist=Math.sqrt(Math.pow(Integer.parseInt(values[0])-x,2) + Math.pow(Integer.parseInt(values[1])-y,2));
    	return dist;

    }
    
    /**
     * Method that sorts the array of coordinates by distance to new origin
     * @param coorArr: array of coordinates
     * @param start: starting point of the array
     * @param end: end point of the array
     */
    public void sort(Coordinate[] coorArr, int start, int end){
    	int i=start;
    	int j=end;
    	double pivot=coorArr[start+(end-start)/2].getDistance();
    	
    	while(i<=j){
    		while(coorArr[i].getDistance()<pivot){
    			i++;
    		}
    		while(coorArr[j].getDistance()>pivot){
    			j--;
    		}
    		
            if (i <= j) {
    			Coordinate temp =coorArr[i];
    			coorArr[i]=coorArr[j];
    			coorArr[j]=temp;
        		i++;
        		j--;
    		}
    		
    	}
    	
    	if(start<j){
    		sort(coorArr,start,j);
    	}
    	if(i<end){
    		sort(coorArr,i,end);

    	}
    	
    }
}

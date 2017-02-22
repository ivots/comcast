package comcast.developer.assigment.comcastassigment;

import static org.junit.Assert.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import comcast.developer.assigment.*;;

//import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
	
	private App app;
    public AppTest()
    {
    }
    @Before
    public void setUp()
    {
    	 app = new App();
    }

    @After
    public void tearDown()
    {
    }
    /**
     * @return the suite of tests being tested
     */

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    /*
     * This will test if the first element of the new array is equal as the provided coordinates
     */
    @Test
    public void testFirstCoordinate()
    { 

    	Collection myCollection= (Collection) new Object();
    	
 		JSONParser parser = new JSONParser(); 
 		String result="";
 		JSONArray array=null;
 		try {
 			array = (JSONArray)parser.parse(app.findCoordinates(31, 49));
 			result=array.get(0).toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}
 		
    	assertEquals("{\"id\":\"a\",\"value\":\"31,49\"}", result);
    }

    /*
     * This will test the distance calculator
     */
    @Test
    public void testDistance()
    { 
    	DecimalFormat df2 = new DecimalFormat(".####");
    	String expected="23.8537";
    	assertEquals(expected, df2.format(app.getDist("10,20", 30,33)));
    }
}

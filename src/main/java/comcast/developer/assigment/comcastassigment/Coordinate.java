package comcast.developer.assigment.comcastassigment;

public class Coordinate {
	
private char id;
private String value;
private transient double distance;

public Coordinate(){
	
}

public Coordinate(char id,String value){
	this.id=id;
	this.value=value;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
public char getId() {
	return id;
}
public void setId(char id) {
	this.id = id;
}
public void setDistance(double distance){
	this.distance=distance;
}
public double getDistance() {
	return distance;
}

}

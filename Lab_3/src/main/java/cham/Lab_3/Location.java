package cham.Lab_3;

/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }
    
    public boolean equals(Location location) {
    	if(location.xCoord == this.xCoord && location.yCoord == this.yCoord)
    		return true;
    	
    	return false;
    }
    
    public int hashCode() {
    	int hash_code = Integer.toString(xCoord).hashCode() * Integer.toString(yCoord).hashCode();
    	return hash_code;
    }
}

package cham.Lab_2;

public class Point3d {

	private double X;
	private double Y;
	private double Z;
	
	public Point3d() {
		this(0,0,0);
	}
	
	public Point3d(double X, double Y, double Z) {
		this.X = X;
		this.Y = Y;
		this.Z = Z;
	}
	
	public void setX(double value) {
		X = value;
	}
	
	public void setY(double value) {
		Y = value;
	}

	public void setZ(double value) {
		Z = value;
	}
	
	public double getX() {
		return X;
	}
	
	public double getY() {
		return Y;
	}

	public double getZ() {
		return Z;
	}
	
	public boolean comparison(Point3d point) {
		
		if((this.getX() == point.getX()) && (this.getY() == point.getY()) && 
				(this.getZ() == point.getZ()))
			return true;
		
		return false;
	}
	
	public double distanceTo(Point3d point) {
		double distance_X = this.getX() - point.getX();
		double distance_Y = this.getY() - point.getY();
		double distance_Z = this.getZ() - point.getZ();
		double distance = 0;
		
		distance = Math.sqrt(distance_X * distance_X + distance_Y * distance_Y);
		distance = Math.sqrt(distance * distance + distance_Z * distance_Z);
		
		return distance;
	}
}

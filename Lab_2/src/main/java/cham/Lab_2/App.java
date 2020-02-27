package cham.Lab_2;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Point3d points[] = new Point3d[3];
    	Scanner scn = new Scanner(System.in);
    	for(int i = 0; i < 3; i++) {
    		System.out.print("Введите значение для " + (i + 1) + " точки: ");
    		double X = scn.nextDouble();
    		double Y = scn.nextDouble();
    		double Z = scn.nextDouble();
    		
    		points[i] = new Point3d(X, Y, Z); 
    	}
    	
    	System.out.println("Вычисление площади треугольника");
    	computeArea(points[0], points[1], points[2]);
        
    }
    
    private static void computeArea(Point3d point_1, Point3d point_2, Point3d point_3) {
    	if(point_1.comparison(point_2) || point_1.comparison(point_3) || point_2.comparison(point_3)) {
    		System.out.println("Одна или несколько точек совпадают, это не является треугольником");
    		return;
    	}
    	
    	double length_a = point_1.distanceTo(point_2);
    	double length_b = point_2.distanceTo(point_3);
    	double length_c = point_3.distanceTo(point_1);
    	
    	double semi_perimeter = (length_a + length_b + length_c) / 2;
    	
    	double area = Math.sqrt(semi_perimeter * (semi_perimeter - length_a) * (semi_perimeter - length_b)
    			* (semi_perimeter - length_c));
    	
    	System.out.println("Площадь треугольника равна: " + area);
    }
}

package Utilities;

public class Angle {
	
	public static double getAngle(double x1,double y1,double x2,double y2){
		return Math.atan2(y2 - y1, x2 - x1);
	}

}

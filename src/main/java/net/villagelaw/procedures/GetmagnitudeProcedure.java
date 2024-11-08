package net.villagelaw.procedures;

public class GetmagnitudeProcedure {
	public static double execute(double x1, double x2, double y1, double y2, double z1, double z2) {
		double errorSight = 0;
		String pCrime = "";
		boolean isNoticed = false;
		return Math.abs(Math.abs(x1 + y1 + z1) - Math.abs(x2 + y2 + z2));
	}
}

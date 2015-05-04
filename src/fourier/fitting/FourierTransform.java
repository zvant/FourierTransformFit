package fourier.fitting;

import java.util.ArrayList;

public class FourierTransform
{
	private static int MAX = 11;
	private double[] coefficient = new double[MAX];
	private int up_limit;
	ArrayList<Point> sample_points;
	
	public FourierTransform()
	{
		up_limit = -1;
		for(double coeff : coefficient)
			coeff = 0.0;
	}
	
	public boolean setPoints(ArrayList<Point> sample)
	{
		if(sample != null)
		{
			sample_points = sample;
			return true;
		}
		else
		{
			sample_points = new ArrayList<Point>();
			return false;
		}
	}
	
	public boolean setOrder(int up_limit)
	{
		if(up_limit <= MAX)
		{
			this.up_limit = up_limit;
			return true;
		}
		else
			return false;
	}
	
	public double[] getFitting()
	{
		///////////////////////////////
		// The fitting algorithm //////
		///////////////////////////////
		coefficient[1] = 0.25;
		coefficient[0] = 0.5; // just for tests
		return coefficient;
	}
}

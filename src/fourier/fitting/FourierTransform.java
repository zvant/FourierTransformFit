package fourier.fitting;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class FourierTransform
{
	private static int MAX = 11;
	private double[] coefficients = new double[MAX];
	private int up_limit;
	ArrayList<Point2D> sample_points;
	
	public FourierTransform()
	{
		up_limit = -1;
		for(int i = 0; i < MAX; i ++)
			coefficients[i] = 0.0;
	}
	
	public boolean setPoints(ArrayList sample)
	{
		if(sample != null)
		{
			sample_points = sample;
			return true;
		}
		else
		{
			sample_points = new ArrayList<Point2D>();
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
		coefficients[1] = 0.25;
		coefficients[0] = 0.5; // just for tests
		return coefficients;
	}
}

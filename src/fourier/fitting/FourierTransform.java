package fourier.fitting;

import fourier.fitting.Complex;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * 
 * @author Zekun Zhang
 */
public class FourierTransform
{
	//private static int MAX = 11;
	//private double[] coefficients = new double[MAX];
	//private int up_limit;
	private ArrayList<Complex> sample_points;
	private ArrayList<Complex> coefficients;
	private int N;
	
	public FourierTransform()
	{
		N = 0;
		sample_points = new ArrayList<Complex>();
		coefficients = new ArrayList<Complex>();
	}
	
	// add a sample complex
	public void addSample(Complex z)
	{
		sample_points.add(z);
		N ++;
	}
	
	// do the Discrete Fourier Transformation (DFT):
	public void transform()
	{
		Complex I = new Complex(0.0, 1.0);
		for(int n = 0; n < N; n ++)
		{
			Complex zn = new Complex(0.0, 0.0);

			for(int k = 0; k < N; k ++)
			{
				Complex zk = sample_points.get(k);
				zn = zn.plus(zk.times((Complex.times(I, k * n * 2 * Math.PI / N)).exp()));
			}
			
			coefficients.add(zn.divides(N));
		}
	}
	
	// display the sample and result to stdout:
	public void showTransform()
	{
		System.out.println("DFT for N = " + N);
		System.out.println("Original Points:");
		for(Complex zn : sample_points)
			System.out.print(zn.toString() + ", ");
		System.out.println("\n");
		
		System.out.println("Transformed Points:");
		for(Complex zk : coefficients)
			System.out.print(zk.toString() + ", ");
		System.out.println("\n");
	}
	
	public ArrayList<Complex> getSamples()
	{
		return sample_points;
	}
	
	public ArrayList<Complex> getCoeffs()
	{
		return coefficients;
	}
	
	public int getN()
	{
		return N;
	}
}

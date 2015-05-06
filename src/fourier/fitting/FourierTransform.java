package fourier.fitting;

import fourier.fitting.Complex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 
 * @author Zekun Zhang
 */
public class FourierTransform
{
	private ArrayList<Complex> sample_points;
	Complex center;
	private ArrayList<Complex> coefficients;
	private int N;
	
    /**
     *
     */
    public FourierTransform()
	{
		N = 0;
		center = new Complex(0.0, 0.0);
		sample_points = new ArrayList<Complex>();
		coefficients = new ArrayList<Complex>();
	}
	
	// add a sample complex

    /**
     *
     * @param z
     */
    public void addSample(Complex z)
	{
		sample_points.add(z);
		N ++;
	}
	
	// do the Discrete Fourier Transformation (DFT):

    /**
     *
     */
    public void transform()
	{
    	reOrder();
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
    
    // re-order the sample points to get better performance
    
    /**
     * 
     */
    public void reOrder()
    {
    	//ArrayList<Complex> tmp = new ArrayList<Complex>();

    	for(Complex zk : sample_points)
    	{
    		center = Complex.plus(center, zk);
    	}
    	center = center.divides(N);
    	
    	Collections.sort(sample_points, new PhaseComparator());
    }
    
    private class PhaseComparator implements Comparator<Complex>
	{
		public int compare(Complex z1, Complex z2)
		{
			double diff = Complex.minus(z1, center).phase() - Complex.minus(z2, center).phase();
			
			return (int)(diff * 2000);
		}
	}
	
	// display the sample and result to stdout:

    /**
     *
     */
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
	
    /**
     *
     * @return
     */
    public ArrayList<Complex> getSamples() { return sample_points; }
	
    /**
     *
     * @return
     */
    public ArrayList<Complex> getCoeffs() { return coefficients; }
	
    /**
     *
     * @return
     */
    public int getN() { return N; }
}

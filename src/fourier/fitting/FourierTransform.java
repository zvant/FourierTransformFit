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
     * Initiate a transformation
     */
    public FourierTransform()
	{
		N = 0;
		center = new Complex(0.0, 0.0);
		sample_points = new ArrayList<Complex>();
		coefficients = new ArrayList<Complex>();
	}
	

    /**
     * Add a new sample to the transformation
     * @param z a new sample point
     */
    public void addSample(Complex z)
	{
		sample_points.add(z);
		N ++;
	}
	

    /**
     * transformation that generates coefficients
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
    
    private class PhaseComparator implements Comparator<Complex>
	{
		public int compare(Complex z1, Complex z2)
		{
			double diff = Complex.minus(z1, center).phase() - Complex.minus(z2, center).phase();
			
			return (int)(diff * 2000);
		}
	}

    /**
     * display the sample and result to stdout
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
     * @return ArrayList of sample complex numbers
     */
    public ArrayList<Complex> getSamples() { return sample_points; }
	
    /**
     *
     * @return ArrayList of coefficient complex numbers
     */
    public ArrayList<Complex> getCoeffs() { return coefficients; }
	
    /**
     *
     * @return number of samples as well as coefficients
     */
    public int getN() { return N; }
}

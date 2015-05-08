package fourier.fitting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Discrete Fourier Transformation
 * @author Zekun Zhang
 */
public class FourierTransform {

    private ArrayList<Complex> sample_points;
    private ArrayList<Complex> coefficients;
    private int N;

    /**
     * Initiate a transformation
     */
    public FourierTransform() {
        N = 0;
        sample_points = new ArrayList<>();
        coefficients = new ArrayList<>();
    }

    /**
     * Add a new sample to the transformation
     *
     * @param z a new sample point
     */
    public void addSample(Complex z) {
        sample_points.add(z);
        N++;
    }
    
    /**
     * save data, including the samples and coefficients, to a data file
     * @param path path of the data file
     * @return true if no exception is thrown
     */
    public boolean saveData(String path)
    {
    	ArrayList< ArrayList<Complex> > data = new ArrayList< ArrayList<Complex> >();
    	data.add(sample_points);
    	data.add(coefficients);
    	
    	try
    	{
    		ObjectOutputStream out_stream = new ObjectOutputStream(new FileOutputStream(new File(path)));
    		out_stream.writeObject(data);
    		out_stream.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return false;
    	}
    	return true;
    }
    
    /**
     * load data, including the samples and coefficients, from a data file
     * @param path path of the data file
     * @return true if no exception is thrown
     */
    @SuppressWarnings("unchecked")
	public boolean loadData(String path)
    {
    	ArrayList< ArrayList<Complex> > data = new ArrayList< ArrayList<Complex> >();
    	
    	try
    	{
    		ObjectInputStream in_stream = new ObjectInputStream(new FileInputStream(new File(path)));
    		data = (ArrayList< ArrayList<Complex> >)(in_stream.readObject());
    		in_stream.close();
    		sample_points = data.get(0);
    		coefficients = data.get(1);
		}
    	catch (Exception e)
    	{
			e.printStackTrace();
			return false;
		}
    	return true;
    }

    /**
     * transformation that generates coefficients
     */
    public void transform() {
        Complex I = new Complex(0.0, 1.0);
        for (int n = 0; n < N; n++) {
            Complex zn = new Complex(0.0, 0.0);

            for (int k = 0; k < N; k++) {
                Complex zk = sample_points.get(k);
                zn = zn.plus(zk.times((Complex.times(I, k * n * 2 * Math.PI / N)).exp()));
            }

            coefficients.add(zn.divides(N));
        }
    }
    
    /**
     * display the sample and result to stdout
     */
    public void showTransform() {
        System.out.println("DFT for N = " + N);

        System.out.println("Original Points:");
        for (Complex zn : sample_points) {
            System.out.print(zn.toString() + ", ");
        }
        System.out.println("\n");

        System.out.println("Transformed Points:");
        for (Complex zk : coefficients) {
            System.out.print(zk.toString() + ", ");
        }
        System.out.println("\n");
    }

    /**
     *
     * @return ArrayList of sample complex numbers
     */
    public ArrayList<Complex> getSamples() {
        return sample_points;
    }

    /**
     *
     * @return ArrayList of coefficient complex numbers
     */
    public ArrayList<Complex> getCoeffs() {
        return coefficients;
    }

    /**
     *
     * @return number of samples as well as coefficients
     */
    public int getN() {
        return N;
    }
}

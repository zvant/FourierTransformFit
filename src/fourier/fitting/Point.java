package fourier.fitting;

public class Point
{
	private double x;
	private double y;
	
	public Point()
	{
		x = 0.0;
		y = 0.0;
	}
	
	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}
	
	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}

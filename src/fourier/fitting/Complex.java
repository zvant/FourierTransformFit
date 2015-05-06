package fourier.fitting;

import java.awt.geom.Point2D;
import java.lang.Math;

/**
 * Complex number class
 * @author caoyuan9642
 */
public class Complex {

    private final double re; // the real part
    private final double im; // the imaginary part

    /**
     *
     */
    public static final double EPS = 1E-5; // values less than epsilon would be treated as zero when printed

    /**
     *
     * @param real real part
     * @param imag imaginary part
     */
    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    /**
     *
     * @param z
     */
    public Complex(Complex z) {
        re = z.re();
        im = z.im();
    }

    /**
     *
     * @return a description string
     */
    @Override
    public String toString() {

        if (Math.abs(im) < EPS && Math.abs(re) < EPS) {
            return "0.0";
        }
        if (Math.abs(im) < EPS) {
            return re + "";
        }
        if (Math.abs(re) < EPS) {
            return im + "i";
        }
        if (im < 0) {
            return re + " - " + (-im) + "i";
        }

        return re + " + " + im + "i";
    }

    /**
     *
     * @return absolute value
     */
    public double abs() {
        return Math.hypot(re, im);
    }

    /**
     *
     * @return Arg(this), between -PI and PI
     */
    public double phase() {
        return Math.atan2(im, re);
    }


    /**
     *
     * @param b complex
     * @return this + b
     */
        public Complex plus(Complex b) {
        return new Complex(this.re + b.re, this.im + b.im);
    }

    /**
     *
     * @param a complex
     * @param b complex
     * @return a + b
     */
    public static Complex plus(Complex a, Complex b) {
        return a.plus(b);
    }

    /**
     *
     * @param b complex
     * @return this - b
     */
        public Complex minus(Complex b) {
        return new Complex(this.re - b.re, this.im - b.im);
    }

    /**
     *
     * @param a complex
     * @param b complex
     * @return a - b
     */
    public static Complex minus(Complex a, Complex b) {
        return a.minus(b);
    }

    /**
     *
     * @param b complex
     * @return this * b
     */
        public Complex times(Complex b) {
        return new Complex(
                this.re * b.re - this.im * b.im,
                this.re * b.im + this.im * b.re
        );
    }

    /**
     *
     * @param a complex
     * @param b complex
     * @return a * b
     */
    public static Complex times(Complex a, Complex b) {
        return a.times(b);
    }

    /**
     *
     * @param alpha real
     * @return this * alpha
     */
    public Complex times(double alpha) {
        return new Complex(this.re * alpha, this.im * alpha);
    }

    /**
     *
     * @param z complex
     * @param alpha real
     * @return z * alpha
     */
    public static Complex times(Complex z, double alpha) {
        return z.times(alpha);
    }

    /**
     *
     * @param alpha real
     * @param z complex
     * @return z * alpha
     */
    public static Complex times(double alpha, Complex z) {
        return z.times(alpha);
    }

    /**
     *
     * @param b complex
     * @return this / b
     */
        public Complex divides(Complex b) {
        return this.times(b.reciprocal());
    }

    /**
     *
     * @param a complex
     * @param b complex
     * @return a / b
     */
    public static Complex divides(Complex a, Complex b) {
        return a.times(b.reciprocal());
    }

    /**
     *
     * @param alpha real
     * @return this / alpha
     */
    public Complex divides(double alpha) {
        return new Complex(re / alpha, im / alpha);
    }

    /**
     *
     * @param z complex
     * @param alpha real
     * @return z / alpha
     */
    public static Complex divides(Complex z, double alpha) {
        return new Complex(z.re / alpha, z.im / alpha);
    }

    /**
     *
     * @param alpha real
     * @param z complex
     * @return alpha / z
     */
    public static Complex divides(double alpha, Complex z) {
        return new Complex(alpha, 0.0).divides(z);
    }

    /**
     *
     * @return conjugation complex number
     */
        public Complex conjugate() {
        return new Complex(re, -im);
    }

    /**
     *
     * @return complex reciprocal
     */
        public Complex reciprocal() {
        double scale = re * re + im * im;
        return new Complex(re / scale, -im / scale);
    }

    /**
     *
     * @return real part
     */
    public double re() {
        return re;
    }

    /**
     *
     * @return imaginary part
     */
    public double im() {
        return im;
    }
    
    /**
     *
     * @return complex exponential
     */
        public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    /**
     *
     * @param z
     * @return complex exponential
     */
    public static Complex exp(Complex z) {
        return z.exp();
    }

    /**
     *
     * @return complex sine
     */
        public Complex sin() {
        return new Complex(Math.sin(re) * Math.cosh(im), Math.cos(re) * Math.sinh(im));
    }

    /**
     *
     * @param z
     * @return complex sine
     */
    public static Complex sin(Complex z) {
        return z.sin();
    }

    /**
     *
     * @return complex cosine
     */
        public Complex cos() {
        return new Complex(Math.cos(re) * Math.cosh(im), -Math.sin(re) * Math.sinh(im));
    }

    /**
     *
     * @param z
     * @return complex cosine
     */
    public static Complex cos(Complex z) {
        return z.cos();
    }

    /**
     *
     * @return complex tangent
     */
        public Complex tan() {
        return sin().divides(cos());
    }

    /**
     *
     * @param z
     * @return complex tangent
     */
    public static Complex tan(Complex z) {
        return z.tan();
    }

    /**
     *
     * @return <i>java.awt.geom.Point2D</i> Point
     */
    public Point2D getPoint() {
        return new Point2D.Double(re, im);
    }

    /**
     *
     * @param r modulus
     * @param phi argument
     * @return
     */
    public static Complex fromPolar(double r, double phi) {
        return new Complex(r * Math.cos(phi), r * Math.sin(phi));
    }

}

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
     * @param real
     * @param imag
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
     * @return
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
     * @return
     */
    public double abs() {
        return Math.hypot(re, im);
    } // Math.sqrt(re*re + im*im)

    /**
     *
     * @return
     */
    public double phase() {
        return Math.atan2(im, re);
    } // between -pi and pi

    // plus methods:

    /**
     *
     * @param b
     * @return
     */
        public Complex plus(Complex b) {
        return new Complex(this.re + b.re, this.im + b.im);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Complex plus(Complex a, Complex b) {
        return a.plus(b);
    }

    // minus methods:

    /**
     *
     * @param b
     * @return
     */
        public Complex minus(Complex b) {
        return new Complex(this.re - b.re, this.im - b.im);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Complex minus(Complex a, Complex b) {
        return a.minus(b);
    }

    // times methods:

    /**
     *
     * @param b
     * @return
     */
        public Complex times(Complex b) {
        return new Complex(
                this.re * b.re - this.im * b.im,
                this.re * b.im + this.im * b.re
        );
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Complex times(Complex a, Complex b) {
        return a.times(b);
    }

    /**
     *
     * @param alpha
     * @return
     */
    public Complex times(double alpha) {
        return new Complex(this.re * alpha, this.im * alpha);
    }

    /**
     *
     * @param z
     * @param alpha
     * @return
     */
    public static Complex times(Complex z, double alpha) {
        return z.times(alpha);
    }

    /**
     *
     * @param alpha
     * @param z
     * @return
     */
    public static Complex times(Double alpha, Complex z) {
        return z.times(alpha);
    }

    // divides methods:

    /**
     *
     * @param b
     * @return
     */
        public Complex divides(Complex b) {
        return this.times(b.reciprocal());
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Complex divides(Complex a, Complex b) {
        return a.times(b.reciprocal());
    }

    /**
     *
     * @param alpha
     * @return
     */
    public Complex divides(double alpha) {
        return new Complex(re / alpha, im / alpha);
    }

    /**
     *
     * @param z
     * @param alpha
     * @return
     */
    public static Complex divides(Complex z, double alpha) {
        return new Complex(z.re / alpha, z.im / alpha);
    }

    /**
     *
     * @param alpha
     * @param z
     * @return
     */
    public static Complex divides(Double alpha, Complex z) {
        return new Complex(alpha, 0.0).divides(z);
    }

    // conjugate:

    /**
     *
     * @return
     */
        public Complex conjugate() {
        return new Complex(re, -im);
    }

    // reciprocal:

    /**
     *
     * @return
     */
        public Complex reciprocal() {
        double scale = re * re + im * im;
        return new Complex(re / scale, -im / scale);
    }

    /**
     *
     * @return
     */
    public double re() {
        return re;
    }

    /**
     *
     * @return
     */
    public double im() {
        return im;
    }

    // complex exponential e^z:

    /**
     *
     * @return
     */
        public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    /**
     *
     * @param z
     * @return
     */
    public static Complex exp(Complex z) {
        return z.exp();
    }

    // complex sine:

    /**
     *
     * @return
     */
        public Complex sin() {
        return new Complex(Math.sin(re) * Math.cosh(im), Math.cos(re) * Math.sinh(im));
    }

    /**
     *
     * @param z
     * @return
     */
    public static Complex sin(Complex z) {
        return z.sin();
    }

    // complex cosine:

    /**
     *
     * @return
     */
        public Complex cos() {
        return new Complex(Math.cos(re) * Math.cosh(im), -Math.sin(re) * Math.sinh(im));
    }

    /**
     *
     * @param z
     * @return
     */
    public static Complex cos(Complex z) {
        return z.cos();
    }

    // complex tangent:

    /**
     *
     * @return
     */
        public Complex tan() {
        return sin().divides(cos());
    }

    /**
     *
     * @param z
     * @return
     */
    public static Complex tan(Complex z) {
        return z.tan();
    }

    /**
     *
     * @return
     */
    public Point2D getPoint() {
        return new Point2D.Double(re, im);
    }

    /**
     *
     * @param r
     * @param phi
     * @return
     */
    public static Complex fromPolar(double r, double phi) {
        return new Complex(r * Math.cos(phi), r * Math.sin(phi));
    }

}

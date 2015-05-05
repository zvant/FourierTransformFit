package fourier.fitting;

import java.awt.geom.Point2D;
import java.lang.Math;

public class Complex {

    private final double re; // the real part
    private final double im; // the imaginary part
    public static final double EPS = 1E-5; // values less than epsilon would be treated as zero when printed

    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    public Complex(Complex z) {
        re = z.re();
        im = z.im();
    }

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

    public double abs() {
        return Math.hypot(re, im);
    } // Math.sqrt(re*re + im*im)

    public double phase() {
        return Math.atan2(im, re);
    } // between -pi and pi

    // plus methods:
    public Complex plus(Complex b) {
        return new Complex(this.re + b.re, this.im + b.im);
    }

    public static Complex plus(Complex a, Complex b) {
        return a.plus(b);
    }

    // minus methods:
    public Complex minus(Complex b) {
        return new Complex(this.re - b.re, this.im - b.im);
    }

    public static Complex minus(Complex a, Complex b) {
        return a.minus(b);
    }

    // times methods:
    public Complex times(Complex b) {
        return new Complex(
                this.re * b.re - this.im * b.im,
                this.re * b.im + this.im * b.re
        );
    }

    public static Complex times(Complex a, Complex b) {
        return a.times(b);
    }

    public Complex times(double alpha) {
        return new Complex(this.re * alpha, this.im * alpha);
    }

    public static Complex times(Complex z, double alpha) {
        return z.times(alpha);
    }

    public static Complex times(Double alpha, Complex z) {
        return z.times(alpha);
    }

    // divides methods:
    public Complex divides(Complex b) {
        return this.times(b.reciprocal());
    }

    public static Complex divides(Complex a, Complex b) {
        return a.times(b.reciprocal());
    }

    public Complex divides(double alpha) {
        return new Complex(re / alpha, im / alpha);
    }

    public static Complex divides(Complex z, double alpha) {
        return new Complex(z.re / alpha, z.im / alpha);
    }

    public static Complex divides(Double alpha, Complex z) {
        return new Complex(alpha, 0.0).divides(z);
    }

    // conjugate:
    public Complex conjugate() {
        return new Complex(re, -im);
    }

    // reciprocal:
    public Complex reciprocal() {
        double scale = re * re + im * im;
        return new Complex(re / scale, -im / scale);
    }

    public double re() {
        return re;
    }

    public double im() {
        return im;
    }

    // complex exponential e^z:
    public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    public static Complex exp(Complex z) {
        return z.exp();
    }

    // complex sine:
    public Complex sin() {
        return new Complex(Math.sin(re) * Math.cosh(im), Math.cos(re) * Math.sinh(im));
    }

    public static Complex sin(Complex z) {
        return z.sin();
    }

    // complex cosine:
    public Complex cos() {
        return new Complex(Math.cos(re) * Math.cosh(im), -Math.sin(re) * Math.sinh(im));
    }

    public static Complex cos(Complex z) {
        return z.cos();
    }

    // complex tangent:
    public Complex tan() {
        return sin().divides(cos());
    }

    public static Complex tan(Complex z) {
        return z.tan();
    }

    public Point2D getPoint() {
        return new Point2D.Double(re, im);
    }

    public static Complex fromPolar(double r, double phi) {
        return new Complex(r * Math.cos(phi), r * Math.sin(phi));
    }

}

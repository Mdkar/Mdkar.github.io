
public class ComplexNumber {
	private double a,b;
	public ComplexNumber(double a, double b) {
		this.a = a;
		this.b = b;
	}
	public double mag() {
		return(Math.sqrt(a*a+b*b));
	}
	public ComplexNumber times(ComplexNumber z) {
		return(new ComplexNumber(this.a*z.a-this.b*z.b,this.a*z.b+this.b*z.a));
	}
	public ComplexNumber plus(ComplexNumber z) {
		return(new ComplexNumber(this.a+z.a, this.b+z.b));
	}
	
}

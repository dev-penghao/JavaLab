public class Main {
	
	public static void main(String[] args){
		new Main().main();
	}

	private void main(){
		Fraction f1=new Fraction(-1,9);
		Fraction f2=new Fraction(2,8);
		f1=f1.reciprocal(Fraction.ZERO);
		System.out.println(f1.getNumerator()+"   "+f1.getDenominator());
		
		//System.out.println("a="+a+"  b="+b);
	}
}

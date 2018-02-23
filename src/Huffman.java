import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Huffman {

    public static void main(String[] args) {
        long a=System.currentTimeMillis();
        new Huffman().main();
        long b=System.currentTimeMillis();
        System.out.println(b-a);
    }

    public void main() {
        //过程处理
        File file=new File("/storage/emulated/0/QQBrowser.zip");
        byte[] readed=new byte[1024*1024];
        long[] count=new long[256];
        if (!file.exists()) {
            System.err.println("文件不存在！");
            return;
        }
        try {
            FileInputStream fis=new FileInputStream(file);
            while(fis.read(readed)!=-1) {
                for(int i=0;i<readed.length;i++) {
                    count[readed[i]&0x0ff]++;
                }
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //显示结果
        long total=file.length();
        double probability;
        double sum=0;
        for(int i=0;i<count.length;i++) {
            probability=((double)count[i])/total;
            sum=sum+probability;
            System.out.println(i);
            System.out.println(count[i]+" 的概率是"+probability);
            System.out.println("-------------------------------------------------------------------------");
        }
        System.out.println("和是"+sum);
        System.out.println("最小值是："+Min(count));
        System.out.println("最大值是："+Max(count));

    }

	//返回由正数组成的数组中最大的数
    public static long Max(long[] longs){
        long max=0;
        for (long l:longs){
            if (l>max)
                max=l;
        }
        return max;
    }

    public static long Min(long[] longs){
        long min=Max(longs);
        for (long l:longs){
            if (l<min)
                min=l;
        }
        return min;
    }
}

class Fraction{

    private long numerator;//分子
    private long denominator;//分母
	public static final Fraction ONE=new Fraction(1,1);
	public static final Fraction ZERO=new Fraction(0,1);

    public Fraction(int numerator,int denominator) throws ArithmeticException{
        this.numerator=numerator;
		if(denominator==0){
			throw new ArithmeticException("The denominator is ZERO!");
		}
        this.denominator=denominator;
    }

    public Fraction(long numerator,long denominator) throws ArithmeticException{
        this.numerator=numerator;
		if(denominator==0){
			throw new ArithmeticException("The denominator is ZERO!");
		}
        this.denominator=denominator;
    }

	public void setNumerator(long numerator)
	{
		this.numerator = numerator;
	}

	public long getNumerator()
	{
		return numerator;
	}

	public void setDenominator(long denominator)
	{
		this.denominator = denominator;
	}

	public long getDenominator()
	{
		return denominator;
	}
	
	//分数加法
	public Fraction plus(Fraction fraction){
		long numerator=fraction.getNumerator();
		long denominator=fraction.getDenominator();
		long temNum,temDen;
		temNum=this.numerator*denominator;
		temDen=this.denominator*denominator;
		numerator=numerator*this.denominator;
		denominator=denominator*this.denominator;
		long newNum=numerator+temNum;
		long newDen=denominator;
		System.out.println(newNum+"/"+newDen);
		long gcd=getGcd(newNum,newDen);
		newNum=newNum/gcd;
		newDen=newDen/gcd;
		return new Fraction(newNum,newDen);
	}
	
	//分数乘法
	public Fraction multiply(Fraction fraction){
		long newNum=numerator*fraction.getNumerator();
		long newDen=denominator*fraction.getDenominator();
		long gcd=getGcd(newNum,newDen);
		newNum=newNum/gcd;
		newDen=newDen/gcd;
		return new Fraction(newNum,newDen);
	}
	
	//求倒数运算
	public Fraction reciprocal(Fraction fraction){
		long newNum=fraction.getNumerator();
		long newDen=fraction.getDenominator();
		long tem=newNum;
		newNum=newDen;
		newDen=tem;
		return new Fraction(newNum,newDen);
	}
	
	//比较两分数的大小，a>b返回1，a=b返回0，a<b返回-1
	public short cmp(Fraction a,Fraction b){
		long aNum=a.getNumerator(),aDen=a.getDenominator(),
			bNun=b.getNumerator(),bDen=b.getDenominator();
		return 0;
	}
	
	//辗转相除法求两正数的最大公约数
	private long getGcd(long a,long b){
		//先把a,b比大小，大的赋值给m，小的给n;如果a与b相等的话那么m与n的值也会相等.r是每次除法后的余数
		long m,n,r;
		m=a>=b?a:b;
		n=a>=b?b:a;
		//循环进行辗转相除
		while((r=m%n)!=0){
			m=n;n=r;
		}
		return n;
	}
	
	private long getLcm(long a,long b){
		return (a*b)/getGcd(a,b);
	}
}

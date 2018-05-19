/**
*	Created by Penghao on 2018-2-22.
*	该文件中包含许多算法的实现，这些算法都是Penghao在learn codeing中遇到的问题,
*为了以后复用并作为纪念而汇总于此
*/
public class Algorithm {

	public static void main(String[] args) {
		new Algorithm().main();
	}
	
	public void main(){
		int[] test={5,98,45,3,6,5,98,123,3};//要被排序的数
		sort(test);
	}
	
	//冒泡法对数组从小到大排序
	//Created by Penghao
	public int[] sort(int[] test){
		int i=0,tem;
		while(true) {
			if(test[i]>test[i+1]) {
				tem=test[i];
				test[i]=test[i+1];
				test[i+1]=tem;
				i=0;
			}else {
				i++;
				if(i+1==test.length)break;
			}
		}
		//输出排序结果
		for(int b:test){
			System.out.println(b);
		}
		return test;
	}
	
	//辗转相除法求两正数的最大公约数
	//Created by Penghao on 2018-2-22
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
}


public class Project_1 {

	public static void main(String[] args) {
		int[] a= {9,8,7,6,4,2,1,89,78,62,54,45,552,5};
		int i=0;
		int index=a.length;
		while(true) {
			if(a[i]>a[i+1]) {
				int tem;
				tem=a[i];
				a[i]=a[i+1];
				a[i+1]=tem;
				
				i=0;
			}else {
				i++;
				if(i+1==index) {
					break;
				}
			}
		}
	}
}
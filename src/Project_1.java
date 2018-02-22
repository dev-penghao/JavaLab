
public class Project_1 {

	public static void main(String[] args) {
		int[] test= {9,8,7,6,4,2,1,89,78,62,54,45,552,5};
		int i=0;
		int index=test.length;
		while(true) {
			if(test[i]>test[i+1]) {
				int tem;
				tem=test[i];
				test[i]=test[i+1];
				test[i+1]=tem;
				
				i=0;
			}else {
				i++;
				if(i+1==index) {
					break;
				}
			}
		}
		for(int b:test){
			System.out.println(b);
		}
	}
}

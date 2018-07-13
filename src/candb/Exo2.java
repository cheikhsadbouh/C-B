package candb;

public class Exo2 {

	
	
	public  static int getvawels(String data){
		int  count_vowels=0;
		String [] check_if_exist={"0","A","0","E","0","I","0","O","0","U","0","Y"};
		char substring;
		
		for(int i=0 ;i<data.length();i++){
			substring = data.charAt(i);
			
			switch (substring) {
			case 'A':{
				
				count_vowels++;
				check_if_exist[0]="1";
				break;
			}
			case 'E':{
				
				count_vowels++;
				check_if_exist[2]="1";
				break;
			}
			case 'I':{
				
				count_vowels++;
				check_if_exist[4]="1";
				break;
			}
			case 'O':{
				
				count_vowels++;
				check_if_exist[6]="1";
				break;
			}
			case 'U':{
				
				count_vowels++;
				check_if_exist[8]="1";
				break;
			}
			case 'Y':{
				
				count_vowels++;
				check_if_exist[10]="1";
				break;
			}
			default :{}
			
			}
			
		}
		System.out.print("voyelles absentes :   ");
		for(int i=0 ;i<check_if_exist.length;i++){
			
			
			if(check_if_exist[i]=="0"){
			
				System.out.print(check_if_exist[i+1]);
			
		}
		}

	
		return count_vowels;
	}
	public static void main(String[] args) {
		///begin exo3
	int d=	getvawels("CHEIKH");
	System.out.println();
	System.out.print("return vowels :"+d);
	System.out.println();
	
	///////end exo3
	
		int t1[]={5,10,7,6,-5,0,2};
		for(int i=0;i<6;i++){
			for(int j=i+1;j<7;j++){
				
				if(t1[i]>t1[j]){
					System.out.println("("+i+"->"+j+")");
					
					t1[i]=t1[i]+t1[j];
					t1[j]=t1[i]-t1[j];
					t1[i]=t1[i]-t1[j];
					
					
				}
				
			}
			
		}
		
		try{
			
			for(int k=0;k<=t1.length;k++){
				System.out.print(""+t1[k]+"");
			}
		}catch(Exception e ){
			System.out.print("*****");
		}
		
		
		
		
		
	}

}

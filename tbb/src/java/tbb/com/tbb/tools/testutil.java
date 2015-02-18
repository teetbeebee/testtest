package com.tbb.tools;

public class testutil {
	public static void main(String[] args) {
//		System.out.println(System.getProperty("java.class.path"));
//		System.out.println(Math.random());
//		
//		for(int i = 1;i < 11;i++){
//			if(Math.random() > 0.5){
//				System.out.println("help" + i + ":ok");
//			} else {
//				System.out.println("help" + i + ":no");
//			}
//		}
		
		double ds = 0.99;
		double zjl = 0.001;
		int back = 900; //返奖倍数
		int tz_yqr = 2000;
		int tz_ds = 20;
		long pool = 0;
		long total_tz = 0;
		
		for(int j = 0;j < 100;j++){
			for(int i = 1;i < 10000000; i++){
				int tz = 0;
				if(Math.random() > ds)
					tz = tz_yqr;
				else
					tz = tz_ds;
				
				total_tz += tz;
				pool += tz;
				
				if(Math.random() > (1 - zjl)){
					pool -=(tz * back); 
				}
			}
			
			//System.out.println("pool: " + pool);
			//System.out.println("total_tz: " + total_tz);
			//System.out.println("p:" + ((double)pool/total_tz) * 100 + "%");
			System.out.println(((double)pool/total_tz) * 100);
			
			pool = 0;
			total_tz = 0;
			
			//System.out.println("");
		}
		
	}
}

package multiThread;

public class MT04ClassLevelSyncronization {

	public static void main(String[] args) {
		
		
		Parantez1 p1 = new Parantez1();
		Parantez1 p2 = new Parantez1();
		
		Thread th1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				for (int i = 1; i <=5; i++) {
					p1.parantezKoy();	
				}
							
			}
		});
		
		Thread th2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				for (int i = 1; i <=5; i++) {
					p2.parantezKoy();	
				}
							
			}
		});
		
		th1.start();
		th2.start();
		
		
		 
	}
}
class Parantez1{
	synchronized static public void parantezKoy() {
		 for (int i = 1; i <=10; i++) {
			if (i<5) {
				System.out.print("[");

			} else {
				System.out.print("]");

			}
		}
		
		System.out.println();

		 
		
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
	}
	

}

	
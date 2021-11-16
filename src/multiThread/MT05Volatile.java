package multiThread;

public class MT05Volatile {

	
	volatile public static int yas=0;
	
	public static void main(String[] args) {
		Thread th1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					if (yas==0) {
						System.out.println("TH1 calisiyor....");
						
					} else {
						break;
					}
				}
				
			}
		});
		Thread th2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				yas=+1;
				System.out.println("Yas Guncellendi...");
			}
		});
		th1.start();
		th2.start();
	}
	
	
}

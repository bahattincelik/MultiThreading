package multiThread;

public class MT06WaitNotify {
	
	public static double bakiye = 0.0;
	
	public static void main(String[] args) {
		
		MT06WaitNotify islem = new MT06WaitNotify();
		
		
		Thread paraCekmeThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				islem.paraCekme(200);
			}
		});
		
		Thread paraYatirmaThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				islem.paraYatirma(500);
			}
		});	
		
	
		
		paraCekmeThread.start();
		paraYatirmaThread.start();
	}
	
	public void paraCekme(double miktar) {
		
		
		
		synchronized (this) {
			
		
		if(bakiye<0 || bakiye<miktar) {
			System.out.println("Bakiyeniz islem icin yetersiz. Lutfen para yatiriniz");
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		}
		bakiye=bakiye-miktar;
		System.out.println("Para cekildi, Bakiyeniz: "+bakiye);
		}
	}
	
	public void paraYatirma(double miktar) {
		bakiye=bakiye+miktar;
		System.out.println("Miktar yatirildi, Bakiyeniz :"+ bakiye);
		synchronized (this) {
			notify();
		}
		
	}
}

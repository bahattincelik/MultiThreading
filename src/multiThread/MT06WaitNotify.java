package multiThread;

/* ============================ WAIT, NOTIFY ==========================
Object.wait() metodu bir thread'i suresiz olarak askıya alir. Diğer bir ifade ile
bu thread'in kilitlemiş (locked) olduğu bir kaynağı salıvermesini ve askıya geçmesini sağlar.
Thread bu durumdan bir başka thread Onu bilgilendirirse (notify) çıkabilir.

Object.notify() metodu ise aynı nesne üzerinde askıya alınan bir thread'in uyanmasini saglar.
Object.notifyAll() metodu bir nesne üzerinde askıya alınan tum thread'lerin uyandirilmasini saglar.

Bu metotlar, thread'ler arasi iletişim metodu olarak kullanılır.

*/
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

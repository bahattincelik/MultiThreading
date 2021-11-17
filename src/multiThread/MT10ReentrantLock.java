package multiThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MT10ReentrantLock {
	
	public static void main(String[] args) {
		
		Lock lock = new ReentrantLock();
		
		BiletSatis islem = new BiletSatis(lock);
		
		Thread yolcu1 = new Thread(islem, "Yolcu-1");
		Thread yolcu2 = new Thread(islem, "Yolcu-2");
		Thread yolcu3 = new Thread(islem, "Yolcu-3");
		
		yolcu1.start();
		yolcu2.start();
		yolcu3.start();
	}

}


class BiletSatis implements Runnable{
	
	
	public int biletSayisi=2;	


	Lock lock;
	

	public BiletSatis(Lock lock) {
		
		this.lock = lock;
	}






	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+ " bileti satin almak icin bekliyor...");
	
		lock.lock();
		
		if (biletSayisi>0) {
			System.out.println(Thread.currentThread().getName()+" bileti almak uzer....");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		biletSayisi--;
		System.out.println(Thread.currentThread().getName()+" bileti satin aldi....");
	} else {
		System.out.println(Thread.currentThread().getName()+" bileti satin alamadi....");
	}
	
	lock.unlock();
	
	
	
	}
	
}
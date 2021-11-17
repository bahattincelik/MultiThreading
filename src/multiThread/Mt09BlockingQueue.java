package multiThread;
/*
ArrayBlockingQueue aslinda bir Collection'dır. Queue'nun bir kapasitesi bulunmaktadir ve bu kapasite bir kere
belirlendi mi runtime'da değiştirilemez.  Bu Queue'nun en büyük özelliği, queue doluyken yeni bir veri eklemeyi
ve queue bos iken veri cikarmayi engellemesidir. Queue'nun calismasi LIFO mantığına goredir.
*/

import java.util.concurrent.ArrayBlockingQueue;

public class Mt09BlockingQueue {
	
	
	static public int sayac =0;
	public static void main(String[] args) {
		ArrayBlockingQueue<Integer> kuyruk=new ArrayBlockingQueue<Integer>(10);
		
		Uretici uretici = new Uretici(kuyruk); 
		Thread ureticiThread = new Thread(uretici);
		
		

		Tuketici tuketici = new Tuketici(kuyruk); 
		Thread tuketiciThread = new Thread(tuketici);
		
		ureticiThread.start();
		tuketiciThread.start();
		
	}

}


class Uretici implements Runnable{

	private ArrayBlockingQueue<Integer> kuyruk;
	public Uretici(ArrayBlockingQueue<Integer> kuyruk) {
	
		this.kuyruk = kuyruk;
	}
	
	
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				kuyruk.put(++Mt09BlockingQueue.sayac);
				System.out.println("sayacin degeri kuyruga eklendi: "+Mt09BlockingQueue.sayac);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
		
	}



	
}



class Tuketici implements Runnable{

	private ArrayBlockingQueue<Integer> kuyruk;
	public Tuketici(ArrayBlockingQueue<Integer> kuyruk) {
	
		this.kuyruk = kuyruk;
	}
	
	
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(5000);
				kuyruk.take();
				Mt09BlockingQueue.sayac--;
				System.out.println("sayacin degeri kuyruktan cikarildi: "+Mt09BlockingQueue.sayac);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
		
	}

	
}
package multiThread;

/* =============================================== THREAD POOL ====================================================
Thread Pool, ExecutorService interface'i yardımıyla oluşturulan bir Thread havuzudur. Hizmet görecek olan
thread'ler baştan bir kere oluşturulur sonrasında ise kuyrukta (TaskQueue) sıranın ona gelmesine bekler.
Havuzun kapasitesi ölçüsünde thread'ler eş zamanlı hizmet görürler.

 Bu yöntem baştan thread'lerin oluşturulmasını, belirli sayıda thread'in eş-zamanlı çalıştırılmasını ve
 gerektiğinde tekrar kullanılmasını sağlamaktadir. Böylece, sürekli Thread oluşturmak ve kaldırmak gibi ciddi
 iş yükü getiren işlemlerin runtime sırasında yapılmasına gerek kalmaz.  Bu da önemli ölçüde performans artışına
 yol açar.

 Özelikle web-sunucu ortamlari için elverişli bir metot sunar.
*/
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class MT07ThreadPool {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
		Thread th1 =  new ThreadOlustur("Thread1");
		Thread th2 =  new ThreadOlustur("Thread2");
		Thread th3 =  new ThreadOlustur("Thread3");
		Thread th4 =  new ThreadOlustur("Thread4");
		Thread th5 =  new ThreadOlustur("Thread5");
		Thread th6 =  new ThreadOlustur("Thread6");
		Thread th7 =  new ThreadOlustur("Thread7");
		Thread th8 =  new ThreadOlustur("Thread8");
		Thread th9 =  new ThreadOlustur("Thread9");
		Thread th10 =  new ThreadOlustur("Thread10");
		
		service.execute(th1);
		service.execute(th2);
		service.execute(th3);
		service.execute(th4);
		service.execute(th5);
		service.execute(th6);
		service.execute(th7);
		service.execute(th8);
		service.execute(th9);
		service.execute(th10);
		
		service.shutdown();
	
	}
}


class ThreadOlustur extends Thread {

	private String threadAdi;
	
	public ThreadOlustur(String threadAdi) {
		
		this.threadAdi = threadAdi;
	}
	
	@Override
	public void run() {
		System.out.println(threadAdi+" calismaya basladi..");
		
		try {
			Integer randomTime = ThreadLocalRandom.current().nextInt(500,3000) ;
			Thread.sleep(randomTime);
			System.out.println(randomTime+"  " +threadAdi);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(threadAdi+" durdu...");
	}



	
	
	
}
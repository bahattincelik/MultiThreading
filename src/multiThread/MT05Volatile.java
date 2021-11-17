package multiThread;

//============================================= VOLATILE ===============================================================
//Volatile keyword'u, Bir degiskenin farklı threadler tarafından kullanılırken degerinin degismesini saglamak icin
//kullanılmaktadir. Aynı zamanda bir class'ı thread-safe yapmak icin de kullanılır. Yani diger bir degisle, bir class
//yada nesneyi farklı thread'lerin es zamanlı olarak problemiz kullanımına olanak saglar.

//Volatile keywordu sadece degiskenler ile (primitif veya non-primitif) kullanılabilir. Nesne ve Class'lara konulmaz

//Volatile keywordu kullanılan bir degiskenin degeri cache bellege saklanmaz. Her defasında ilgili class'ın bellegi
//(MAIN MEMORY) den okunur. Dolayısıyla farklı thread'ler degiskeni guncellese de her defasında en guncel deger okunmus olur.
//Bu özellikleri sayesinde Syncronization yönteminin daha iyi bir alternatifi olarak düşünülebilir.
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

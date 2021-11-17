package multiThread;

public class MT11DeadLockOrnek {

	 public static void main(String[] args) {
	        String kaynak1 = "Defter";
	        String kaynak2 = "Kalem";

	        Thread th1 = new Thread(new Runnable() {
	            @Override
	            public void run() {
	                synchronized (kaynak1) {
	                    System.out.println("Thread1, Defteri kilitledi");

	                    try {
	                        Thread.sleep(500);
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                    synchronized (kaynak2){
	                        System.out.println("Thread1, Kalemi kilitledi");
	                    }
	                }
	            }
	        });
	        Thread th2 = new Thread(new Runnable() {
	            @Override
	            public void run() {
	                synchronized (kaynak2) {
	                    System.out.println("Thread2, Kalemi kilitledi");

	                    try {
	                        Thread.sleep(500);
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                    synchronized (kaynak1){
	                        System.out.println("Thread2, Defteri kilitledi");
	                    }
	                }
	            }
	        });

	        th1.start();
	        th2.start();

	}
	
	
}

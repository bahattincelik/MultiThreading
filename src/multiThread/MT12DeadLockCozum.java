package multiThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MT12DeadLockCozum {
    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flagLock1 = false;
                boolean flagLock2 = false;
                boolean doneLock1 = false;
                boolean doneLock2 = false;
                while (true) {
                    try {
                        if (!flagLock1) {
                            flagLock1 = lock1.tryLock();
                            if (flagLock1) {
                                System.out.println("Thread1 kaynak1 kilitledi");
                            }
                        }
                        if (!flagLock2) {
                            flagLock2 = lock2.tryLock();
                            if (flagLock2) {
                                System.out.println("Thread1 kaynak2 kilitledi");
                            }
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } finally {
                        if (flagLock1 && !doneLock1) {
                            lock1.unlock();
                            doneLock1 = true;
                            System.out.println("Thread1, kaynak1'in kilidini açtı");
                        }
                        if (flagLock2 && !doneLock2) {
                            lock2.unlock();
                            doneLock2 = true;
                            System.out.println("Thread1, kaynak2'in kilidini açtı");
                        }
                        if (flagLock1 && flagLock2) {
                            break;
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flagLock1 = false;
                boolean flagLock2 = false;
                boolean doneLock1 = false;
                boolean doneLock2 = false;

                while (true) {
                    try {
                        if (!flagLock1) {
                            flagLock1 = lock1.tryLock();
                            if (flagLock1) {
                                System.out.println("Thread2 kaynak1 kilitledi");
                            }
                        }
                        if (!flagLock2) {
                            flagLock2 = lock2.tryLock();
                            if (flagLock1) {
                                System.out.println("Thread1 kaynak2 kilitledi");
                            }
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } finally {
                        if (flagLock1 && !doneLock1) {
                            lock1.unlock();
                            doneLock1 = true;
                            System.out.println("Thread2, kaynak1'in kilidini açtı");
                        }
                        if (flagLock2 && !doneLock2) {
                            lock2.unlock();
                            doneLock2 = true;
                            System.out.println("Thread2, kaynak2'nin kilidini açtı");
                        }
                        if (flagLock1 && flagLock2) {
                            break;
                        }
                    }
                }
            }
        });
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread'lerin calimsasi tamamlandi...");

    }

}
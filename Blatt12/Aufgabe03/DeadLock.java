package uebung_12;

public class DeadLock extends Thread{
	public static void main(String [] args) throws Exception{
		Object l1 = new Object();
		Object l2 = new Object();
		Thread t1 = new DeadLock(l1, l2);
		Thread t2 = new DeadLock(l1, l2);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}

	private Object lock1;
	private Object lock2;

	public DeadLock(Object lock1, Object lock2){
		this.lock1 = lock1;
		this.lock2 = lock2;
	}

	public void run () {
		synchronized (lock1) {
			System.out.println("First Lock acquired!");
			synchronized (lock2) {
				System.out.println("Second Lock acquired!");
			}
		}
		System.out.println("Done! All locks released!");
	}
}
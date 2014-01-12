package net.juniper.monitor.test;

public class MainTestCase {
	public static void main(String[] args){
		//not a thread
		new ServerStartThread().run();
		
		for(int i = 0; i < 10; i ++){
			Thread reqThread = new RequestThread("method" + i);
			reqThread.start();
		}
		
//		Thread jobThread =/* new JobThread();
//		jobThread.start();*/
		try {
			Thread.currentThread().join();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

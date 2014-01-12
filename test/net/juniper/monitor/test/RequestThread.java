package net.juniper.monitor.test;

import java.util.Random;

import net.juniper.jmp.tracer.ThreadTracer;


public class RequestThread extends Thread{
	private Random random = new Random();
	public RequestThread(String method) {
	}

	public void run() {
		while(true){
			try{
				ThreadTracer.getInstance().startTracer();
				ThreadTracer.getInstance().setName("RequestThread");
				try {
					Thread.sleep(getThinkTime());
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				ThreadTracer.getInstance().setUserId("testuser");
				ThreadTracer.getInstance().setClientIp("127.0.0.1");
				ThreadTracer.getInstance().setCallPath("/mainui");
				ThreadTracer.getInstance().setCallMethod("LoadingServlet");
				ThreadTracer.getInstance().setRequestBytes(1024);
				ThreadTracer.getInstance().setReponseBytes(24);
				new A().run();
				new A().run();
				new A().run();
				new A().run();
				new A().run();
				new A().run();
				new A().run();
				new A().run();
				new A().run();
				new A().run();
				
				
				try {
					Thread.sleep(getThinkTime());
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				ThreadTracer.getInstance().startStage("Stage1");
				
				try {
					Thread.sleep(getThinkTime());
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				ThreadTracer.getInstance().startStage("Stage11");
				try {
					Thread.sleep(getThinkTime());
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				ThreadTracer.getInstance().endStage();
				try {
					Thread.sleep(getThinkTime());
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				ThreadTracer.getInstance().startStage("Stage12");
				ThreadTracer.getInstance().endStage();
				
				ThreadTracer.getInstance().endStage();
				try {
					Thread.sleep(getThinkTime());
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				try {
					Thread.sleep(getThinkTime());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			finally{
				ThreadTracer.getInstance().endTracer();
			}
		}
	}
	
	public long getThinkTime() {
		int rand = random.nextInt();
		return Math.abs(rand % 10000);
	}
}

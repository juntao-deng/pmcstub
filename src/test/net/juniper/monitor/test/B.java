package net.juniper.monitor.test;

import java.lang.reflect.Method;
import java.util.Random;

import net.juniper.jmp.tracer.ThreadTracer;

public class B {
	public void run() {
		Class<C> bc;
		int index = new Random().nextInt();
		try {
			if(index % 5 == 0){
				ThreadTracer.getInstance().startStage("B stage");
			}
			bc = (Class<C>) Class.forName(C.class.getName());
			C obj = bc.newInstance();
			Method m = bc.getMethod("run", null);
			m.invoke(obj, null);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(index % 5 == 0){
				ThreadTracer.getInstance().endStage();
			}
			
		}
	}
}

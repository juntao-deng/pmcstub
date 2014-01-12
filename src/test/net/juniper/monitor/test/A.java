package net.juniper.monitor.test;

import java.lang.reflect.Method;

public class A {

	public void run() {
		Class<B> bc;
		try {
			bc = (Class<B>) Class.forName(B.class.getName());
			B obj = bc.newInstance();
			Method m = bc.getMethod("run", null);
			m.invoke(obj, null);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package newpackage;

import java.util.ArrayList;
import java.util.Iterator;

public class ppt {
	

	
		/*
		 * It is concrete class in java 
		 * we can red and add and delete the bjects 
		 * it can can grow dynamicallyy 
		 * the name og inbult class java.util.list
		 * The fully wualified name is java,util.Atrraylist
		 * We can create a obhecet foin array list 
		 * insertion arder i snot mentioned
		
		 * 
		 * */

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			ArrayList a = new ArrayList();
			a.add("Radha");
			a.add("Gobinda");
			a.add("Radha");
			a.add("Dash");
			
			for(int i =0; i<a.size(); i++) {
				System.out.println(a.get(i));
			}
			System.out.println("Execution of itreator ");
//	      Using iterartor
			Iterator var = a.iterator();
			while(var.hasNext()) {
				System.out.println(var.next());
			}
			
		}

}

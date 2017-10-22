package Monitor;

import java.util.LinkedList;
import java.util.Queue;

public class BathroomQueue {
	
	private Queue<String> buffer;
	
    private final int TAM_BUFFER = 5;
    
    public BathroomQueue() {
    	buffer = new LinkedList<String>();
    }
    
    public synchronized void entrar(String item) {
    	while (buffer.size() == TAM_BUFFER) {
        	System.out.print("Banheiro cheio. ");
    		System.out.print(Thread.currentThread().getName() + " suspenso.\n");
    		try {
    			wait();
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
        }
    	
    	while(buffer.size() > 0 && !buffer.contains(item)){
	    	System.out.print("Banheiro sendo usado por outro genero. ");
			System.out.print(Thread.currentThread().getName() + " suspenso.\n");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
    	buffer.add(item);
    	System.out.println(Thread.currentThread().getName() + " entrou: ");
    }
    

    public synchronized void sair() {
    	while (buffer.size() == 0) {
        	System.out.print("Banheiro vazio. ");
    		System.out.print(Thread.currentThread().getName() + " suspenso.\n");
    		try {
    			wait();
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
        }
        	
        String item = buffer.remove();
        System.out.println(Thread.currentThread().getName() + " saiu ");
        notify();
    }
}
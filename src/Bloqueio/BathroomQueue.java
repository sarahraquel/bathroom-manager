package Bloqueio;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BathroomQueue {

	private Queue<String> buffer;
	private final int TAM_BUFFER = 5;
	private Lock bloqueio;
	private Condition cheio;
	private Condition vazio;

	public BathroomQueue() {
		buffer = new LinkedList<String>();
		bloqueio = new ReentrantLock();
		cheio = bloqueio.newCondition();
		vazio = bloqueio.newCondition();
	}

	public void entrar(String item) {

		bloqueio.lock();
		try {
			while (buffer.size() == TAM_BUFFER) {
				System.out.print("Banheiro cheio. ");
				System.out.print(Thread.currentThread().getName() + " suspenso.\n");
				cheio.await();
			}
			while (buffer.size() > 0 && !buffer.contains(item)) {
				System.out.print("Banheiro sendo usado por outro genero. ");
				System.out.print(Thread.currentThread().getName() + " suspenso.\n");
				cheio.await();
			}
			buffer.add(item);
			System.out.print(Thread.currentThread().getName() + " entrou no banheiro.\n");
			vazio.signal();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bloqueio.unlock();
		}
	}

	public void sair() {
		bloqueio.lock();
		try {
			while (buffer.size() == 0) {
				System.out.print("Banheiro vazio. ");
				System.out.print(Thread.currentThread().getName() + " suspenso.\n");
				vazio.await();
			}
			String item = buffer.remove();
			System.out.println(Thread.currentThread().getName() + " retirou");
			cheio.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bloqueio.unlock();
		}
	}

}

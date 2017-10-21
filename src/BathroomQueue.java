import java.util.concurrent.Semaphore;

public class BathroomQueue {

	private Semaphore semaforo;
	private boolean isManUsing;
	
	public BathroomQueue() {
		semaforo = new Semaphore(4, true);
	}

	public void useBathroom(boolean isMan) {
		if(isMan == this.isManUsing){
			usar(isMan);
		}
		else{
			while(this.semaforo.getQueueLength() != 0){
				System.out.println("Outro genero ta usando o banheiro, esperando");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Todos sairam, minha vez!");
			usar(isMan);
		}
	}
	
	private void usar(boolean isMan){
		this.isManUsing = isMan;
		try {
			semaforo.acquire();
			int duracao = (int) (Math.random() * 5) + 1;
			System.out.print(Thread.currentThread().getName() + " usando o banheiro por " + duracao + " segundos\n");
			Thread.sleep(duracao * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.print(Thread.currentThread().getName() + " saiu \n");
			semaforo.release();
		}
	}
}

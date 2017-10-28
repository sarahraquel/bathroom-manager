package Bloqueio;

public class Person extends Thread {

	private BathroomQueue queue;
	private boolean gender; // True homem, False mulher

	public Person(String nome, boolean gender, BathroomQueue queue) {
		super(nome + (gender ? " homem" : " mulher"));
		this.queue = queue;
		this.gender = gender;
	}

	@Override
	public void run() {
		queue.entrar(String.valueOf(gender));
		int duracao = (int) (Math.random() * 5) + 1;
		try {
			this.sleep(duracao * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		queue.sair();
	}

}

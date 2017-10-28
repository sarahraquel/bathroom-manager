package Bloqueio;

public class LockMain {

	public static void main(String[] args) {

		BathroomQueue queue = new BathroomQueue();
		int qtd = 8;
		Person pessoas[] = new Person[qtd];

		for (int i = 0; i < qtd; i++) {
			double aleatorio = (int) (Math.random() * 10);
			pessoas[i] = new Person("Pessoa " + (i + 1), aleatorio % 2 == 0 ? true : false, queue);
		}

		try {
			for (int i = 0; i < qtd; i++) {
				pessoas[i].start();

			}

			for (int i = 0; i < qtd; i++) {
				pessoas[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

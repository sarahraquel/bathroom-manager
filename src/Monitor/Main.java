package Monitor;

public class Main {
	/**
	 * Metodo principal
	 * @param args Argumentos de linha de comando
	 */
	public static void main(String[] args) {

		BathroomQueue queue = new BathroomQueue();
		int qtd = 8;
		Pessoa pessoas[] = new Pessoa[qtd];

		for (int i = 0; i < qtd/2; i++) {
			pessoas[i] = new Pessoa("Pessoa " + (i+1), true, queue);
			pessoas[i+(qtd/2)] = new Pessoa("Pessoa " + (i+1+(qtd/2)), false, queue);
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

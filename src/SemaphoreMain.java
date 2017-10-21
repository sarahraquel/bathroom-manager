
public class SemaphoreMain {

	public static void main(String[] args) {

		BathroomQueue queue = new BathroomQueue();
		Person people[] = new Person[10];
		
		for (int i = 0; i < 2; i++) {
			people[i] = new Person("Person " + (i+1), queue, false); 
			people[i+2] = new Person("Person " + (i+1+2), queue, true); 
		}
		
		for (int i = 0; i < 4; i++) {
			people[i].start();
		}
        
	}

}

public class Person extends Thread{
	

	private BathroomQueue queue;
	private boolean isMan;
	
	public Person(String name,BathroomQueue queue, boolean isMan) {
		super((isMan? "Man " : "Woman ")+name);
		this.queue = queue;
		this.isMan = isMan;
	}

	@Override
    public void run() {
		System.out.println(Thread.currentThread().getName() + " wants to use the bathroom");
		queue.useBathroom(isMan);	
    }

}

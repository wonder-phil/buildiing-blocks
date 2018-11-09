package b;

public class MineBlock implements Runnable {

	private volatile boolean exit = false;
	
	@Override
	public void run() {
		while(!exit) {
			for (int i =0 ; i < 10000000; i++)  {
				int j = 2;
				j += 1;
			}
			System.out.println("Running");
		}
		System.out.println("Stopped");
	}
	
	public void stop(){
		exit = true;
	}

}

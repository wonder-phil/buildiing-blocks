package b;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import com.google.gson.Gson;

public class MineBlock implements Runnable {

    private volatile boolean exit = false;
    private String fileNPath = "block.json";
    
    
    @Override
	public void run() {
	
	
	BufferedReader bufferedReader = null;
	try {
	    bufferedReader = new BufferedReader(new FileReader(fileNPath));
	} catch (FileNotFoundException e) {
	    System.err.println(" Can't read JSON Block ");
	    e.printStackTrace();
	}

        Gson gson = new Gson();
        Block json = gson.fromJson(bufferedReader, Block.class); // Block.class
        
        System.out.println("Mine Block");
        System.out.println(json.toString());
	
	
    }
    
    public void stop(){
	exit = true;
    }

}

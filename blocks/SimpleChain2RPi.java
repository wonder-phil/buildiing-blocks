package blocks;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class SimpleChain2RPi {
 
	private static final String dir_FILETOTRANSFER = "/Users/pgb15001/TALKS/CCWC-2020/CCWC-workshop/";
	public static ArrayList<Block> blockChain = new ArrayList<Block>();
	public static int difficulty = 4;

	public static void main(String[] args) {	
		//add our blocks to the blockchain ArrayList:
		
		SFTPinJava Sftp = new SFTPinJava();
		
		blockChain.add(new Block("The first block ", "0"));
		System.out.println("Hash of Gensis block: " + blockChain.get(0).hash);
		System.out.println("Trying to Mine block 1... ");
		blockChain.get(0).mineBlock(difficulty);
		
		Block block = blockChain.get(blockChain.size()-1);
		writeToFile(dir_FILETOTRANSFER + "Block1.txt", block.toString());
		Sftp.sendFile(dir_FILETOTRANSFER + "Block1.txt");
		
		/*
		 * 
		 */
		
		blockChain.add(new Block("The second block ", blockChain.get(blockChain.size()-1).hash));
		System.out.println("Trying to Mine block 2... ");
		blockChain.get(1).mineBlock(difficulty);
		
		block = blockChain.get(blockChain.size()-1);
		writeToFile(dir_FILETOTRANSFER + "Block2.txt", block.toString());
		Sftp.sendFile(dir_FILETOTRANSFER + "Block2.txt");
		
		/*
		 * 
		 */
		
		blockChain.add(new Block("The third block ", blockChain.get(blockChain.size()-1).hash));
		System.out.println("Trying to Mine block 3... ");
		blockChain.get(2).mineBlock(difficulty);	
		
		block = blockChain.get(blockChain.size()-1);
		writeToFile(dir_FILETOTRANSFER + "Block3.txt", block.toString());
		Sftp.sendFile(dir_FILETOTRANSFER + "Block3.txt");
		
		/*
		 * 
		 */
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
	}
	
	private static void writeToFile(String fullFileName, String toFile) {
		try {
			
			Files.write(Paths.get(fullFileName), toFile.getBytes());

          
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private static void writeObjectToFile(String fileName, Block blockToFile) {
		FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(fileName);
            out = new ObjectOutputStream(fos);
            out.writeObject(blockToFile);

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

}
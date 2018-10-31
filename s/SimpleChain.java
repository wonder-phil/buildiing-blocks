/*
 *  https://medium.com/programmers-blockchain/create-simple-blockchain-java-tutorial-from-scratch-6eeed3cb03fa
 */
package s;


import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class SimpleChain {
 
	public static ArrayList<Block> dogchain = new ArrayList<Block>();
	public static int difficulty = 5;

	public static void main(String[] args) {	
		//add our blocks to the blockchain ArrayList:
		
		dogchain.add(new Block("The first block", "0"));
		System.out.println("Trying to Mine block 1... ");
		dogchain.get(0).mineBlock(difficulty);
		
		dogchain.add(new Block("The second block",dogchain.get(dogchain.size()-1).hash));
		System.out.println("Trying to Mine block 2... ");
		dogchain.get(1).mineBlock(difficulty);
		
		dogchain.add(new Block("The third block",dogchain.get(dogchain.size()-1).hash));
		System.out.println("Trying to Mine block 3... ");
		dogchain.get(2).mineBlock(difficulty);	
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(dogchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
		for(int i=1; i < dogchain.size(); i++) {
			currentBlock = dogchain.get(i);
			previousBlock = dogchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
}
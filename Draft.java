import java.util.*;

public class Draft {

	public static int[] getDraftOrder(int numPlayers) {
		int[] randNums = new int[numPlayers];
		Random orderGen = new Random();
		for (int i = 0; i < numPlayers; i++) {
			int test = orderGen.nextInt(numPlayers);
			boolean done = checkRand(test, i, randNums);
			while (done == false) {
				int testAgain = orderGen.nextInt(numPlayers);
				done = checkRand(testAgain, i, randNums);
			}
		}
		if (randNums[randNums.length - 1] == 0)
		{
			for (int i = randNums.length - 1; i > 0; i--) {
				randNums[i] = randNums[i - 1];
			}
			randNums[0] = 0;
		}

		System.out.println(Arrays.toString(randNums));
		return randNums;
	}
	//helper function for getDraftOrder
	public static boolean checkRand(int num, int idx, int[] order) {
		boolean noRepeat = false;
		order[idx] = num; 
		for (int i = 0; i < idx; i++) {
			if (num == order[i]) {
				return false;
			}
		}
		return true;
	}
}

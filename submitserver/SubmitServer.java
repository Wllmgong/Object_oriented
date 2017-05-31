package submitserver;

/**
 * Name:William Gong
 * Section:0303
 * ID:113510311
 * Directory ID:wgong1
 * Honor Code:I pledge on my honor that i have not give or received any
 * unauthorized assistance on this assignment
 * 

 * 
 * @author William Gong
 *
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SubmitServer {
	HashMap<String, HashMap<Integer, Integer>> submissions
	= new HashMap<String, HashMap<Integer, Integer>>();

	public void addSubmission(String id, int[] results) {
		if (results == null || id == null)
			throw new NullPointerException();
		if (results.length == 0)
			throw new IllegalArgumentException();
		int score = 0;
		for (int i : results)
			score += i;
		if (submissions.containsKey(id)) {

			Set<Integer> temp = submissions.get(id).keySet();
			int k = temp.size();
			submissions.get(id).put(k + 1, score);
		}

		else {
			HashMap<Integer, Integer> k = new HashMap<Integer, Integer>();
			k.put(1, score);
			submissions.put(id, k);
		}

	}

	public int numSubmissions(String id) {
		if (id == null)
			throw new NullPointerException();
		if (submissions.containsKey(id)) {
			Set<Integer> k = submissions.get(id).keySet();
			return k.size();
		}
		return 0;
	}

	public int numSubmissions() {
		int count = 0;
		Set<String> k = submissions.keySet();
		for (String t : k) {
			Set<Integer> i = submissions.get(t).keySet();
			count += i.size();
		}
		return count;

	}

	// return which of the submissions is the best
	public int bestSubmissionNumber(String id) {
		if (id == null)
			throw new NullPointerException();

		int score = 0;
		int num = 0;
		if (submissions.containsKey(id)) {
			Set<Integer> i = submissions.get(id).keySet();
			for (Integer n : i) {
				if (score <= submissions.get(id).get(n)) {
					score = submissions.get(id).get(n);
					num = n;
				}
			}
		}
		return num;
	}

	// return which score is the best
	public int bestSubmissionScore(String id) {
		if (id == null)
			throw new NullPointerException();

		int score = 0;
		if (submissions.containsKey(id)) {
			Set<Integer> i = submissions.get(id).keySet();
			for (Integer n : i) {
				if (score < submissions.get(id).get(n)) {
					score = submissions.get(id).get(n);

				}
			}
		}
		return score;
	}

	public boolean gotExtraCredit(String id) {
		if (id == null)
			throw new NullPointerException();

		if (this.numSubmissions(id) == 1)
			if (this.bestSubmissionScore(id) == 100)
				return true;
		return false;
	}

	public Set<String> gotExtraCredit() {
		Set<String> k = submissions.keySet();
		Set<String> extra = new HashSet<String>();
		for (String t : k) {
			if (gotExtraCredit(t)) {
				extra.add(t);
			}
		}
		return extra;
	}

}

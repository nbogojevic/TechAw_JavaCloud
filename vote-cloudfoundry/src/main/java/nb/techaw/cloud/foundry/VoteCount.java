package nb.techaw.cloud.foundry;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VoteCount {
	@Id
	String choice;
	int count;
	public VoteCount() {
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}

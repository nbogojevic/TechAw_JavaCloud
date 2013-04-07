package nb.techaw.vote;

public interface Ballot {

	public abstract void vote(String option);

	public abstract String[] choices();

	public abstract int tally(String choice);

	public abstract void cleanup();

}
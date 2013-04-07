package nb.techaw.cloud.foundry;

import org.springframework.web.context.ContextLoader;

import nb.techaw.vote.Ballot;

public class BallotBridge implements Ballot {
	final String[] choices;

	public BallotBridge(String[] choices) {
		super();
		this.choices = choices;
	}

	@Override
	public void vote(String choice) {
		ContextLoader.getCurrentWebApplicationContext().getBean(VoteCounter.class).vote(choice);
	}

	@Override
	public String[] choices() {
		return choices;
	}

	@Override
	public int tally(String choice) {
		return ContextLoader.getCurrentWebApplicationContext().getBean(VoteCounter.class).tally(choice);
	}

	@Override
	public void cleanup() {
		ContextLoader.getCurrentWebApplicationContext().getBean(VoteCounter.class).cleanup();
	}

}

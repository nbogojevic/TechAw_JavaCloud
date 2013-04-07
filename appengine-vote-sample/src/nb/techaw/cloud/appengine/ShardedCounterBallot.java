package nb.techaw.cloud.appengine;

import nb.techaw.vote.AbstractBallot;
import nb.techaw.vote.Counter;

public class ShardedCounterBallot extends AbstractBallot {
  public ShardedCounterBallot(String...choices) {
    super(choices);
  }
  
  protected Counter initCounter(String choice) {
    ShardedCounter counter = new ShardedCounter(choice);
    return counter;
  }
}

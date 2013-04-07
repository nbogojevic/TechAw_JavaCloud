package nb.techaw.vote;

import java.util.HashMap;

public abstract class AbstractBallot implements Ballot {
  private HashMap<String, Counter> counters = new HashMap<String, Counter>();

  public AbstractBallot(String...choices) {
    for (String choice : choices) {
      counters.put(choice, initCounter(choice));
    }
  }

  /* (non-Javadoc)
 * @see nb.techaw.vote.Ballot#vote(java.lang.String)
 */
@Override
public final void vote(String option) {
    Counter counter = counters.get(option);
    if (counter != null) {
      counter.increment();
    } else {
      throw new IllegalArgumentException("Option not recognized");
    }
  }

  /* (non-Javadoc)
 * @see nb.techaw.vote.Ballot#choices()
 */
@Override
public final String[] choices() {
    return counters.keySet().toArray(new String[0]);
  }

  /* (non-Javadoc)
 * @see nb.techaw.vote.Ballot#tally(java.lang.String)
 */
@Override
public int tally(String choice) {
    Counter counter = counters.get(choice);
    if (counter != null) {
      return counter.getCount();
    }
    return 0;
  }

  /* (non-Javadoc)
 * @see nb.techaw.vote.Ballot#cleanup()
 */
@Override
public final void cleanup() {
    for (Counter counter : counters.values()) {
      counter.clean();
    }    
  }

  protected abstract Counter initCounter(String choice);
}

package nb.techaw.cloud.foundry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nb.techaw.vote.Ballot;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoteCounter implements Ballot {
	private EntityManager entityManager;
	String[] choices = {"A", "B", "C"};

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	@Transactional
	@Override
	public void vote(String choice) {
		VoteCount vc = entityManager.find(VoteCount.class, choice);
		if (vc == null) {
			vc = new VoteCount();
			vc.setChoice(choice);
			vc.setCount(1);
			entityManager.persist(vc);
		}
		else {
			vc.count++;
			entityManager.merge(vc);			
		}
	}

	@Override
	public String[] choices() {
		return choices;
	}

	@Override
	public int tally(String option) {
		VoteCount vc = entityManager.find(VoteCount.class, option);
		if (vc == null) {
			vc = new VoteCount();
			vc.setChoice(option);
			entityManager.persist(vc);			
		}
		return vc.count;
	}

	@Override
	public void cleanup() {
	}
}

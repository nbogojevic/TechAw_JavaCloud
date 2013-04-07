package nb.techaw.vote;

import java.lang.reflect.Constructor;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

public class BallotContainer implements ServletContextListener {
  private static final String BALLOT_LIST_INIT_PARAM = "ballot-list";
  private static final String BALLOT_IMPLEMENTATION_INIT_PARAM = "ballot-implementation";
  private static final String BALLOT_CTX_KEY = "ballot";
  private static final String[] BALLOT = {"A", "B", "C"};

  @Override
  public void contextDestroyed(ServletContextEvent event) {
  }

  @Override
  public void contextInitialized(ServletContextEvent event) {
	ServletContext ctx = event.getServletContext();
    String implName = ctx.getInitParameter(BALLOT_IMPLEMENTATION_INIT_PARAM);
    String ballotListParam = ctx.getInitParameter(BALLOT_LIST_INIT_PARAM);
    String[] ballotList = ballotListParam != null ? ballotListParam.split(",") : BALLOT;
    try {
      @SuppressWarnings("unchecked")
      Class<? extends AbstractBallot> ballotImpl = (Class<? extends AbstractBallot>) Class.forName(implName);
      Constructor<? extends AbstractBallot> constructor = ballotImpl.getConstructor(String[].class);
      ctx.setAttribute(BALLOT_CTX_KEY, constructor.newInstance((Object) ballotList));
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException("Unable to initiate ballot instance " + implName, e);
    }
  }

  public static Ballot get(ServletContext ctx) {
	return (Ballot) WebApplicationContextUtils.getWebApplicationContext(ctx).getBean("voteCounter");
  }
}

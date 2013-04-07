package nb.techaw.vote;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class VoteServlet extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	doPost(req, resp);	
}
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	String input = req.getPathInfo() != null ? req.getPathInfo().substring(1) : req.getParameter("vote");
    Ballot ballot = BallotContainer.get(getServletContext());
    try {
      ballot.vote(input);
    } catch (IllegalArgumentException e) {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
      return;      
    }
    new JSON().append("voted", input).marshall(resp);
  }
}
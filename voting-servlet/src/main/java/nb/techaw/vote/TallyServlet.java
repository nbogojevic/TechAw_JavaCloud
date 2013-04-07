package nb.techaw.vote;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/tally")
@SuppressWarnings("serial")
public class TallyServlet extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Ballot ballot = BallotContainer.get(getServletContext());
    JSON json = new JSON();
    for (String choice :  ballot.choices()) {
      json.append(choice, ballot.tally(choice));
    }
    json.marshall(resp);
  }
}
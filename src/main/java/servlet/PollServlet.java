package servlet;

import dao.PollDao;
import dao.impl.PolllDaoImpl;
import model.Poll;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/poll")
public class PollServlet extends HttpServlet {

    private final PollDao<Poll> pollDao;

    public PollServlet(){
        pollDao = new PolllDaoImpl();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Poll> pollList = null;
        try {
            pollList = pollDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("pollList",pollList);
        req.getRequestDispatcher("/WEB-INF/view/poll.jsp").forward(req,resp);
    }
}

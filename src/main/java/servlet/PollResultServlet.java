package servlet;

import dao.AnswerDao;
import dao.ResultDao;
import dao.impl.AnswerDaoImpl;
import dao.impl.ResultDaoImpl;
import model.Answer;
import model.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

@WebServlet("/poll/result")
public class PollResultServlet extends HttpServlet {

    private final AnswerDao answerDao;
    private final ResultDao resultDao;

    public PollResultServlet(){
        answerDao = new AnswerDaoImpl();
        resultDao = new ResultDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> attributeNames = req.getAttributeNames();
        int weight = 0;
        while (attributeNames.hasMoreElements()){
            String attrName = attributeNames.nextElement();
            if(attrName.endsWith("_question")){
                String answerId = req.getParameter(attrName);
                Answer answer = null;
                try {
                    answer = answerDao.findById(Long.parseLong(answerId));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                assert answer != null;
                weight += answer.getWeight();
            }
        }
        Result result = null;
        try {
            result = resultDao.findByScoreBetween(weight);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(result == null){
            resp.sendRedirect("/poll");
        }else {
            req.setAttribute("result",result);
            req.getRequestDispatcher("/pollResult.jsp").forward(req,resp);
        }

    }
}

package servlet;

import dao.AnswerDao;
import dao.QuestionDao;
import dao.impl.AnswerDaoImpl;
import dao.impl.QuestionDaoImpl;
import model.Answer;
import model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/poll/beginpoll")
public class PollDetailedServlet extends HttpServlet {

    private final QuestionDao questionDao;
    private final AnswerDao answerDao;

    public PollDetailedServlet(){
        questionDao = new QuestionDaoImpl();
        answerDao = new AnswerDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pollId = req.getParameter("pollId");
        if(pollId == null || pollId.trim().isEmpty()){
            resp.sendRedirect("/poll");
        }else {
            List<Question> questionList = null;
            try {
                questionList = questionDao.findByPollId(Long.parseLong(pollId));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            assert questionList != null;
            questionList = questionList
                    .stream()
                    .peek(question -> {
                        List<Answer> answerList = null;
                        try {
                            answerList = answerDao.findByQuestionId(question.getId());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        question.setAnswers(answerList);
                    }).collect(Collectors.toList());
            req.setAttribute("questionList",questionList);
            req.getRequestDispatcher("/pollDetail.jsp").forward(req,resp);
        }
    }
}

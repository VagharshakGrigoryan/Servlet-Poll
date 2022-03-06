package dao;


import model.Answer;

import java.sql.SQLException;
import java.util.List;

public interface AnswerDao extends PollDao<Answer> {

    List<Answer> findByQuestionId(long questionId) throws SQLException;
}

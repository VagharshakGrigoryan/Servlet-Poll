package dao;


import model.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionDao extends PollDao<Question> {

    List<Question> findByPollId(long pollId) throws SQLException;
}

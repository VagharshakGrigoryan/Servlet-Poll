package dao;


import model.Result;

import java.sql.SQLException;
import java.util.List;

public interface ResultDao extends PollDao<Result> {

    List<Result> findByPollId(long pollId);

    Result findByScore(long pollId, int score) throws SQLException;

    Result findByScoreBetween(int weight) throws SQLException;
}

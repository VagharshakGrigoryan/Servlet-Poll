package dao.impl;

import dao.ResultDao;
import dao.config.ConnectionFactory;
import lombok.SneakyThrows;
import model.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDaoImpl implements ResultDao {

    private final Connection connection;

    public ResultDaoImpl(){
        this.connection = ConnectionFactory.getInstance().getConnection();
    }


    @Override
    @SneakyThrows
    public List<Result> findAll() {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM result");
        List<Result> resultList = new ArrayList<>();
        while (resultSet.next()){
            resultList.add(Result
                    .builder()
                    .id(resultSet.getLong("id"))
                    .explanation(resultSet.getString("explanation"))
                    .minScore(resultSet.getInt("min_score"))
                    .maxScore(resultSet.getInt("max_score"))
                    .build());
        }
        return resultList;
    }

    @Override
    @SneakyThrows
    public Result findById(long id) {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM result where id = ?");
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return Result
                    .builder()
                    .id(resultSet.getLong("id"))
                    .explanation(resultSet.getString("explanation"))
                    .minScore(resultSet.getInt("min_score"))
                    .maxScore(resultSet.getInt("max_score"))
                    .build();
        }
        return null;
    }

    @Override
    @SneakyThrows
    public List<Result> findByPollId(long pollId) {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM result where poll_id = ?");
        preparedStatement.setLong(1,pollId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Result> resultList = new ArrayList<>();
        while (resultSet.next()){
            resultList.add(Result
                    .builder()
                    .id(resultSet.getLong("id"))
                    .explanation(resultSet.getString("explanation"))
                    .minScore(resultSet.getInt("min_score"))
                    .maxScore(resultSet.getInt("max_score"))
                    .build());
        }
        return resultList;

    }

    @Override
    public Result findByScore(long pollId, int score) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM result where poll_id = ? and max_score = ?");
        preparedStatement.setLong(1,pollId);
        preparedStatement.setLong(2,score);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return Result
                    .builder()
                    .id(resultSet.getLong("id"))
                    .explanation(resultSet.getString("explanation"))
                    .minScore(resultSet.getInt("min_score"))
                    .maxScore(resultSet.getInt("max_score"))
                    .build();
        }
        return null;
    }

    @Override
    public Result findByScoreBetween(int weight) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM result where ? between min_score and max_score");
        preparedStatement.setLong(1,weight);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return Result
                    .builder()
                    .id(resultSet.getLong("id"))
                    .explanation(resultSet.getString("explanation"))
                    .minScore(resultSet.getInt("min_score"))
                    .maxScore(resultSet.getInt("max_score"))
                    .build();
        }
        return null;

    }
}

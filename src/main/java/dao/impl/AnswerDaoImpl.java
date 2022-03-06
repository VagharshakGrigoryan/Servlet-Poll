package dao.impl;

import dao.AnswerDao;
import dao.config.ConnectionFactory;
import model.Answer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoImpl implements AnswerDao {

    private final Connection connection;

    public AnswerDaoImpl(){
        this.connection = ConnectionFactory.getInstance().getConnection();
    }


    @Override
    public List<Answer> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM answer");
        List<Answer> answerList = new ArrayList<>();
        while (resultSet.next()){
            answerList.add(Answer
                    .builder()
                    .id(resultSet.getLong("id"))
                    .text(resultSet.getString("text"))
                    .weight(resultSet.getInt("weight"))
                    .build());
        }
        return answerList;
    }

    @Override
    public Answer findById(long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM answer where id = ?");
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return Answer
                    .builder()
                    .id(resultSet.getLong("id"))
                    .text(resultSet.getString("text"))
                    .weight(resultSet.getInt("weight"))
                    .build();
        }
        return null;
    }

    @Override
    public List<Answer> findByQuestionId(long questionId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM answer where question_id = ?");
        preparedStatement.setLong(1,questionId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Answer> answerList = new ArrayList<>();
        while (resultSet.next()){
            answerList.add(Answer
                    .builder()
                    .id(resultSet.getLong("id"))
                    .text(resultSet.getString("text"))
                    .weight(resultSet.getInt("weight"))
                    .build());
        }
        return answerList;
    }
}

package dao.impl;


import dao.QuestionDao;
import dao.config.ConnectionFactory;
import model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    private final Connection connection;

    public QuestionDaoImpl(){
        this.connection = ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public List<Question> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM question");
        List<Question> questionList = new ArrayList<>();
        while (resultSet.next()){
            questionList.add(Question
                    .builder()
                    .id(resultSet.getLong("id"))
                    .text(resultSet.getString("text"))
                    .build());
        }
        return questionList;
    }

    @Override
    public Question findById(long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM question where id = ?");
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return Question
                    .builder()
                    .id(resultSet.getLong("id"))
                    .text(resultSet.getString("text"))
                    .build();
        }
        return null;
    }

    @Override
    public List<Question> findByPollId(long pollId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM question where poll_id = ?");
        preparedStatement.setLong(1,pollId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Question> questionList = new ArrayList<>();
        while (resultSet.next()){
            questionList.add(Question
                    .builder()
                    .id(resultSet.getLong("id"))
                    .text(resultSet.getString("text"))
                    .build());
        }
        return questionList;
    }
}

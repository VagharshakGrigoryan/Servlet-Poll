package dao.impl;

import dao.PollDao;
import dao.config.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import model.Poll;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PolllDaoImpl implements PollDao<Poll> {

    private final Connection connection;

    public PolllDaoImpl(){
        this.connection = ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public List<Poll> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM poll");
        List<Poll> pollList = new ArrayList<>();
        while (resultSet.next()){
            pollList.add(Poll
                    .builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .description(resultSet.getString("description"))
                    .build());
        }
        return pollList;
    }

    @Override
    public Poll findById(long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM poll where id = ?");
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return Poll
                    .builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .description(resultSet.getString("description"))
                    .build();
        }
        return null;
    }
}

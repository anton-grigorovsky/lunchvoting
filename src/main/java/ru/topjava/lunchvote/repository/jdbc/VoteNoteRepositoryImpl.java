package ru.topjava.lunchvote.repository.jdbc;

import org.apache.commons.dbutils.DbUtils;
import org.springframework.stereotype.Repository;
import ru.topjava.lunchvote.model.Restaurant;
import ru.topjava.lunchvote.model.VoteNote;
import ru.topjava.lunchvote.repository.VoteNoteRepository;
import ru.topjava.lunchvote.util.DBCredentials;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Антон on 01.07.2018.
 */
@Repository
public class VoteNoteRepositoryImpl implements VoteNoteRepository
{
    private Connection connection;

    @Override
    public VoteNote save(VoteNote note) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = DriverManager.getConnection(DBCredentials.URL, DBCredentials.NAME, DBCredentials.PASSWORD);
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT COUNT(*) FROM vote_notes WHERE user_id=? AND date=?");
            statement.setInt(1, note.getUserId());
            statement.setDate(2, Date.valueOf(note.getDate()));
            resultSet = statement.executeQuery();
            if(resultSet.next() && resultSet.getInt(1) == 0)
            {
                DbUtils.closeQuietly(resultSet);
                DbUtils.closeQuietly(statement);
                statement = connection.prepareStatement("INSERT INTO vote_notes (restaurant_id, user_id) VALUES (?,?)");
                statement.setInt(1, note.getRestaurantId());
                statement.setInt(2, note.getUserId());
                count = statement.executeUpdate();
            }
            else
            {
                DbUtils.closeQuietly(resultSet);
                DbUtils.closeQuietly(statement);
                statement = connection.prepareStatement("UPDATE vote_notes SET restaurant_id=? WHERE user_id=? AND date=?");
                statement.setInt(1, note.getRestaurantId());
                statement.setInt(2, note.getUserId());
                statement.setDate(3, Date.valueOf(note.getDate()));
                count = statement.executeUpdate();
            }
            connection.commit();
        }
        catch (SQLException e)
        {
            DbUtils.rollbackAndCloseQuietly(connection);
        }
        finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }
        return count > 0 ? note : null;
    }

    @Override
    public List<VoteNote> getAll() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<VoteNote> notes = null;
        try {
            connection = DriverManager.getConnection(DBCredentials.URL, DBCredentials.NAME, DBCredentials.PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM vote_notes");
            notes = getNotesFromRs(resultSet);
        }
        catch (SQLException e)
        {
        }
        finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return notes;
    }

    private List<VoteNote> getNotesFromRs(ResultSet resultSet)
    {
        List<VoteNote> notes = new ArrayList<>();
        try
        {
            while (resultSet.next())
            {
                VoteNote note = new VoteNote();
                note.setDate(resultSet.getDate(1).toLocalDate());
                note.setRestaurantId(resultSet.getInt(2));
                note.setUserId(resultSet.getInt(3));
                notes.add(note);
            }
        }
        catch (SQLException e)
        {
        }
        return notes;
    }
}

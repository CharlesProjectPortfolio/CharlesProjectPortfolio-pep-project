package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {

    public Message createNewMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "insert into message(posted_by, message_text, time_posted_epoch) values (?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            preparedStatement.executeUpdate();
            
            String sql2 = "SELECT * FROM message WHERE posted_by=? AND message_text=?;";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setInt(1, message.getPosted_by());
            preparedStatement2.setString(2, message.getMessage_text());;

            ResultSet rs = preparedStatement2.executeQuery();
            if (rs.next()) {
                Message mess = new Message();
                mess.setMessage_id((int)rs.getLong("message_id"));
                mess.setMessage_text(rs.getString("message_text"));
                mess.setPosted_by(rs.getInt("posted_by"));
                mess.setTime_posted_epoch(rs.getLong("time_posted_epoch"));
                return mess;
            } else {
                return null;
            } 
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Message> getAllMessages() {

        List<Message> out = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM message;";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement2.executeQuery();

            while (rs.next()) {
                Message message = new Message();
                message.setMessage_id((int)rs.getLong("message_id"));
                message.setMessage_text(rs.getString("message_text"));
                message.setPosted_by(rs.getInt("posted_by"));
                message.setTime_posted_epoch(rs.getLong("time_posted_epoch"));
                out.add(message);
            }
            return out;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Message getMessageById(int message_id) {

        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM message WHERE message_id=?;";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
            preparedStatement2.setInt(1, message_id);
            ResultSet rs = preparedStatement2.executeQuery();
            Message message = new Message();
            if (rs.next()) {
                message.setMessage_id((int)rs.getLong("message_id"));
                message.setMessage_text(rs.getString("message_text"));
                message.setPosted_by(rs.getInt("posted_by"));
                message.setTime_posted_epoch(rs.getLong("time_posted_epoch"));
                return message;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Message deleteMessageById(int message_id) {

        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM message WHERE message_id=?;";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
            preparedStatement2.setInt(1, message_id);
            ResultSet rs = preparedStatement2.executeQuery();
            Message message = new Message();
            if (rs.next()) {
                message.setMessage_id((int)rs.getLong("message_id"));
                message.setMessage_text(rs.getString("message_text"));
                message.setPosted_by(rs.getInt("posted_by"));
                message.setTime_posted_epoch(rs.getLong("time_posted_epoch"));

                String sql2 = "DELETE FROM message WHERE message_id=?;";
                PreparedStatement preparedStatement3 = connection.prepareStatement(sql2);
                preparedStatement3.setInt(1, message_id);
                preparedStatement3.executeUpdate();
                return message;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Message updateMessageById(int message_id, Message message) {

        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM message WHERE message_id=?;";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
            preparedStatement2.setInt(1, message_id);
            ResultSet rs = preparedStatement2.executeQuery();
            Message updatedMessage = new Message();
            if (rs.next()) {
                updatedMessage.setMessage_id((int)rs.getLong("message_id"));
                updatedMessage.setMessage_text(message.getMessage_text());
                updatedMessage.setPosted_by(rs.getInt("posted_by"));
                updatedMessage.setTime_posted_epoch(rs.getLong("time_posted_epoch"));

                String sql2 = "UPDATE message SET message_text=? WHERE message_id=?;";
                PreparedStatement preparedStatement3 = connection.prepareStatement(sql2);
                preparedStatement3.setString(1, message.getMessage_text());
                preparedStatement3.setInt(2, message_id);
                preparedStatement3.executeUpdate();
                return updatedMessage;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Message> getAllMessagesByAccountId(int posted_by) {
       List<Message> out = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM message WHERE posted_by=?;";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
            preparedStatement2.setInt(1, posted_by);
            ResultSet rs = preparedStatement2.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                message.setMessage_id((int)rs.getLong("message_id"));
                message.setMessage_text(rs.getString("message_text"));
                message.setPosted_by(rs.getInt("posted_by"));
                message.setTime_posted_epoch(rs.getLong("time_posted_epoch"));
                out.add(message);
            }
            return out;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}

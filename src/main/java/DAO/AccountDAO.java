package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Account;
import Model.Message;
import Util.ConnectionUtil;

public class AccountDAO {

    public Account createNewAccount(Account account) {

        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "select * from account where username=?;";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
            preparedStatement1.setString(1, account.getUsername());
            ResultSet rs = preparedStatement1.executeQuery();
            if (rs.next()) {
                return null;
            } else {

                String sql2 = "insert into account(username, password) values (?,?);";
                PreparedStatement preparedStatement2 =  connection.prepareStatement(sql2);
                preparedStatement2.setString(1, account.getUsername());
                preparedStatement2.setString(2, account.getPassword());
                preparedStatement2.executeUpdate();

                String sql3 = "select * from account where username=?;";
                PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
                preparedStatement3.setString(1, account.getUsername());
                ResultSet pkResultSet = preparedStatement3.executeQuery();
                if (pkResultSet.next()) {
                    Account newAccount = new Account();
                    newAccount.setAccount_id((int) pkResultSet.getLong(1));
                    newAccount.setUsername(pkResultSet.getNString(2));
                    newAccount.setPassword(pkResultSet.getNString(3));
                    return newAccount;
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            return null;
        }
    }

    public Account verifyLogin(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {

            String sql = "select * from account where username=? and password=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Account verifyAccount = new Account();
                verifyAccount.setAccount_id((int) rs.getLong("account_id"));
                verifyAccount.setUsername(rs.getString("username"));
                verifyAccount.setPassword(rs.getString("password"));
                return verifyAccount;
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    public Message createNewMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "select * from account where account_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message.getPosted_by());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return message;
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }
    


}

package Service;

import java.util.List;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    private MessageDAO messageDAO;
    private AccountDAO accountDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO) {
        this.messageDAO = messageDAO;
        this.accountDAO = accountDAO;
    }

    public Message createNewMessage(Message message) {
        if (message.message_text == null || message.message_text.length() <= 0 || message.message_text.length() >= 255) return null;
        if (accountDAO.createNewMessage(message) == null) return null;
        return messageDAO.createNewMessage(message);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    public Message deleteMessageById(int message_id) {
        return messageDAO.deleteMessageById(message_id);
    }

    public Message updateMessageById(int message_id, Message message) {
        if (message.message_text == null || message.message_text.length() <= 0 || message.message_text.length() >= 255) return null;
        if (getMessageById(message_id) == null) {
            return null;
        } else {
            return messageDAO.updateMessageById(message_id, message);
        }
    }

    public List<Message> getAllMessagesByAccountId(int userAccount) {
        return messageDAO.getAllMessagesByAccountId(userAccount);
    }


}

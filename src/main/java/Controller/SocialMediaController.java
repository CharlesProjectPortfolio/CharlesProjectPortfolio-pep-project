package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */

    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);

        app.post("/register", this::createNewAccount);
        app.post("/login", this::verifyLogin);
        app.post("/messages", this::createNewMessage);
        app.get("/messages", this::getAllMessages);
        app.get("/messages/{message_id}", this::getMessageById);
        app.delete("/messages/{message_id}", this::deleteMessageById);
        app.patch("/messages/{message_id}", this::updateMessageById);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByAccountId);
        
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    private void createNewAccount(Context ctx) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Account account = objectMapper.readValue(ctx.body(), Account.class);
        Account newAccount = accountService.createNewAccount(account);
        if (newAccount == null) {
            ctx.status(400);
        } else {
            ctx.json(newAccount).status(200);
        }
    }

    private void verifyLogin(Context ctx) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Account account = objectMapper.readValue(ctx.body(), Account.class);
        Account verifyAccount = accountService.verifyLogin(account);
        if (verifyAccount == null) {
            ctx.status(401);
        } else {
            ctx.json(verifyAccount).status(200);
        }
    }

    private void createNewMessage(Context ctx) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Message message = objectMapper.readValue(ctx.body(), Message.class);
        Message createMessage = messageService.createNewMessage(message);
        if (createMessage == null) {
            ctx.status(400);
        } else {
            ctx.json(createMessage).status(200);
        }
    }

    private void getAllMessages(Context ctx) throws JsonProcessingException{
        ctx.json(messageService.getAllMessages()).status(200);
    }

    private void getMessageById(Context ctx) throws JsonProcessingException{
        int message = Integer.parseInt(ctx.pathParam("message_id"));
        Message createMessage = messageService.getMessageById(message);
        if (createMessage == null) {
            ctx.status(200);
        } else {
            ctx.json(createMessage).status(200);
        }
    }

    private void deleteMessageById(Context ctx) throws JsonProcessingException{
        int message = Integer.parseInt(ctx.pathParam("message_id"));
        Message deleteMessage = messageService.deleteMessageById(message);
        if (deleteMessage == null) {
            ctx.status(200);
        } else {
            ctx.json(deleteMessage).status(200);
        }
    }

    private void updateMessageById(Context ctx) throws JsonProcessingException{
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        ObjectMapper objectMapper = new ObjectMapper();
        Message message = objectMapper.readValue(ctx.body(), Message.class);
        Message updateMessage = messageService.updateMessageById(message_id, message);
        if (updateMessage == null) {
            ctx.status(400);
        } else {
            ctx.json(updateMessage).status(200);
        }
    }

    private void getAllMessagesByAccountId(Context ctx) throws JsonProcessingException{
        int userAccount = Integer.parseInt(ctx.pathParam("account_id"));
        ctx.json(messageService.getAllMessagesByAccountId(userAccount)).status(200);
    }


}
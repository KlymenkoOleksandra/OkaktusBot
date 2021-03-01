import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;

public class Bot  extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            telegramBotsApi.registerBot(new Bot());
        }
        catch(TelegramApiRequestException e){
            e.printStackTrace();

        }
    }
public void sendMsg(Message message, String text){
    SendMessage sendMessage= new SendMessage();
    sendMessage.enableMarkdown(true);
    sendMessage.setChatId(message.getChatId().toString());
    sendMessage.setReplyToMessageId(message.getMessageId());
    sendMessage.setText(text);
    try{
        setButtons(sendMessage);
        execute(sendMessage);

    } catch (TelegramApiException e) {
        e.printStackTrace();
    }
}
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(message !=null && message.hasText()){
            switch(message.getText()){
                case "/help": case "Помощь":
                    sendMsg(message, "Чем могу вам помочь, сударь?");
                    break;
                case "/settings": case "Настройки":
                    sendMsg(message, "Что будем настраивать, сударь?)");
                    break;
                case "/start": case "Старт":
                    sendMsg(message, "Приветствую вас, господин! Я могу помочь вам выбрать ресторан, какое блюдо вы изволите?");
                    break;
                case "О проекте":
                    sendMsg(message, "Великие создатели: @OKaktus, @sobachkasobachka, @AddanAenye, Виктор, Юра. <3");
                    break;
                case "Поддержать проект)":
                    sendMsg(message, "Тут должна быть ссылка на обратную связь, мы работаем над этим...");
                    break;
                default:
                    sendMsg(message, "О, сударь, прошу мня простить! Я еще слишком глуп, чтобы вас понять!");
                    break;

            }
        }

    }

    public void setButtons(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keybordFirstRow = new KeyboardRow();

        keybordFirstRow.add(new KeyboardButton("Старт"));

        KeyboardRow keybordSecondRow = new KeyboardRow();

        keybordSecondRow.add(new KeyboardButton("Помощь"));
        keybordSecondRow.add(new KeyboardButton("Настройки"));
        keybordSecondRow.add(new KeyboardButton("О проекте"));

        KeyboardRow keybordThirdRow = new KeyboardRow();

        keybordSecondRow.add(new KeyboardButton("Поддержать проект)"));

        keyboardRowList.add(keybordFirstRow);
        keyboardRowList.add(keybordSecondRow);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }


    @Override
    public String getBotUsername() {
        return "OkaktusBot";
    }

    @Override
    public String getBotToken() {
        return "1449209300:AAG5dy7qX8aeD1mzahZm8UqbtrquPUjCOSI";
    }
}

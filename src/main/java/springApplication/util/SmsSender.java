package springApplication.util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSender {
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "AC5c4be43a2d76946c142643a10158fdea";
    public static final String AUTH_TOKEN = "9cd9f1017aa1f79e9ec22e139bbf8749";

    public static void sendSms(String password) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+40736912280"),
                new PhoneNumber("+13344543872"),
                "Welcome to PetBox" + " " + password).create();

        System.out.println(message.getSid());
    }
}
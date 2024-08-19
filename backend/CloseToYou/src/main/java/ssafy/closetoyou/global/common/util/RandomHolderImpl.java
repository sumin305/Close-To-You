package ssafy.closetoyou.global.common.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomHolderImpl implements RandomHolder {

    @Override
    public int getRandomEmailAuthenticateCode() {
        return (int)(Math.random() * (90000)) + 100000;
    }

    @Override
    public String getRandomClosetCode() {
        final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWYZ0123456789";
        final int ALPHANUMERIC_LENGTH = ALPHANUMERIC.length();
        final int TOTAL_LENGTH = 6;
        StringBuilder randomCode = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int randomNum = (int) (Math.random() * ALPHANUMERIC_LENGTH);
            randomCode.append(ALPHANUMERIC.charAt(randomNum));
        }
        return randomCode.toString();
    }
}

package io.github.shadowchild.growlithe.utils;


import io.github.shadowchild.growlithe.Growlithe;

/**
 * Created by Zach Piddock on 15/02/2016.
 */
// TODO: Implement more utils and rename
public class CommandUtils {

    public static void sendNotice(String user, String message) {

        Growlithe.bot.sendIRC().notice(user, message);
    }
}

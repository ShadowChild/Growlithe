package io.github.shadowchild.growlithe;


import io.github.shadowchild.growlithe.bot.CommandListener;
import io.github.shadowchild.growlithe.bot.GrowlitheBot;
import io.github.shadowchild.growlithe.parsers.ArgumentParser;
import io.github.shadowchild.growlithe.parsers.CommandListParser;
import io.github.shadowchild.growlithe.parsers.ProfileParser;
import io.github.shadowchild.growlithe.utils.ConfigHandler;


/**
 * Created by Zach Piddock on 17/12/2015.
 */
public class Growlithe {

    public static GrowlitheBot bot;
    public static CommandListener listener;

    public static void main(String... args) {

        new ArgumentParser().parseMultiple(args);

        ConfigHandler.handle();
        listener = new CommandListener();
        CommandListParser.rebuildCommandList();
        ProfileParser.parse();
    }
}

package io.github.shadowchild.growlithe;


import io.github.shadowchild.growlithe.bot.GrowlitheBot;
import io.github.shadowchild.growlithe.parsers.ArgumentParser;
import io.github.shadowchild.growlithe.parsers.ProfileParser;


/**
 * Created by Zach Piddock on 17/12/2015.
 */
public class Growlithe {

    public static GrowlitheBot bot;

    public static void main(String... args) {

        new ArgumentParser().parse(args);

        ProfileParser.parse();
    }
}

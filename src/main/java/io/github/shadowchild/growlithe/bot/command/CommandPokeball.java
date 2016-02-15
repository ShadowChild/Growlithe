package io.github.shadowchild.growlithe.bot.command;


import io.github.shadowchild.growlithe.Growlithe;
import io.github.shadowchild.growlithe.utils.EnumPermission;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.Map;

/**
 * Created by Zach Piddock on 18/12/2015.
 */
public class CommandPokeball implements ICommand {

    @Override
    public void onMessage(MessageEvent<? extends PircBotX> evt, String[] processedArgs) {

        evt.respond("Returning to my Pokeball master!");
        Growlithe.bot.shutdown();
    }

    @Override
    public EnumPermission getPermissionLevel(Map<String, String> overwrittenPermissions) {

        return EnumPermission.SUPER_ADMIN;
    }

    @Override
    public String getUsage() {

        return "shutdown";
    }

    @Override
    public String[] getHelpMessage() {

        return new String[] {
                "Shuts down the instance of " + Growlithe.bot.getNick()
        };
    }
}

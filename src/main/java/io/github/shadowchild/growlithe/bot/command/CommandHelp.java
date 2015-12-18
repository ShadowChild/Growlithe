package io.github.shadowchild.growlithe.bot.command;


import io.github.shadowchild.growlithe.utils.EnumPermission;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.Map;

/**
 * Created by Zach Piddock on 18/12/2015.
 */
public class CommandHelp implements ICommand {

    @Override
    public void onMessage(MessageEvent evt, String[] processedArgs) {

        System.out.println("Help");
    }

    @Override
    public EnumPermission getPermissionLevel(Map<String, String> overwrittenPermissions) {

        return EnumPermission.FIRE_STONE;
    }

    @Override
    public String getUsage() {

        return "-growlithe help";
    }
}

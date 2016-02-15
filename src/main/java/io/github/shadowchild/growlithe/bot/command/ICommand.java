package io.github.shadowchild.growlithe.bot.command;


import io.github.shadowchild.growlithe.utils.EnumPermission;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.Map;

/**
 * Created by Zach Piddock on 18/12/2015.
 */
public interface ICommand {

    void onMessage(MessageEvent<? extends PircBotX> evt, String[] processedArgs);

    EnumPermission getPermissionLevel(Map<String, String> overwrittenPermissions);

    String getUsage();

    String[] getHelpMessage();
}

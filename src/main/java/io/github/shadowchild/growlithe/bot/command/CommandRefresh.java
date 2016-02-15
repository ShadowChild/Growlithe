package io.github.shadowchild.growlithe.bot.command;


import io.github.shadowchild.growlithe.parsers.CommandListParser;
import io.github.shadowchild.growlithe.utils.EnumPermission;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.Map;

/**
 * Created by Zach Piddock on 11/02/2016.
 */
public class CommandRefresh implements ICommand {

    @Override
    public void onMessage(MessageEvent<? extends PircBotX> evt, String[] processedArgs) {

        switch(processedArgs[0]) {

            case "cmds": {

                evt.respond("Rebuilding my command list, please wait...");
                CommandListParser.rebuildCommandList();
                evt.respond("Rebuilding of commands finished, you may now use commands again!");
                break;
            }
        }
    }

    @Override
    public EnumPermission getPermissionLevel(Map<String, String> overwrittenPermissions) {

        return EnumPermission.SUPER_ADMIN;
    }

    @Override
    public String getUsage() {

        return "refresh <cmds/settings>";
    }

    @Override
    public String[] getHelpMessage() {

        return new String[] {
                "Refreshes the Command List or the Settings File"
        };
    }
}

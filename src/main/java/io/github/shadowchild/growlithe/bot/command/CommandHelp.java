package io.github.shadowchild.growlithe.bot.command;


import io.github.shadowchild.growlithe.Growlithe;
import io.github.shadowchild.growlithe.bot.CommandListener;
import io.github.shadowchild.growlithe.utils.CommandUtils;
import io.github.shadowchild.growlithe.utils.EnumPermission;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.Map;
import java.util.Set;

/**
 * Created by Zach Piddock on 18/12/2015.
 */
public class CommandHelp implements ICommand {

    @Override
    public void onMessage(MessageEvent<? extends PircBotX> evt, String[] processedArgs) {

        Set<Map.Entry<String, ICommand>> entrySet = CommandListener.getCommandRegistry().registry.entrySet();
        String user = evt.getUser().getNick();

        if(processedArgs.length > 0 && processedArgs[0].equalsIgnoreCase("syn")) {

            CommandUtils.sendNotice(user, "//***************//");
            entrySet.forEach(e -> {

                String messagePrepend = e.getKey() + ": ";
                CommandUtils.sendNotice(user, messagePrepend + "-" + Growlithe.bot.getNick().toLowerCase() + " " + e.getValue().getUsage());
            });
            CommandUtils.sendNotice(user, "//***************//");
        } else {

            CommandUtils.sendNotice(user, "//***************//");
            entrySet.forEach(e -> {

                String messagePrepend = e.getKey() + ": ";
                for(String line : e.getValue().getHelpMessage()) {

                    CommandUtils.sendNotice(user, messagePrepend + line);
                }
            });
            CommandUtils.sendNotice(user, "//***************//");
        }
    }

    @Override
    public EnumPermission getPermissionLevel(Map<String, String> overwrittenPermissions) {

        return EnumPermission.FIRE_STONE;
    }

    @Override
    public String getUsage() {

        return "help";
    }

    @Override
    public String[] getHelpMessage() {

        return new String[] {
                "Displays this help menu"
        };
    }
}

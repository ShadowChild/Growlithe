package io.github.shadowchild.growlithe.bot;


import io.github.shadowchild.cybernize.reg.NamedRegistry;
import io.github.shadowchild.growlithe.bot.command.ICommand;
import io.github.shadowchild.growlithe.parsers.CommandListParser;

import org.apache.commons.lang3.StringUtils;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zach Piddock on 18/12/2015.
 */
public class CommandListener extends ListenerAdapter<GrowlitheBot> {

    // String is the command alias
    private static NamedRegistry<ICommand> commandRegistry = new NamedRegistry<>();

    public CommandListener() {

//        commandMap.put("help", new CommandHelp());
//        commandMap.put("pokeball", new CommandPokeball());
    }

    @Override
    public void onMessage(MessageEvent<GrowlitheBot> event) throws Exception {

        String message = event.getMessage().toLowerCase();

        if(StringUtils.startsWith(message, "-" + event.getBot().getNick().toLowerCase())) {

            // TODO: FIX THIS - I'VE FORGOTTEN WHAT'S EVEN BROKEN
            String[] args = message.replace("-" + event.getBot().getNick().toLowerCase() + " ", "").split(" ");
            String command = args[0];

            if(command.equalsIgnoreCase("refreshcmds")) {
            	
            	CommandListParser.rebuildCommandList();
            	event.respond("Rebuilding my command list, please wait about 60 seconds");
            	return;
            }
            
            System.out.println(command);

            Object obj = commandRegistry.getObject(command);
            if(obj != null) {

                ((ICommand)obj).onMessage(event, args);
            } else {

                event.respond("Invalid Command Entered");
            }
        }
    }

    public static void addCommand(String name, ICommand command) {

        commandRegistry.register(name, command);
    }

    public static void removeCommand(String name) {

    	commandRegistry.remove(name);
    }

    public static NamedRegistry<ICommand> getCommandRegistry() {

        return commandRegistry;
    }
}

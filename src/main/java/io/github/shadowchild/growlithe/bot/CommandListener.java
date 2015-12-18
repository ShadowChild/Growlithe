package io.github.shadowchild.growlithe.bot;


import io.github.shadowchild.growlithe.bot.command.CommandHelp;
import io.github.shadowchild.growlithe.bot.command.CommandPokeball;
import io.github.shadowchild.growlithe.bot.command.ICommand;
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
    public static Map<String, ICommand> commandMap = new HashMap<>();

    public CommandListener() {

        commandMap.put("help", new CommandHelp());
        commandMap.put("pokeball", new CommandPokeball());
    }

    @Override
    public void onMessage(MessageEvent<GrowlitheBot> event) throws Exception {

        String message = event.getMessage().toLowerCase();

        if(StringUtils.startsWith(message, "-" + event.getBot().getNick().toLowerCase())) {

            // TODO: FIX THIS
            String[] args = message.replace("-" + event.getBot().getNick().toLowerCase() + " ", "").split(" ");
            String command = args[0];

            System.out.println(command);

            if(commandMap.containsKey(command)) {

                commandMap.get(command).onMessage(event, args);
            } else {

                event.respond("Invalid Command Entered");
            }
        }
    }
}

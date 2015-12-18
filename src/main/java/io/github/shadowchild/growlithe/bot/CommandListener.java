package io.github.shadowchild.growlithe.bot;


import io.github.shadowchild.growlithe.bot.command.CommandHelp;
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
    }

    @Override
    public void onMessage(MessageEvent<GrowlitheBot> event) throws Exception {

        if(StringUtils.startsWithIgnoreCase(event.getMessage(), "-" + event.getBot().getNick())) {

            // TODO: FIX THIS
            String[] args = event.getMessage().replace("-" + event.getBot().getNick() + " ", "").split(" ");
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

package io.github.shadowchild.growlithe.bot;


import io.github.shadowchild.cybernize.reg.NamedRegistry;
import io.github.shadowchild.growlithe.bot.command.ICommand;
import org.apache.commons.lang3.StringUtils;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by Zach Piddock on 18/12/2015.
 */
public class CommandListener extends ListenerAdapter<GrowlitheBot> {

    // String is the command alias
    private static NamedRegistry<ICommand> commandRegistry = new NamedRegistry<>();

    public CommandListener() {

        commandRegistry.setIgnoreValueOnRegister(true);
//        commandMap.put("help", new CommandHelp());
//        commandMap.put("pokeball", new CommandPokeball());
    }

    @Override
    public void onMessage(MessageEvent<GrowlitheBot> event) throws Exception {

        String message = event.getMessage().toLowerCase();
        String commandString = "-" + event.getBot().getNick().toLowerCase();

        if(StringUtils.startsWith(message, commandString)) {

            // TODO: FIX THIS - I'VE FORGOTTEN WHAT'S EVEN BROKEN
            String[] args = message.replace(commandString + " ", "").split(" ");
            String command = args[0];

            String[] processedArgs = new String[args.length - 1];
            System.arraycopy(args, 1, processedArgs, 0, args.length - 1);

            Object obj = commandRegistry.getObject(command);
            if(obj != null) {

                ((ICommand)obj).onMessage(event, processedArgs);
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

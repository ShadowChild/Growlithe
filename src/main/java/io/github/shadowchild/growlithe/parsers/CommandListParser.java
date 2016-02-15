package io.github.shadowchild.growlithe.parsers;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import io.github.shadowchild.growlithe.Growlithe;
import io.github.shadowchild.growlithe.bot.CommandListener;
import io.github.shadowchild.growlithe.bot.command.CommandHelp;
import io.github.shadowchild.growlithe.bot.command.CommandPokeball;
import io.github.shadowchild.growlithe.bot.command.CommandRefresh;
import io.github.shadowchild.growlithe.bot.command.ICommand;
import io.github.shadowchild.growlithe.utils.Settings;

import java.io.InputStreamReader;
import java.io.InvalidObjectException;
import java.util.Map;

/**
 * Created by Zach Piddock on 08/02/2016.
 */
public class CommandListParser {

    private static void parse() {

        InputStreamReader reader = new InputStreamReader(Growlithe.class.getResourceAsStream("/assets/commands.json"));

        JsonReader jr = new JsonReader(reader);
        JsonParser jp = new JsonParser();
        JsonElement element = jp.parse(jr);
        
        JsonObject root = element.getAsJsonObject();
        JsonObject commands = root.getAsJsonObject("commands");

        for(Map.Entry<String, JsonElement> e : commands.entrySet()) {

            try {

                Class clazz = Class.forName(e.getValue().getAsString());
                Object obj = clazz.newInstance();
                if(!(obj instanceof ICommand))
                    throw new InvalidObjectException(String.format("Class %s does not extend ICommand", e.getValue().getAsString()));

                ICommand command = (ICommand)obj;
                CommandListener.addCommand(e.getKey(), command);
                System.out.printf("Registering Command '%s', class is '%s'", e.getKey(), e.getValue().getAsString());
                System.out.println();
            } catch(ClassNotFoundException | InstantiationException | IllegalAccessException | InvalidObjectException e1) {

                e1.printStackTrace();
            }
        }
    }
    
    public static void rebuildCommandList() {
    	
    	// clear the command list
    	CommandListener.getCommandRegistry().clearRegistry();
    	
    	// add default commands, if enabled in settings
        CommandListener.addCommand("refresh", new CommandRefresh());
    	if(Settings.helpCommandActive) CommandListener.addCommand("help", new CommandHelp());
    	if(Settings.shutdownCommandActive) {
    		
    		CommandPokeball pokeballCmd = new CommandPokeball();
    		CommandListener.addCommand("pokeball", pokeballCmd);
    		CommandListener.addCommand("shutdown", pokeballCmd);
    	}
    	
    	// parse commands now
    	parse();
    }
}

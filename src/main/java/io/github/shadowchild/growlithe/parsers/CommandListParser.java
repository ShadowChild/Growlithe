package io.github.shadowchild.growlithe.parsers;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import io.github.shadowchild.growlithe.Growlithe;
import io.github.shadowchild.growlithe.bot.CommandListener;
import io.github.shadowchild.growlithe.bot.command.ICommand;

import java.io.InputStreamReader;
import java.io.InvalidObjectException;
import java.util.Map;

/**
 * Created by Zach Piddock on 08/02/2016.
 */
public class CommandListParser {

    public static void parse() {

        InputStreamReader reader = new InputStreamReader(Growlithe.class.getResourceAsStream("/assets/commands.json"));

        JsonReader jr = new JsonReader(reader);
        JsonParser jp = new JsonParser();
        JsonElement element = jp.parse(jr);

        JsonObject object = element.getAsJsonObject().getAsJsonObject("commands");

        for(Map.Entry<String, JsonElement> e : object.entrySet()) {

            try {

                Class clazz = Class.forName(e.getValue().getAsString());
                Object obj = clazz.newInstance();
                if(!(obj instanceof ICommand))
                    throw new InvalidObjectException("Command does not extend ICommand");

                ICommand command = (ICommand)obj;
                CommandListener.addCommand(e.getKey(), command);
            } catch(ClassNotFoundException | InstantiationException | IllegalAccessException | InvalidObjectException e1) {

                e1.printStackTrace();
            }
            System.out.println(e.getKey());
            System.out.println(e.getValue().getAsString());
        }
    }
}

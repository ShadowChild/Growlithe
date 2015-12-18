package io.github.shadowchild.growlithe.parsers;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import io.github.shadowchild.growlithe.Growlithe;
import io.github.shadowchild.growlithe.bot.CommandListener;
import io.github.shadowchild.growlithe.bot.GrowlitheBot;
import org.pircbotx.Configuration;
import org.pircbotx.exception.IrcException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zach Piddock on 17/12/2015.
 */
public class ProfileParser {

    Map<String, List<String>> nickAliasMap = new HashMap<>();
    Map<String, List<String>> userPermissionMap = new HashMap<>();

    public static void parse() {

        parseProfile();
        parsePerms();
    }

    private static void parseProfile() {

        URL profileURL = Growlithe.class.getResource("/assets/profile.json");
        URL defaultURL = Growlithe.class.getResource("/assets/defaultProfile.json");

        File profile = null;
        try {

            profile = new File(profileURL.toURI());
        } catch(URISyntaxException e) {

            e.printStackTrace();
        }

        FileReader fr = null;
        try {

            fr = profileExists(profile) ? new FileReader(profile) : new FileReader(new File(defaultURL.toURI()));
        } catch(FileNotFoundException | URISyntaxException e) {

            e.printStackTrace();
        }
        JsonReader jr = new JsonReader(fr);

        JsonParser jp = new JsonParser();
        JsonElement element = jp.parse(jr);

        JsonObject object = element.getAsJsonObject().getAsJsonObject("profile");

        Configuration.Builder<GrowlitheBot> builder = new Configuration.Builder<>();

        builder.setName(object.get("nick").getAsString());
        builder.setServerHostname(object.get("server").getAsString());
        if(object.has("port")) builder.setServerPort(object.get("port").getAsInt());
        builder.setLogin(object.get("login").getAsString());
        if(object.has("password")) builder.setNickservPassword(object.get("password").getAsString());
        builder.setRealName(object.get("realName").getAsString());

        JsonArray array = object.getAsJsonArray("channels");
        array.forEach(jsonElement -> builder.addAutoJoinChannel(jsonElement.getAsString()));
        builder.addListener(new CommandListener());

        try {

            Growlithe.bot = new GrowlitheBot(builder.buildConfiguration());
            Growlithe.bot.startBot();
        } catch(IOException | IrcException e) {

            e.printStackTrace();
        }
    }

    private static boolean profileExists(File profile) {

        return profile.exists() && profile.isFile();
    }

    private static void parsePerms() {

    }
}

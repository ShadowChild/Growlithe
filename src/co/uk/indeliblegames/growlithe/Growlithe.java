package co.uk.indeliblegames.growlithe;

import co.uk.indeliblegames.growlithe.bot.GrowlitheBot;
import co.uk.indeliblegames.growlithe.gui.GrowlitheUI;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShadowChild.
 */
public class Growlithe {

    public static GrowlitheBot bot;
    public static GrowlitheUI ui;

    public static void main(String... args) {

        try {

            ui = new GrowlitheUI();
            bot = new GrowlitheBot(loadProfile());

        } catch (Exception e) {

            e.printStackTrace();
            System.exit(1);
        }
    }

    private static Map<String, Object> loadProfile() throws IOException {

        Map<String, Object> map = new HashMap<String, Object>();

        File profile = new File(".", "profile.json");
        BufferedReader br;
        if (profile.exists()) {

            br = new BufferedReader(new InputStreamReader(profile.toURI().toURL().openStream()));
        } else {

            br = new BufferedReader(new InputStreamReader(Growlithe.class.getResourceAsStream("/defaultProfile.json")));
        }
        JsonReader jsonReader = new JsonReader(br);

        jsonReader.beginObject();

        while (jsonReader.hasNext()) {

            String name = jsonReader.nextName();

            if (name != null) {

                if (name.equals("profile")) {

                    jsonReader.beginObject();

                    while (jsonReader.hasNext()) {

                        String n = jsonReader.nextName();

                        if (n.equals("nick")) {

                            map.put("nick", jsonReader.nextString());
                        }

                        if (n.equals("server")) {

                            map.put("server", jsonReader.nextString());
                        }

                        if (n.equals("port")) {

                            map.put("port", jsonReader.nextInt());
                        }

                        if (n.equals("realName")) {

                            map.put("realName", jsonReader.nextString());
                        }

                        if (n.equals("login")) {

                            map.put("login", jsonReader.nextString());
                        }

                        if (n.equals("channels")) {

                            ArrayList<String> list = new ArrayList<String>();

                            jsonReader.beginArray();

                            while (jsonReader.hasNext()) {

                                list.add(jsonReader.nextString());
                            }
                            jsonReader.endArray();
                            map.put("channels", list);
                        }
                        if (n.equals("password")) {

                            map.put("password", jsonReader.nextString());
                        }
                    }
                    jsonReader.endObject();
                }
            }
        }

        jsonReader.endObject();
        jsonReader.close();

        return map;
    }
}

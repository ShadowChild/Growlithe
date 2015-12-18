package io.github.shadowchild.growlithe.parsers;


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

    }

    private static void parsePerms() {

    }
}

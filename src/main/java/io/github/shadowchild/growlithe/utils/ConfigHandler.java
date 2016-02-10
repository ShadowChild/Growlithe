package io.github.shadowchild.growlithe.utils;

import io.github.shadowchild.cybernize.config.Config;
import io.github.shadowchild.cybernize.config.Config.Type;
import io.github.shadowchild.cybernize.config.Configuration;
import io.github.shadowchild.cybernize.config.Configuration.ConfigType;

public class ConfigHandler {

	public static void handle() {
		
		Config cfg = new Configuration(ConfigType.INI, "/cfg/General").load();
		
		Settings.canRestart = cfg.getBoolean("commands", "canRestart", true, null);
		Settings.helpCommandActive = cfg.getBoolean("commands", "helpCommandActive", true, null);
		Settings.shutdownCommandActive = cfg.getBoolean("commands", "shutdownCommandActive", true, null);
	}
}

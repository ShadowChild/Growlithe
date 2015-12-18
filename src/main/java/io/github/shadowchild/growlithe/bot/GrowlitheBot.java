package io.github.shadowchild.growlithe.bot;


import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

/**
 * Created by Zach Piddock on 18/12/2015.
 */
public class GrowlitheBot extends PircBotX {

    /**
     * Constructs a PircBotX with the provided configuration.
     *
     * @param configuration
     */
    public GrowlitheBot(Configuration<? extends PircBotX> configuration) {

        super(configuration);
    }
}

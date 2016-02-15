package io.github.shadowchild.growlithe.bot.command.vote;


import io.github.shadowchild.growlithe.bot.command.ICommand;
import io.github.shadowchild.growlithe.utils.EnumPermission;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.Map;

/**
 * Created by Zach Piddock on 15/02/2016.
 */
public class CommandVote implements ICommand {

    private Vote vote;

    @Override
    public void onMessage(MessageEvent<? extends PircBotX> evt, String[] processedArgs) {

        switch(processedArgs[0]) {

            case "new": {

                break;
            }
            case "finish": {

                break;
            }
            // in case of answer
            default: {

                break;
            }
        }
    }

    @Override
    public EnumPermission getPermissionLevel(Map<String, String> overwrittenPermissions) {

        return EnumPermission.FIRE_STONE;
    }

    @Override
    public String getUsage() {

        return "vote <new/finish/[option]> [option1] [option2] etc";
    }

    @Override
    public String[] getHelpMessage() {

        return new String[] {
                "Creates a vote that can be finished at any time, also can have as many options as you wish"
        };
    }
}

package io.github.shadowchild.growlithe.bot.command.vote;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.pircbotx.User;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Zach Piddock on 15/02/2016.
 */
public class Vote {

    public User owner;
    public List<String> options = Lists.newArrayList();
    private Map<User, String> userAnswerMap = Maps.newHashMap();

    public Vote(User owner, String... options) {

        this.owner = owner;
        Collections.addAll(this.options, options);
    }


}

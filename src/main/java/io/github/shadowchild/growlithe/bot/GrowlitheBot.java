package io.github.shadowchild.growlithe.bot;


import io.github.shadowchild.growlithe.Growlithe;
import org.pircbotx.User;
import org.pircbotx.exception.IrcException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


/**
 * @author ShadowChild.
 */
public class GrowlitheBot extends PircBot {

    private ArrayList<String> shadowNicks = new ArrayList<String>();
    public boolean isActive;

    public GrowlitheBot(Map<String, Object> map) {

        isActive = true;
        this.setVerbose(true);

        String server = "irc.esper.net";
        int port = 6667;
        ArrayList<String> channels = new ArrayList<String>();
        String password = "";

        for(Map.Entry<String, Object> entry : map.entrySet()) {

            switch(entry.getKey()) {

                case "nick": {

                    this.setName((String)entry.getValue());
                    break;
                }

                case "login": {

                    this.setLogin((String)entry.getValue());
                    break;
                }

                case "realName": {

                    this.setVersion((String)entry.getValue());
                    break;
                }

                case "server": {

                    server = (String)entry.getValue();
                    break;
                }

                case "port": {

                    port = (Integer)entry.getValue();
                    break;
                }

                case "channels": {

                    channels = (ArrayList<String>)entry.getValue();
                    break;
                }

                case "password": {

                    password = (String)entry.getValue();
                    break;
                }

                default:
                    break;
            }
        }

        try {

            this.connect(server, port);
            for(String channel : channels)
                this.joinChannel(channel);

            if(password != null && !password.isEmpty()) this.sendRawLine("NS identify " + password);
        } catch(IOException e) {

            e.printStackTrace();
        } catch(IrcException e) {

            e.printStackTrace();
        }

        shadowNicks.add("ShadowChild");
        shadowNicks.add("ShadowChild|*");
        shadowNicks.add("ShowerChild");

        Growlithe.ui.setVisible(true);
    }

    @Override
    protected void onJoin(String channel, String sender, String login, String hostname) {

        if(isActive && sender.equals(this.getNick())) this.sendMessage(channel, "Ohai there guys!");
    }

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message) {

        String[] split = message.split(" ");

        if(message.equalsIgnoreCase("Go " + this.getNick()) && !this.isActive) {

            this.isActive = true;
            this.sendMessage(channel, "Reactived bitches :D");
            return;
        }

        if(isActive) {

            if(message.contains(this.getNick()) && (split[0].equalsIgnoreCase("hi") || split[0].equalsIgnoreCase("hello"))) {

                if(isShadow(sender)) {

                    this.sendMessage(channel, "Hello Master");
                } else {

                    this.sendMessage(channel, "Hi " + sender);
                }
            } else if(message.equalsIgnoreCase(this.getNick() + " pokeball")) {

                for(User user : this.getUsers(channel)) {

                    if((user.getNick().equals(sender) && user.isOp()) || isShadow(sender)) {

                        this.isActive = false;
                        this.sendMessage(channel, "i hate you " + sender + " :'(");
                        return;
                    } else if(user.getNick().equals(sender) && !user.isOp()) {

                        this.sendMessage(channel, sender + ", http://i.imgur.com/36HTEnu.gif");
                    }
                }
            } else if(message.startsWith("!")) {

                String firstCommand = split[0].substring(1);

                if(!firstCommand.equalsIgnoreCase(this.getNick().toLowerCase())) {

                    return;
                }

                String secondCommand = split[1];
                switch(secondCommand) {

                    case "join": {

                        this.joinChannel(split[2]);
                        break;
                    }

                    case "help": {

                        this.sendMessage(channel, "NYI");
                        break;
                    }

                    case "rename": {

                        this.changeNick(split[2]);
                        break;
                    }
                }
            }
        }
    }

    private boolean isShadow(String sender) {

        for(String nick : shadowNicks) {

            if(nick.contains("*") && sender.contains("|")) {

                return nick.substring(0, nick.length() - 1).equals(sender.substring(0, nick.length() - 1));
            }
        }
        return shadowNicks.contains(sender);
    }

    @Override
    protected void onPrivateMessage(String sender, String login, String hostname, String message) {

        if(isActive) sendPrivateMessage(sender, "Screw you, talk to me in one of the channels i'm in");
    }

    @Override
    protected void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {

        if(isActive) this.joinChannel(channel);
    }

    private void sendPrivateMessage(String sender, String message) {

        this.sendRawLine("PRIVMSG " + sender + " " + message);
    }
}

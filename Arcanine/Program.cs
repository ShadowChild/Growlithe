using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using Meebey.SmartIrc4net;
using System.Threading;
using Arcanine.Command;

namespace Arcanine
{
    class Program
    {
        // make an instance of the high-level API
        public static IrcClient irc = new IrcClient();

        public static void Main(string[] args)
        {

            // first thing we do, load settings and command list
            CommandLoader.loadCommands();

            // Set thread name
            Thread.CurrentThread.Name = "Main";

            // UTF-8 test
            irc.Encoding = Encoding.UTF8;

            // wait time between messages, we can set this lower on own irc servers
            irc.SendDelay = 200;

            // we use channel sync, means we can use irc.GetChannel() and so on
            irc.ActiveChannelSyncing = true;

            // here we connect the events of the API to our written methods
            // most have own event handler types, because they ship different data
            irc.OnQueryMessage += new IrcEventHandler(CommandListener.OnQueryMessage);
            irc.OnError += new ErrorEventHandler(CommandListener.OnError);
            irc.OnRawMessage += new IrcEventHandler(CommandListener.OnRawMessage);
            irc.OnChannelMessage += new IrcEventHandler(CommandListener.OnGenericMessage);

            string[] serverlist;
            // the server we want to connect to, could be also a simple string
            serverlist = new string[] { "irc.esper.net" };
            int port = 6667;
            string channel = "#ShadowChild";
            try
            {
                // here we try to connect to the server and exceptions get handled
                irc.Connect(serverlist, port);
            }
            catch (ConnectionException e)
            {
                // something went wrong, the reason will be shown
                Console.WriteLine("couldn't connect! Reason: " + e.Message);
                Exit();
            }

            try
            {
                // here we logon and register our nickname and so on 
                irc.Login("Growlithe", "Growlithe Bot");
                // join the channel
                irc.RfcJoin(channel);

                // spawn a new thread to read the stdin of the console, this we use
                // for reading IRC commands from the keyboard while the IRC connection
                // stays in its own thread
                new Thread(new ThreadStart(ReadCommands)).Start();

                // here we tell the IRC API to go into a receive mode, all events
                // will be triggered by _this_ thread (main thread in this case)
                // Listen() blocks by default, you can also use ListenOnce() if you
                // need that does one IRC operation and then returns, so you need then 
                // an own loop 
                irc.Listen();

                // when Listen() returns our IRC session is over, to be sure we call
                // disconnect manually
                irc.Disconnect();
            }
            catch (ConnectionException)
            {
                // this exception is handled because Disconnect() can throw a not
                // connected exception
                Exit();
            }
            catch (Exception e)
            {
                // this should not happen by just in case we handle it nicely
                Console.WriteLine("Error occurred! Message: " + e.Message);
                Console.WriteLine("Exception: " + e.StackTrace);
                Exit();
            }
        }

        public static void ReadCommands()
        {
            // here we read the commands from the stdin and send it to the IRC API
            // WARNING, it uses WriteLine() means you need to enter RFC commands
            // like "JOIN #test" and then "PRIVMSG #test :hello to you"
            while (true)
            {
                string cmd = Console.ReadLine();
            }
        }

        public static void Exit()
        {
            // we are done, lets exit...
            Console.WriteLine("Exiting...");
            Environment.Exit(0);
        }

        
    }
}

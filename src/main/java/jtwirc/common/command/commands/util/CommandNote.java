package jtwirc.common.command.commands.util;

import jtwirc.common.command.CommandBase;
import jtwirc.todo.ChirpBot;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.MessageSending;

public class CommandNote extends CommandBase
{
    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            boolean noteAdded = false;
            try
            {
                long note = (long) ChirpBot.noteList.size() + 1;
                while (!noteAdded)
                {
                    if (!ChirpBot.noteList.containsKey(note))
                    {
                        ChirpBot.noteList.put(note, msgWOCommand);
                        noteAdded = true;
                        MessageSending.sendWhisper(user.getName().toLowerCase(), " note has been added as #" + note + ".");
                        ChirpBot.log.info("note " + note + " has been added");
                    }
                    else
                    {
                        note += 1;
                        noteAdded = false;
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            finally
            {
                ChirpBot.saveAllTheThings();
            }
        }
    }
}

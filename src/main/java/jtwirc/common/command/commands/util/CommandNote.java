package jtwirc.common.command.commands.util;

import jtwirc.TwircBot;
import jtwirc.common.command.CommandBase;
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
                long note = (long) TwircBot.noteList.size() + 1;
                while (!noteAdded)
                {
                    if (!TwircBot.noteList.containsKey(note))
                    {
                        TwircBot.noteList.put(note, msgWOCommand);
                        noteAdded = true;
                        MessageSending.sendWhisper(user.getName().toLowerCase(), " note has been added as #" + note + ".");
                        TwircBot.log.info("note " + note + " has been added");
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
                TwircBot.saveAllTheThings();
            }
        }
    }
}

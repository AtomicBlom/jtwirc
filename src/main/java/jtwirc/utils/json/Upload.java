package jtwirc.utils.json;

import jtwirc.TwircBot;
import jtwirc.utils.Defaults;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Upload
{

    private static String host = ""; //FTP HOST
    private static String user = ""; //FTP USER
    private static String pass = ""; //FTP PASSWORD
    private static String uploadPath = "/public_html/api/channel/";

    private static String[] files = new String[]{"songRequestList", "data", "subMessages", "scheduleList", "rankList", "commandpermissions", "commands", "permissions", "ranks", "points", "note", "quotes", "raffleTickets"};
    private static String[] raffleFiles = new String[]{"data", "subMessages", "raffleTickets"};

    private static void showServerReply(FTPClient ftpClient)
    {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0)
        {
            for (String aReply : replies)
            {
                TwircBot.log.info("SERVER: " + aReply);
            }
        }
    }

    public static void uploadFiles()
    {
        TwircBot.log.info("Uploading files to FTP.");
        try
        {
            FTPClient ftpClient = new FTPClient();
            try
            {
                ftpClient.connect(host);
                boolean login = ftpClient.login(user, pass);
                if (login)
                {
                    ftpClient.enterLocalPassiveMode();
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                    ftpClient.makeDirectory(uploadPath + "" + TwircBot.config.getProperty("autoJoinChannel"));
                    showServerReply(ftpClient);

                    for (String file : files)
                    {
                        try
                        {
                            File upload = new File("config/" + file + ".json");

                            InputStream inputStream = new FileInputStream(upload);
                            OutputStream outputStream = ftpClient.storeFileStream(uploadPath + TwircBot.config.getProperty("autoJoinChannel") + "/" + file + ".json");
                            byte[] bytesIn = new byte[4096];
                            int read;

                            while ((read = inputStream.read(bytesIn)) != -1)
                            {
                                outputStream.write(bytesIn, 0, read);
                            }
                            inputStream.close();
                            outputStream.close();

                            boolean completed = ftpClient.completePendingCommand();
                            if (completed)
                            {
                                inputStream.close();
                            }
                        }
                        catch (Exception ignored)
                        {
                        }
                    }
                    ftpClient.logout();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            TwircBot.log.info("Finished uploading files to FTP.");
        }
    }

    public static void uploadDataFiles()
    {
        TwircBot.log.info("Uploading raffle files to FTP.");
        try
        {
            FTPClient ftpClient = new FTPClient();
            try
            {
                ftpClient.connect(host);
                boolean login = ftpClient.login(user, pass);
                if (login)
                {
                    ftpClient.enterLocalPassiveMode();
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                    for (String file : raffleFiles)
                    {
                        File upload = new File("config/" + file + ".json");

                        InputStream inputStream = new FileInputStream(upload);
                        OutputStream outputStream = ftpClient.storeFileStream(uploadPath + Defaults.getStreamer() + "/" + file + ".json");
                        byte[] bytesIn = new byte[4096];
                        int read;

                        while ((read = inputStream.read(bytesIn)) != -1)
                        {
                            outputStream.write(bytesIn, 0, read);
                        }
                        inputStream.close();
                        outputStream.close();

                        boolean completed = ftpClient.completePendingCommand();
                        if (completed)
                        {
                            inputStream.close();
                        }
                    }
                    ftpClient.logout();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            TwircBot.log.info("Finished uploading raffle files to FTP.");
        }
    }
}

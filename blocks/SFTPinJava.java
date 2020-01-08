package blocks;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * @author https://kodehelp.com
 *
 */
public class SFTPinJava {

    private String SFTPHOST = "127.0.0.1";
    private int SFTPPORT = 5027;
    private String SFTPUSER = "pi";
    private String SFTPPASS = "r2d2";
    private String SFTPWORKINGDIR = "/home/pi/";//"/Users/pgb15001/TALKS/CCWC-2020/CCWC-workshop/";//
    private static final String dir_FILETOTRANSFER = "/Users/pgb15001/TALKS/CCWC-2020/CCWC-workshop/";

	/**
     * @param args
     */
    public void sendFile(String sendFileName) {


        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;

        try {
            JSch jsch = new JSch();
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            session.setPassword(SFTPPASS);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(SFTPWORKINGDIR);
            File f = new File(sendFileName);
            channelSftp.put(new FileInputStream(f), f.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Done");
    }
    
}

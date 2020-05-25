package com.project.qa.ui.utils;

import com.xebialabs.overthere.CmdLine;
import com.xebialabs.overthere.ConnectionOptions;
import com.xebialabs.overthere.Overthere;
import com.xebialabs.overthere.OverthereConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.xebialabs.overthere.ConnectionOptions.*;
import static com.xebialabs.overthere.OperatingSystemFamily.UNIX;
import static com.xebialabs.overthere.ssh.SshConnectionBuilder.CONNECTION_TYPE;
import static com.xebialabs.overthere.ssh.SshConnectionType.SFTP;

public class RemoteShellRunner {
    static final Logger LOGGER = LoggerFactory.getLogger(RemoteShellRunner.class);
    private String host;
    private String userName;
    private String password;

    public RemoteShellRunner(String host, String userName, String password) {
        this.host = host;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Method to execute shell command on remote linux machine
     * @param cmdName
     * @param cmd
     * @param args
     * @throws Exception
     */
    public void runRemoteShellCommand(String cmdName, String cmd, String... args) throws Exception {
        ConnectionOptions connectionOptions = new ConnectionOptions();
        connectionOptions.set(ADDRESS, host);
        connectionOptions.set(USERNAME, userName);
        connectionOptions.set(PASSWORD, password);
        connectionOptions.set(OPERATING_SYSTEM, UNIX);
        connectionOptions.set(CONNECTION_TYPE, SFTP);
        OverthereConnection overthereConnection = Overthere.getConnection("ssh", connectionOptions);
        String params = "";

        try {
            for (String arg : args) {
                params += arg + ",";
            }
            LOGGER.info("Executing remote shell command: {}", cmdName);
            overthereConnection.execute(CmdLine.build(cmdName, cmd, params.replaceAll(",$", "")));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            LOGGER.info("Remote shell connection closed");
            overthereConnection.close();
        }

    }
}

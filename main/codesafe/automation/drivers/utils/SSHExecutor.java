package codesafe.automation.drivers.utils;

import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SSHExecutor {
      //远程主机的ip地址
      private String ip;
      //远程主机登录用户名
      private String username;
      //远程主机的登录密码
      private String password;
      //设置ssh连接的远程端口
      public static final int DEFAULT_SSH_PORT = 22;
      //保存输出内容的容器
      private ArrayList<String> stdout;
      
      public SSHExecutor(String ip, String username, String password) {
            this.ip = ip;
            this.username = username;
            this.password = password;
            stdout = new ArrayList<String>();
      }
      
      public ArrayList<String> getStandardOutput() {
            return stdout;
      }
      
      public int execute(final String command) {
            int returnCode = 0;
            JSch jsch = new JSch();
            SSHUserInfo userInfo = new SSHUserInfo();
            
            try {
                  //创建session并且打开连接，因为创建session之后要主动打开连接
                  Session session = jsch.getSession(username, ip, DEFAULT_SSH_PORT);
                  session.setPassword(password);
                  session.setUserInfo(userInfo);
                  session.connect();
                  
                  //打开通道，设置通道类型，和执行的命令
                  Channel channel = session.openChannel("exec");
                  ChannelExec channelExec = (ChannelExec) channel;
                  channelExec.setCommand(command);
                  
                  channelExec.setInputStream(null);
                  BufferedReader input = new BufferedReader(new InputStreamReader
                          (channelExec.getInputStream()));
                  
                  channelExec.connect();
                  System.out.println("The remote command is :" + command);
                  
                  //接收远程服务器执行命令的结果
                  String line;
                  while ((line = input.readLine()) != null) {
                        stdout.add(line);
                  }
                  input.close();
                  
                  // 得到returnCode
                  if (channelExec.isClosed()) {
                        returnCode = channelExec.getExitStatus();
                  }
                  
                  // 关闭通道
                  channelExec.disconnect();
                  //关闭session
                  session.disconnect();
                  
            } catch (JSchException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return returnCode;
      }
}

package cn.hdu.usv.node;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import cn.hdu.usv.dao.ShipInforDao;
import cn.hdu.usv.dao.impl.ShipInforImpl;
import cn.hdu.usv.mavlink.MAVLinkPacket;
import cn.hdu.usv.mavlink.Parser;
import cn.hdu.usv.mavlink.mavlinkjava.common.ShipInformation;
import cn.hdu.usv.mavlink.messages.MAVLinkMessage;
/**
 * 通信协议接收
 * 1. 
 * @author luobicangqiong
 *
 */
public class NodeServlet extends HttpServlet {


	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void init() throws ServletException {
		
		System.out.println("NodeServlet服务启动");
		System.out.println("准备监听地面站接收到的信息");
	
		try {
			
			ServerSocket serverSocket = new ServerSocket();
			//172.19.251.119注意此时监听的IP不是公网的IP而是内网的IP
		   	serverSocket.bind(new InetSocketAddress("172.19.251.119",8086));
		   	System.out.println("socket配置完毕准备监听");
			new SocketThread(serverSocket).start();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}

class SocketThread extends Thread{
	
	ServerSocket serverSocket;
	
	public SocketThread(ServerSocket s) {

		this.serverSocket = s;
	}
	@Override
	public void run() {
		while(true){
			try {
				
				Socket socket= serverSocket.accept();
				new Thread(new MyThread(socket)).start();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	
}

class MyThread implements Runnable{
	
	Parser parser = new Parser();    //解析通信包
	ShipInforDao shipdao = new ShipInforImpl();
	Socket socket;
	public MyThread(Socket s) {
		// TODO Auto-generated constructor stub
		this.socket  = s;
	}
	
	
	public void run() {
		
			try {
				
				InputStream inputStream = socket.getInputStream();
				byte[] buf = new byte[1024];
				int length = 0;
				System.out.println("准备干活了");
				while((length = inputStream.read(buf))!=-1)
				{
				   
					String dataTmp = new String(buf,0,length);
					dataTmp = dataTmp.trim();
					System.out.println("接收到的数据为："+ new String(buf,0,length));
					if(!"www.usr.cn".equals(dataTmp))
					{ 
                       for(int i = 0;i<length;i++)
                       {
                    	   int code = buf[i]&0x00ff;
                    	   MAVLinkPacket receivedPacket = parser.mavlink_parse_char(code);
                    	   if(receivedPacket != null)
                    	   {
                    		   MAVLinkMessage message = receivedPacket.unpack(); //解码得到信息包
                               message.unpack(receivedPacket.payload);
                               System.out.println(message);
                               //接下来是存入数据库，把数据解析放入数据库
                               ShipInformation shipinfor = (ShipInformation) message;
                               shipdao.saveShipInfor(shipinfor);
                               break;
                    	   }
                       }
						
					}else{
						
						
						
					}
				}
				System.out.println(length);
				System.out.println("接收完毕，准备关闭socket");
				socket.close() ;
			} catch (IOException e) {

				e.printStackTrace();
			}
			
	}
	
}




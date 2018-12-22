package com.oks.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客服端
 * @author happy everyday
 *
 */
public class Client {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		 
		// 初始化Socket
		Socket socket = new Socket();
		
		// 超时时间
		socket.setSoTimeout(3000);
		
		// 连接本地
		socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 3000), 3000);
		
		System.out.println("已发起服务连接，并进入后续流程~");
		System.out.println("客服端信息:" + socket.getLocalAddress() + "P:" + socket.getLocalPort());
		System.out.println("服务端信息:" + socket.getInetAddress() + "P:" + socket.getPort());
		
		try {
			// 发送接收数据
			todo(socket);
		} catch (Exception e) {
		    System.out.println("异常关闭");
		}
		
		// 释放资源
		socket.close();
		System.out.println("客户端已退出");
		
	}
	
	private static void todo(Socket client) throws IOException {
		// 构建键盘输入流
		InputStream in = System.in;
		BufferedReader input = new BufferedReader(new InputStreamReader(in));
		
		// 得到Socket输出流
		OutputStream outputStream = client.getOutputStream();
		PrintStream socketPrintStream = new PrintStream(outputStream);
		
		// 得到输入流
		InputStream inputStream = client.getInputStream();
		BufferedReader socketBufferReader = new BufferedReader(new InputStreamReader(inputStream));
		
		
		// 键盘读取一行
		String str = input.readLine();
		// 发送到服务器
		socketPrintStream.println(str);
		// 从服务器读取一行
		String echo = socketBufferReader.readLine();
	     boolean flag = true;
	     do {
	    	 if("byte".equalsIgnoreCase(echo)) {
	    		 flag = false;
	    	 }else {
	    		 System.out.println(echo);
	    	 }
		} while (flag);
	     socketPrintStream.close();
	     socketBufferReader.close();
	}

}

package com.wb.snake;

import javax.swing.JFrame;

public class StartGames {
	public static void main(String[] args) {
		//1.绘制一个静态窗口 jframe（JFrame支持所有窗口的操作，例如窗口最小化，设定窗口大小）
		JFrame jframe=new JFrame("王博的贪吃蛇游戏");
		jframe.setBounds(10, 10, 900, 720);//设置画布界面大小
		jframe.setResizable(false);//界面大小不可拖拽改变
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭事件
		jframe.add(new GamePanel());//面板 
		jframe.setVisible(true);//窗口展现
	}

}

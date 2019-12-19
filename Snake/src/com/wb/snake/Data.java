package com.wb.snake;
//存放外部数据

import java.net.URL;

import javax.swing.ImageIcon;

public class Data {
	//通过url获取图片地址
	public static URL headuUrl=Data.class.getResource("/statics/header.png");
    //获取图片(它根据 Image 绘制 Icon图标)
	public static ImageIcon head=new ImageIcon(headuUrl);
	//头部（上下左右）
	public static URL upUrl=Data.class.getResource("/statics/up.png");
	public static URL downUrl=Data.class.getResource("/statics/down.png");
	public static URL leftUrl=Data.class.getResource("/statics/left.png");
	public static URL rightUrl=Data.class.getResource("/statics/right.png");
	public static ImageIcon up=new ImageIcon(upUrl);
	public static ImageIcon down=new ImageIcon(downUrl);
	public static ImageIcon left=new ImageIcon(leftUrl);
	public static ImageIcon right=new ImageIcon(rightUrl);
	//身体
	public static URL bodyUrl=Data.class.getResource("/statics/body.png");
	public static ImageIcon body=new ImageIcon(bodyUrl);
	//食物
	public static URL foodUrl=Data.class.getResource("/statics/food.png");
	public static ImageIcon food=new ImageIcon(foodUrl);
}

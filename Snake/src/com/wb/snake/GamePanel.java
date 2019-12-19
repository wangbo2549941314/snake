package com.wb.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
	// JPanel 是 Java图形用户界面(GUI)工具包swing中的面板容器类,包含在javax.swing
	// 包中,是一种轻量级容器,可以加入到JFrame窗体中。
	int length;// 蛇的长度
	int[] snakeX = new int[600];// 蛇的x轴坐标（可达到600节）
	int[] snakeY = new int[500];// 蛇的y轴坐标
	String fx;// 蛇头方向：R右，L左，U上，D下
	boolean isStart = false;// 游戏是否开始
	Timer timer = new Timer(100, this);// 定时器（100毫秒是小蛇的行驶速度）
	// 定义食物
	int foodx;
	int foody;
	// 定义存放食物的随机点
	Random random = new Random();
	// 死亡判断
	boolean isFail = false;
	// 积分系统
	int score;

	// 初始化
	public void init() {
		length = 3;// 蛇一出来三节（头一节，身体两节）
		snakeX[0] = 100;
		snakeY[0] = 100;// 头部坐标
		snakeX[1] = 75;
		snakeY[1] = 100;// 第一个身体坐标
		snakeX[2] = 50;
		snakeY[2] = 100;// 第二个身体坐标
		fx = "R";// 小蛇头部坐标
		foodx = 25 + 25 * random.nextInt(34);// 存放在x轴食物
		foody = 75 + 25 * random.nextInt(24);// 存放在y轴食物
		score = 0;// 成绩默认为0
	}

	// 构造器
	public GamePanel() {
		init();
		this.setFocusable(true);// 获取键盘监听事件
		this.addKeyListener(this);// 添加监听事件
		timer.start();// 让时间动起来
	}

	// 画板 画界面 画蛇
	protected void paintComponent(Graphics g) {// Graphics 相当于画笔
		super.paintComponent(g);// 清屏
		this.setBackground(Color.white);// 设置背景颜色
		Data.head.paintIcon(this, g, 25, 11);// 头部图片（ 参数1：画到那一个组件上 ，画笔，x轴，y轴）
		g.fillRect(25, 75, 850, 600);// 绘制游戏区域

		// 画一条小蛇（蛇头方向）
		if (fx.equals("R")) {
			Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
		} else if (fx.equals("L")) {
			Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
		} else if (fx.equals("U")) {
			Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
		} else if (fx.equals("D")) {
			Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
		}
		/**
		 * Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);//蛇头
		 * Data.body.paintIcon(this, g, snakeX[1], snakeY[1]);//蛇第一个身体
		 * Data.body.paintIcon(this, g, snakeX[2], snakeY[2]);//蛇第二个身体
		 **/
		// 蛇身
		for (int i = 1; i < length; i++) {
			Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);// 蛇的身体长度通过length来确定
		}
		// 画积分
		g.setColor(Color.white);
		g.setFont(new Font("微软雅黑", Font.BOLD, 18));// 设置字体（Font.BOLD字体加粗）
		g.drawString("长度" + length, 750, 30);
		g.drawString("分数" + score, 750, 50);

		// 画食物
		Data.food.paintIcon(this, g, foodx, foody);
		// 游戏提示：是否开始
		if (isStart == false) {
			// 画一个文字
			g.setColor(Color.white);// 字体颜色为白色
			g.setFont(new Font("微软雅黑", Font.BOLD, 40));// 设置字体（Font.BOLD字体加粗）
			g.drawString("按下空格开始游戏", 300, 300);
		}
		// 失败提示：
		if (isFail) {
			// 画一个文字
			g.setColor(Color.RED);// 字体颜色为白色
			g.setFont(new Font("微软雅黑", Font.BOLD, 40));// 设置字体（Font.BOLD字体加粗）
			g.drawString("游戏失败，按下空格开始游戏", 200, 300);
		}
	}

	// 键盘按下，未释放
	public void keyPressed(KeyEvent e) {

		// 按下的键盘是哪个键
		int keyCode = e.getKeyCode();

		// 判断是不是空格键
		if (keyCode == KeyEvent.VK_SPACE) {
			if (isFail) {// 游戏失败，重新再来
				isFail = false;
				init();
			} else {
				isStart = !isStart;// 暂停游戏
			}
			repaint();// 刷新界面
		}

		// 键盘走向
		if (keyCode == KeyEvent.VK_LEFT) {
			fx = "L";
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			fx = "R";
		} else if (keyCode == KeyEvent.VK_UP) {
			fx = "U";
		} else if (keyCode == KeyEvent.VK_DOWN) {
			fx = "D";
		}
	}

	// 定时器，监听事件，帧：执行定时操作
	public void actionPerformed(ActionEvent e) {
		if (isStart) {// 如果游戏处于开始状态，并且游戏没有结束
			// 蛇身
			for (int i = length - 1; i > 0; i--) {//除了脑袋，蛇身向前移动（ength - 1是减去头部位置）
				snakeX[i] = snakeX[i - 1];
				snakeY[i] = snakeY[i - 1];
			}
			// 通过控制方向让头部移动
			if (fx.equals("R")) {
				snakeX[0] = snakeX[0] + 25;// 头部移动
				// 边界判断
				if (snakeX[0] > 850) {// 如果大于850最高边界，就让它回到初始状态
					snakeX[0] = 25;
				}
			} else if (fx.equals("L")) {
				snakeX[0] = snakeX[0] - 25;// 头部移动
				// 边界判断
				if (snakeX[0] < 25) {
					snakeX[0] = 850;
				}
			} else if (fx.equals("U")) {
				snakeY[0] = snakeY[0] - 25;// 头部移动
				// 边界判断
				if (snakeY[0] < 75) {
					snakeY[0] = 650;
				}
			} else if (fx.equals("D")) {
				snakeY[0] = snakeY[0] + 25;// 头部移动
				// 边界判断
				if (snakeY[0] > 650) {
					snakeY[0] = 75;
				}
			}
			// 如果小蛇的头与食物重合了
			if (snakeX[0] == foodx && snakeY[0] == foody) {

				length++;// 长度+1
				score = score + 10;// 分数+10分
				// 重新生成食物
				foodx = 25 + 25 * random.nextInt(34);
				foody = 75 + 25 * random.nextInt(24);
			}
			// 结束判断
			for (int i = 1; i < length; i++) {
				if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
					isFail = true;
				}
			}
			// 如果蛇超过游戏边界，则游戏失败
			if (snakeX[0] == 850 || snakeY[0] == 600) {
				isFail = true;
				init();
			}

			repaint();// 刷新界面
		}
		timer.start();// 让时间动起来

	}

	// 释放某个键
	public void keyReleased(KeyEvent e) {

	}

	// 键盘按下弹起：敲击
	public void keyTyped(KeyEvent e) {

	}
}

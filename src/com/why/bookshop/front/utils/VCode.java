package com.why.bookshop.front.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.junit.Test;

/**
 * 1.������Ϊ rgb(240, 240, 240); 2.����������� 3.�������������ɫ 4.��������ַ� 5.�����������
 * 
 * @author AFF
 * 
 */

public class VCode {

	private int width = 100;
	private int height = 40;
	
	private StringBuilder code = new StringBuilder(4);

	private Random random = new Random();

	public Color randomColor() {
		return new Color(random.nextInt(256), random.nextInt(256),
				random.nextInt(256));
	}

	public Font randomFont() {
		String[] str = { "�����п�", "����", "����", "����" };

		String name = str[random.nextInt(str.length)];
		int style = random.nextInt(4);
		int size = 30 + random.nextInt(6);

		return new Font(name, style, size);
	}

	public String randomChar() {
		StringBuilder sb = new StringBuilder();
		String code = "abcdefghijklmnopqrstuvwxyz"
				+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
		
		return sb.append(code.charAt(random.nextInt(code.length()))).toString();
	}

	public void randomDrawLine(BufferedImage img) {
		
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		
		Graphics2D g = (Graphics2D)img.getGraphics();
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);
		g.setStroke(new BasicStroke(1.5f));
		
		for(int i = 0; i < 4; i++){
			x1 = random.nextInt(width);
		    y1 = random.nextInt(height);
			x2 = random.nextInt(width);
			y2 = random.nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}
	}

	public BufferedImage createImage() {
		BufferedImage img = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setColor(new Color(240, 240, 240));
		g.fillRect(0, 0, width, height);

		for (int i = 0; i < 4; i++) {
			String ch = randomChar();
			
			code.append(ch);
			
			g.setColor(randomColor());
			g.setFont(randomFont());
			g.drawString(ch, width / 4 * i, 35);
		}
		randomDrawLine(img);
		return img;
	}

	public void save(BufferedImage img, OutputStream os) throws FileNotFoundException,
			IOException {
		ImageIO.write(img, "jpeg", os);
	}
	
	public String getCode(){
		return this.code.toString();
	}
}

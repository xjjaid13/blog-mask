package com.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.*;
import javax.servlet.http.*;

public class VerifyCodeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L; // 序列号

	private static final int IMAGE_WIDTH = 60; // 图象的宽度

	private static final int IMAGE_HEIGHT = 20; // 图象高度

	private static final String SVG_SOURCE1 = "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.0//EN\" \"http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd\">"
			+ "<svg width=\""
			+ IMAGE_WIDTH
			+ "\" height=\""
			+ IMAGE_HEIGHT
			+ "\" xmlns=\"http://www.w3.org/2000/svg\">"
			+ "<text x=\"0\" y=\""
			+ IMAGE_HEIGHT
			+ "\" font-family=\"Arial\" font-size=\""
			+ IMAGE_HEIGHT + "\" fill=\"black\">"; // SVG图片格式的来源

	private static final String SVG_SOURCE2 = "</text></svg>"; // SVG图片格式的来源

	private boolean svgMode = false;

	/**
	 * 判断调用生成随机码图象的格式 如果图形环境无法使用（例如：在Linux控制台下运行）使用 SVG的
	 */
	protected void service(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ServletException,
			IOException {
		// 取得页面参数
		String strCode = httpServletRequest.getParameter("code");
		String strVar = httpServletRequest.getParameter("var"); 

		String str_code = sn2vcode(strCode); // 生成随机码
		if(strVar == null){
			httpServletRequest.getSession().setAttribute("COMPLAT",
					str_code); // 将随机码保存到SESSION中
		}else{
			httpServletRequest.getSession().setAttribute(strVar,
					str_code); // 将随机码保存到SESSION中
		}
		

		if (svgMode)
			outSVG(str_code, httpServletResponse);
		else
			outJPEG(str_code, httpServletResponse);
	}

	/**
	 * SVG图象格式
	 * 
	 * @param svg_strCode
	 * @param resp
	 * @throws IOException
	 */
	private void outSVG(String svg_strCode, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("image/svg+xml");
		resp.getOutputStream().print(SVG_SOURCE1 + svg_strCode + SVG_SOURCE2);
	}

	/**
	 * JPEG图象格式
	 * 
	 * @param jpeg_strCode
	 * @param httpServletResponse
	 * @throws IOException
	 */
	private void outJPEG(String jpeg_strCode,
			HttpServletResponse httpServletResponse) throws IOException {

		// 设置应答流中的文本格式
		httpServletResponse.setContentType("image/jpeg");
		// 图片的字符流
		BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		// 随即数
		Random random = new Random();
		// 绘图对象
		Graphics graphics = image.getGraphics();
		// 设置颜色
		graphics.setColor(getRandColor(200, 250));
		// 绘制矩形
		graphics.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
		// 设置字体
		graphics.setFont(new Font("Times New Roman", Font.HANGING_BASELINE, 18));
		// 设置颜色
		graphics.setColor(getRandColor(200, 250));
		// 构建4个随即数,然后画条线
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(IMAGE_WIDTH);
			int y = random.nextInt(IMAGE_HEIGHT);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			graphics.drawLine(x, y, x + xl, y + yl);
		}
		
		//绘制矩形
		graphics.setColor(Color.black);		
		graphics.drawRect(0,0,IMAGE_WIDTH-1,IMAGE_HEIGHT-1);
		
		for (int i = 1; i <= 4; i++) {
			String strRand = jpeg_strCode.substring(i - 1, i);
			graphics.setColor(new Color(30 + random.nextInt(120), 30 + random
					.nextInt(120), 30 + random.nextInt(120)));
			graphics.drawString(strRand, 13 * (i-1) + 6, 16);
		}

		// 输出到客户端
		ImageWriter writer = (ImageWriter) ImageIO.getImageWritersByFormatName(
				"jpeg").next();
		JPEGImageWriteParam params = new JPEGImageWriteParam(null);
		ImageOutputStream ios = ImageIO
				.createImageOutputStream(httpServletResponse.getOutputStream());
		writer.setOutput(ios);
		writer.write(null, new IIOImage(image, null, null), params);
		writer.dispose();
		// 关闭流
		ios.close();
	}

	/**
	 * 随机码的生成
	 * 
	 * @param strCode
	 * @return
	 */
	public String sn2vcode(String strCode) {
		if(strCode == null || strCode.equals(""))
			strCode = "1";
		
		if(strCode.equals("1")){//数字
			strCode = "0123456789";
		} else if(strCode.equals("2")){ //小写字母
			strCode = "abcdefghijklmnopqrstuvwxyz";
		}else if(strCode.equals("3")){ //大写字母
			strCode = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
		}else if(strCode.equals("4")){ //混合
			strCode = "0123456789ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghijk"
				+ "lmnopqrstuvwxyz";
		}
		int LengthOfRandom = strCode.length();
		StringBuffer sRand = new StringBuffer();
		int rand;
		Random random = new Random();
		// 循环取随机数
		for (int i = 0; i < 4; i++) {
			// String rand = String.valueOf(random.nextInt(10));
			rand = random.nextInt(LengthOfRandom);
			// sRand += rand;
			sRand.append(strCode.substring(rand, rand + 1));
		}
		return sRand.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */

	public void init() throws ServletException {
		try {
			GraphicsEnvironment.getLocalGraphicsEnvironment();
		} catch (Throwable e) {
			svgMode = true;
		}
	}

	/**
	 * 获得颜色对象
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	public Color getRandColor(int fc, int bc) {

		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}

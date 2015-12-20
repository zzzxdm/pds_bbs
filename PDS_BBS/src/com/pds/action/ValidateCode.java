package com.pds.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@SuppressWarnings({ "restriction", "restriction", "restriction" })
public class ValidateCode extends ActionSupport {

	private static final int WIDTH = 120;
	private static final int HEIGHT = 40;
	private ByteArrayInputStream imageStream;

	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;
	}

	/**
	 * Constructor of the object.
	 */
	public ValidateCode() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		imageStream = getImageAsInputStream(createCheckCode(response, session));
		return super.execute();
	}
	//产生图片
	public BufferedImage createCheckCode(HttpServletResponse response,
		HttpSession session) throws IOException {
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		Random random = new Random();

		// 设置背景色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		// 设置边框
		g.setColor(Color.BLACK);
		g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
		// 设置字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		g.setColor(Color.GRAY);

		// 画随机线
		for (int i = 0; i < 6; i++) {
			int x = random.nextInt(WIDTH);
			int y = random.nextInt(HEIGHT);
			int x1 = random.nextInt(WIDTH);
			int y1 = random.nextInt(HEIGHT);
			g.setColor(random_Color());
			g.drawLine(x, y, x1, y1);
		}

		String Rand = "", temp = "";
		int x = 10;
		for (int i = 0; i < 4; i++) {
			String number = String.valueOf(random.nextInt(10));
			String Upper = (char) (random.nextInt(26) + 65) + "";
			String Lower = (char) (random.nextInt(26) + 97) + "";
			// 随机产生数字，大小写字母
			switch (random.nextInt(3)) {
			case 0:
				temp = number;
				break;
			case 1:
				temp = Upper;
				break;
			case 2:
				temp = Lower;
				break;
			default:
				temp = number;
				break;
			}
			// 设置随机颜色
			g.setColor(random_Color());
			// 让产生的数字或字母旋转
			int angle = random.nextInt() % 30;
			g.rotate(angle * Math.PI / 180, x, 25);
			g.drawString(temp, x, 25);
			g.rotate(-angle * Math.PI / 180, x, 25);
			x += 28;
			Rand += temp;
		}
		g.dispose();
		System.out.println(Rand);
		session.setAttribute("vCode", Rand);
		return image;
	}
	//把图片转成流
	private static ByteArrayInputStream convertImageToStream(BufferedImage image) {
		ByteArrayInputStream inputStream = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(bos);
		try {
			jpeg.encode(image);
			byte[] bts = bos.toByteArray();
			inputStream = new ByteArrayInputStream(bts);
		} catch (ImageFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputStream;
	}
	//把图片读成流
	public static ByteArrayInputStream getImageAsInputStream(BufferedImage image){
         return convertImageToStream(image);
     }
	
	// 产生随机颜色
	private Color random_Color() {
		Color c = Color.black;

		switch (new Random().nextInt(13)) {
		case 1:
			c = Color.blue;
			break;
		case 2:
			c = Color.black;
			break;
		case 3:
			c = Color.cyan;
			break;
		case 4:
			c = Color.DARK_GRAY;
			break;
		case 5:
			c = Color.gray;
			break;
		case 6:
			c = Color.green;
			break;
		case 7:
			c = Color.LIGHT_GRAY;
			break;
		case 8:
			c = Color.magenta;
			break;
		case 9:
			c = Color.red;
			break;
		case 10:
			c = Color.orange;
			break;
		case 11:
			c = Color.pink;
			break;
		case 12:
			c = Color.yellow;
			break;
		default:
			c = Color.black;
			break;
		}
		return c;
	}
}

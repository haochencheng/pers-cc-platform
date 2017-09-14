package pers.platfrom.common.utils.qrcode;

import com.google.zxing.*;
import com.google.zxing.Reader;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * <p>
 * Title: 二维码工具类
 * </p>
 * <p>
 * Description: 用于生成二维码
 * </p>
 * 
 * @author cc (non-javadoc)
 */
public class QRCodeUtils {

	public static void main(String[] args) throws Exception {
		String str = QRCodeUtils.parse("E:\\qrcode\\a.jpg");
		System.out.println(str);
	}

	private static transient String DEFAULT_FORMAT = "jpg";
	// 二维码默认宽
	private static transient int DEFAULT_WIDTH = 200;
	// 二维码默认长
	private static transient int DEFAULT_HEIGHT = 200;

	static {
		try {
			// 图片大小 默认240X240
			final String[] foo = new String[] { "240", "240" };
			final String format = "jpg";
			if (StringUtils.isNotBlank(format)) {
				DEFAULT_FORMAT = StringUtils.strip(format).toLowerCase();
			}

			if (ArrayUtils.isNotEmpty(foo) && foo.length == 2) {
				Integer tmpWidth = Integer.valueOf(foo[0]);
				Integer tmpHeight = Integer.valueOf(foo[1]);
				if (tmpWidth > 0 && tmpHeight > 0) {
					DEFAULT_WIDTH = tmpWidth;
					DEFAULT_HEIGHT = tmpHeight;
				} else {
					System.out.println("qrcode size must be lager than zero.");
					throw new RuntimeException("qrcode size must be lager than zero.");
				}
			}
		} catch (Throwable e) {
			throw new RuntimeException("read default qrcode size config error: " + e.getCause());
		}
	}

	/**
	 * 生成二维码（无中间logo）
	 * 
	 * @param content
	 *            二维码文本内容
	 * @param destFile
	 *            输出文件
	 */
	public static final void gen(final String content, File destFile) throws Exception {
		gen(content, destFile, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            二维码文本内容
	 * @param destFile
	 *            目的文件
	 * @param logoFile
	 *            中间logo文件
	 * 
	 */
	public static final void gen(final String content, final File destFile, final File logoFile) throws Exception {
		gen(content, destFile, logoFile, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            二维码文本内容
	 * @param destFile
	 *            目的文件
	 * @param logoFile
	 *            中间logo文件
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 */
	public static final void gen(final String content, final File destFile, final File logoFile, int width, int height)
			throws Exception {
		FolderUtils.mkdirs(destFile.getParent());
		OutputStream output = null;
		InputStream input = null;
		try {
			output = new BufferedOutputStream(new FileOutputStream(destFile));
			if (logoFile != null && logoFile.exists() && logoFile.isFile()) {
				input = new BufferedInputStream(new FileInputStream(logoFile));
			} else {
				throw new RuntimeException("can't find logo file!");
			}
			gen(content, output, input, width, height);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			IOUtils.closeQuietly(output);
			IOUtils.closeQuietly(input);
		}
	}

	/**
	 * 生成二维码（无中间logo）
	 * 
	 * @param content
	 *            二维码文本内容
	 * @param destFile
	 *            输出文件
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 */
	public static final void gen(final String content, File destFile, int width, int height) throws Exception {
		FolderUtils.mkdirs(destFile.getParent());
		OutputStream output = null;
		try {
			output = new BufferedOutputStream(new FileOutputStream(destFile));
			gen(content, output, width, height);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception(e);
		} catch (Exception e) {
			throw e;
		} finally {
			IOUtils.closeQuietly(output);
		}
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            二维码文本内容
	 * @param output
	 *            输出流
	 */
	public static final void gen(final String content, final OutputStream output) throws Exception {
		gen(content, output, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            二维码文本内容
	 * @param output
	 *            输出流
	 * @param logoInput
	 *            中间logo输入流，为空时中间无logo
	 */
	public static final void gen(final String content, final OutputStream output, final InputStream logoInput)
			throws Exception {
		gen(content, output, logoInput, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            二维码文本内容
	 * @param output
	 *            输出流
	 * @param logoInput
	 *            中间logo输入流，为空时中间无logo
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 */
	public static final void gen(final String content, final OutputStream output, final InputStream logoInput,
			int width, int height) throws Exception {
		// 设置容错级别为高
		gen(content, output, logoInput, width, height, ErrorCorrectionLevel.H);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            二维码文本内容
	 * @param output
	 *            输出流
	 * @param logoInput
	 *            中间logo输入流，为空时中间无logo
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param errorCorrectionLevel
	 *            容错级别
	 */
	public static final void gen(final String content, final OutputStream output, final InputStream logoInput,
			int width, int height, ErrorCorrectionLevel errorCorrectionLevel) throws Exception {
		if (StringUtils.isEmpty(content)) {
			throw new IllegalArgumentException("qr code content cannot be empty.");
		}
		if (output == null) {
			throw new IllegalArgumentException("qr code output stream cannot be null.");
		}

		final BitMatrix matrix = MatrixToImageWriterEx.createQRCode(content, width, height, errorCorrectionLevel);

		if (logoInput == null) {
			try {
				MatrixToImageWriter.writeToStream(matrix, DEFAULT_FORMAT, output);
				return;
			} catch (IOException e) {
				e.printStackTrace();
				throw new Exception(e);
			}
		}

		final MatrixToLogoImageConfig logoConfigPro = new MatrixToLogoImageConfig(Color.BLUE, 4);

		final String destPath = FilenameUtils.normalizeNoEndSeparator(
				SystemUtils.getJavaIoTmpDir() + File.separator + UUID.randomUUID().toString() + ".tmp");
		InputStream tmpInput = null;
		final File destFile = new File(destPath);
		try {
			MatrixToImageWriterEx.writeToFile(matrix, DEFAULT_FORMAT, destPath, logoInput, logoConfigPro);
			tmpInput = new BufferedInputStream(new FileInputStream(destFile));
			IOUtils.copy(tmpInput, output);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			IOUtils.closeQuietly(tmpInput);
			destFile.delete();
		}
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            二维码文本内容
	 * @param output
	 *            输出流
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 */
	public static final void gen(final String content, final OutputStream output, int width, int height)
			throws Exception {
		gen(content, output, null, width, height);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            二维码文本内容
	 * @param destPath
	 *            输出文件路径
	 */
	public static final void gen(final String content, final String destPath) throws Exception {
		gen(content, destPath, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            二维码文本内容
	 * @param destPath
	 *            输出文件路径
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 */
	public static final void gen(final String content, final String destPath, int width, int height) throws Exception {
		gen(content, new File(destPath), width, height);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            二维码文本内容
	 * @param destPath
	 *            目的文件路径
	 * @param logoPath
	 *            中间logo文件路径
	 */
	public static final void gen(final String content, final String destPath, final String logoPath) throws Exception {
		gen(content, destPath, logoPath, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            二维码文本内容
	 * @param destPath
	 *            目的文件路径
	 * @param logoPath
	 *            中间logo文件路径
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 */
	public static final void gen(final String content, final String destPath, final String logoPath, int width,
			int height) throws Exception {
		File foo = new File(destPath);
		File bar = new File(logoPath);
		gen(content, foo, bar, width, height);
	}

	/**
	 * 解析二维码
	 * 
	 * @param input
	 *            二维码输入流
	 */
	public static final String parse(InputStream input) throws Exception {
		Reader reader = null;
		BufferedImage image;
		try {
			image = ImageIO.read(input);
			if (image == null) {
				throw new Exception("cannot read image from inputstream.");
			}
			final LuminanceSource source = new BufferedImageLuminanceSource(image);
			final BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			// ####################111111#######################################
			// final Map<DecodeHintType, String> hints = new HashMap<DecodeHintType,
			// String>();
			// hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
			// ######################111111#############################
			Map<DecodeHintType, Object> tmpHintsMap = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
			tmpHintsMap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
			tmpHintsMap.put(DecodeHintType.POSSIBLE_FORMATS, EnumSet.allOf(BarcodeFormat.class));
			tmpHintsMap.put(DecodeHintType.PURE_BARCODE, Boolean.FALSE);
			tmpHintsMap.put(DecodeHintType.CHARACTER_SET, "utf-8");

			// 解码设置编码方式为：utf-8，
			reader = new MultiFormatReader();
			return reader.decode(bitmap, tmpHintsMap).getText();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("parse QR code error: ", e);
		} catch (ReaderException e) {
			e.printStackTrace();
			throw new Exception("parse QR code error: ", e);
		}
	}

	/**
	 * 解析二维码
	 * 
	 * @param url
	 *            二维码url
	 */
	public static final String parse(URL url) throws Exception {
		InputStream in = null;
		try {
			in = url.openStream();
			return parse(in);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("parse QR code error: ", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	/**
	 * 解析二维码
	 * 
	 * @param file
	 *            二维码图片文件
	 */
	public static final String parse(File file) throws Exception {
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			return parse(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception("parse QR code error: ", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	/**
	 * 解析二维码
	 * 
	 * @param filePath
	 *            二维码图片文件路径
	 */
	public static final String parse(String filePath) throws Exception {
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(filePath));
			return parse(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception("parse QR code error: ", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public static final String parse1(String filePath) throws Exception {
		BufferedImage image;
		Result result = null;
		try {
			image = ImageIO.read(new File(filePath));
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.getText();
	}

	static class MatrixToImageWriterEx {

		private final static MatrixToLogoImageConfig DEFAULT_CONFIG = new MatrixToLogoImageConfig();

		/**
		 * 根据内容生成二维码数据
		 * 
		 * @param content
		 *            二维码文字内容[为了信息安全性，一般都要先进行数据加密]
		 * @param width
		 *            二维码照片宽度
		 * @param height
		 *            二维码照片高度
		 * @param errorCorrectionLevel
		 *            纠错等级
		 * @return a {@link com.google.zxing.common.BitMatrix} object.
		 * @since 0.0.7
		 */
		public static BitMatrix createQRCode(String content, int width, int height,
				ErrorCorrectionLevel errorCorrectionLevel) {
			Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
			// 设置字符编码
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			// 指定纠错等级
			hints.put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);
			BitMatrix matrix = null;
			try {
				matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			} catch (WriterException e) {
				e.printStackTrace();
			}
			return matrix;
		}

		/**
		 * 根据内容生成二维码数据
		 * 
		 * @param content
		 *            二维码文字内容[为了信息安全性，一般都要先进行数据加密]
		 * @param width
		 *            二维码照片宽度
		 * @param height
		 *            二维码照片高度
		 * @return a {@link com.google.zxing.common.BitMatrix} object.
		 * @since 0.0.7
		 */
		public BitMatrix createQRCode(String content, int width, int height) {
			return createQRCode(content, width, height, ErrorCorrectionLevel.H);
		}

		/**
		 * 写入二维码、以及将照片logo写入二维码中
		 * 
		 * @param matrix
		 *            要写入的二维码
		 * @param format
		 *            二维码照片格式
		 * @param imagePath
		 *            二维码照片保存路径
		 * @param logoPath
		 *            logo路径
		 * @throws IOException
		 *             if any.
		 * @since 0.0.7
		 */
		public static void writeToFile(BitMatrix matrix, String format, String imagePath, String logoPath)
				throws IOException {
			InputStream input = null;
			try {
				input = new BufferedInputStream(new FileInputStream(logoPath));
				writeToFile(matrix, format, imagePath, input);
			} catch (IOException e) {
				throw e;
			} finally {
				IOUtils.closeQuietly(input);
			}

		}

		/**
		 * <p>
		 * writeToFile.
		 * </p>
		 *
		 * @param matrix
		 *            a {@link com.google.zxing.common.BitMatrix} object.
		 * @param format
		 *            a {@link String} object.
		 * @param imagePath
		 *            a {@link String} object.
		 * @param logoInputStream
		 *            a {@link InputStream} object.
		 * @throws IOException
		 *             if any.
		 * @since 0.0.7
		 */
		public static void writeToFile(BitMatrix matrix, String format, String imagePath, InputStream logoInputStream)
				throws IOException {
			MatrixToImageWriter.writeToPath(matrix, format, new File(imagePath).toPath(), new MatrixToImageConfig());
			// 添加logo图片, 此处一定需要重新进行读取，而不能直接使用二维码的BufferedImage 对象
			BufferedImage img = ImageIO.read(new File(imagePath));
			MatrixToImageWriterEx.overlapImage(img, format, imagePath, logoInputStream, DEFAULT_CONFIG);
		}

		/**
		 * 写入二维码、以及将照片logo写入二维码中
		 *
		 * @param matrix
		 *            要写入的二维码
		 * @param format
		 *            二维码照片格式
		 * @param imagePath
		 *            二维码照片保存路径
		 * @param logoPath
		 *            logo路径
		 * @param logoConfig
		 *            logo配置对象
		 * @throws IOException
		 *             if any.
		 * @since 0.0.7
		 */
		public static void writeToFile(BitMatrix matrix, String format, String imagePath, InputStream logoPath,
				MatrixToLogoImageConfig logoConfig) throws IOException {
			MatrixToImageWriter.writeToPath(matrix, format, new File(imagePath).toPath(), new MatrixToImageConfig());
			// 添加logo图片, 此处一定需要重新进行读取，而不能直接使用二维码的BufferedImage 对象
			BufferedImage img = ImageIO.read(new File(imagePath));
			MatrixToImageWriterEx.overlapImage(img, format, imagePath, logoPath, logoConfig);
		}

		/**
		 * 将照片logo添加到二维码中间
		 * 
		 * @param image
		 *            生成的二维码照片对象
		 * @param imagePath
		 *            照片保存路径
		 * @param logoInputStream
		 *            logo输入流
		 * @param formate
		 *            照片格式
		 * @param logoConfig
		 *            a {@link cn.yicha.commons.qrcode.MatrixToLogoImageConfig} object.
		 * @since 0.0.7
		 */
		public static void overlapImage(BufferedImage image, String formate, String imagePath,
				InputStream logoInputStream, MatrixToLogoImageConfig logoConfig) {
			try {
				BufferedImage logo = ImageIO.read(logoInputStream);
				Graphics2D g = image.createGraphics();
				// 考虑到logo照片贴到二维码中，建议大小不要超过二维码的1/5;
				int width = image.getWidth() / logoConfig.getLogoPart();
				int height = image.getHeight() / logoConfig.getLogoPart();
				// logo起始位置，此目的是为logo居中显示
				int x = (image.getWidth() - width) / 2;
				int y = (image.getHeight() - height) / 2;
				// 绘制图
				g.drawImage(logo, x, y, width, height, null);

				// 给logo画边框
				// 构造一个具有指定线条宽度以及 cap 和 join 风格的默认值的实心 BasicStroke
				g.setStroke(new BasicStroke(logoConfig.getBorder()));
				g.setColor(logoConfig.getBorderColor());
				g.drawRect(x, y, width, height);

				g.dispose();
				// 写入logo照片到二维码
				ImageIO.write(image, formate, new File(imagePath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

class MatrixToLogoImageConfig {

	// logo默认边框颜色
	/** Constant <code>DEFAULT_BORDERCOLOR</code> */
	public static final Color DEFAULT_BORDERCOLOR = Color.RED;
	// logo默认边框宽度
	/** Constant <code>DEFAULT_BORDER=2</code> */
	public static final int DEFAULT_BORDER = 2;
	// logo大小默认为照片的1/5
	/** Constant <code>DEFAULT_LOGOPART=5</code> */
	public static final int DEFAULT_LOGOPART = 5;

	private final int border = DEFAULT_BORDER;
	private final Color borderColor;
	private final int logoPart;

	/**
	 * Creates a default config with on color {@link #BLACK} and off color
	 * {@link #WHITE}, generating normal black-on-white barcodes.
	 * 
	 * @since 0.0.7
	 */
	public MatrixToLogoImageConfig() {
		this(DEFAULT_BORDERCOLOR, DEFAULT_LOGOPART);
	}

	/**
	 * <p>
	 * Constructor for MatrixToLogoImageConfig.
	 * </p>
	 * 
	 * @param borderColor
	 *            a {@link Color} object.
	 * @param logoPart
	 *            a int.
	 * @since 0.0.7
	 */
	public MatrixToLogoImageConfig(Color borderColor, int logoPart) {
		this.borderColor = borderColor;
		this.logoPart = logoPart;
	}

	/**
	 * <p>
	 * Getter for the field <code>borderColor</code>.
	 * </p>
	 *
	 * @return a {@link Color} object.
	 * @since 0.0.7
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * <p>
	 * Getter for the field <code>border</code>.
	 * </p>
	 * 
	 * @return a int.
	 * @since 0.0.7
	 */
	public int getBorder() {
		return border;
	}

	/**
	 * <p>
	 * Getter for the field <code>logoPart</code>.
	 * </p>
	 * 
	 * @return a int.
	 * @since 0.0.7
	 */
	public int getLogoPart() {
		return logoPart;
	}

}

/**
 * 文件夹工具类
 * 
 * @author cc
 *
 */
class FolderUtils {
	/**
	 * 创建完整路径
	 *
	 * @param path
	 *            a {@link String} object.
	 */
	public static final void mkdirs(final String... path) {
		for (String foo : path) {
			final String realPath = FilenameUtils.normalizeNoEndSeparator(foo, true);
			final File folder = new File(realPath);
			if (!folder.exists() || folder.isFile()) {
				folder.mkdirs();
			}
		}
	}
}

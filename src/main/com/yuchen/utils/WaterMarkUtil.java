package com.yuchen.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WaterMarkUtil {

    /**
     * 添加水印到图片右下角，返回带水印的临时文件
     *
     * @param originalImageFile 原始图片文件
     * @return 带水印的临时图片文件
     * @throws IOException 如果处理图片时出现问题
     */
    public static File addWaterMark(File originalImageFile) throws IOException {
        // 加载原始图片
        BufferedImage originalImage = ImageIO.read(originalImageFile);

        // 创建一个与原始图片大小相同的 BufferedImage 对象
        BufferedImage watermarkedImage = new BufferedImage(
                originalImage.getWidth(),
                originalImage.getHeight(),
                BufferedImage.TYPE_INT_RGB // 不需要透明背景，适配网络输出
        );

        // 创建 Graphics2D 对象
        Graphics2D g2d = watermarkedImage.createGraphics();

        // 绘制原始图片到新图像
        g2d.drawImage(originalImage, 0, 0, null);

        // 设置水印的字体和颜色
        Font font = new Font("Arial", Font.BOLD, 30);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK); // 黑色文字

        // 水印内容
        String watermarkText = "西南大学校园论坛";

        // 计算水印位置
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int x = originalImage.getWidth() - fontMetrics.stringWidth(watermarkText) - 10; // 距离右边 10 像素
        int y = originalImage.getHeight() - 10; // 距离底部 10 像素

        // 绘制水印文字
        g2d.drawString(watermarkText, x, y);

        // 释放 Graphics2D 资源
        g2d.dispose();

        // 创建一个临时文件以存储带水印的图片
        File watermarkedFile = File.createTempFile("watermarked_", ".jpg");

        // 将带水印的图片写入临时文件
        ImageIO.write(watermarkedImage, "jpg", watermarkedFile);

        // 返回带水印的临时文件
        return watermarkedFile;
    }
}

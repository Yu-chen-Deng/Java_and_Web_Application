package com.yuchen.filter;

import com.yuchen.utils.WaterMarkUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

// 有些问题没处理好，暂时不用
// @WebFilter(urlPatterns = {"/postInfoServlet"})
public class WaterMarkFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 包装响应以捕获输出内容
        CharResponseWrapper responseWrapper = new CharResponseWrapper((jakarta.servlet.http.HttpServletResponse) response);

        try {
            // 继续过滤链，捕获页面内容
            chain.doFilter(request, responseWrapper);

            // 获取原始 HTML 内容
            String originalContent = responseWrapper.toString();
            // 解析 HTML 并处理图片
            String modifiedContent = processImages(originalContent);

            // 设置正确的响应类型和字符编码
            response.setContentType("text/html; charset=UTF-8");
            response.setContentLength(modifiedContent.getBytes("UTF-8").length);
            response.getWriter().write(modifiedContent);
        } catch (Exception e) {
            e.printStackTrace();
            // 如果处理失败，继续传递原始内容
            chain.doFilter(request, response);
        }
    }

    /**
     * 解析 HTML 并处理所有图片
     */
    private String processImages(String originalContent) throws IOException {
        Document document = Jsoup.parse(originalContent);
        Elements imgTags = document.getElementsByTag("img");

        for (Element imgTag : imgTags) {
            String src = imgTag.attr("src");

            if (!src.isEmpty() && isValidURL(src)) {
                // 下载图片到本地临时文件
                File tempImage = downloadImage(src);

                if (tempImage != null && tempImage.exists()) {
                    // 对图片添加水印
                    File watermarkedImage = WaterMarkUtil.addWaterMark(tempImage);

                    // 替换 img 标签的 src 属性为本地临时文件路径
                    String tempPath = watermarkedImage.toURI().toString();
                    imgTag.attr("src", tempPath);

                    // 删除临时文件（应用结束后删除）
                    tempImage.deleteOnExit();
                    watermarkedImage.deleteOnExit();
                }
            }
        }
        return document.html();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化逻辑（如果需要）
    }

    @Override
    public void destroy() {
        // 销毁逻辑（如果需要）
    }

    // 检查 URL 是否有效
    private boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 从 URL 下载图片到本地临时文件
    private File downloadImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                File tempFile = File.createTempFile("image", ".tmp");
                try (InputStream inputStream = connection.getInputStream();
                     FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
                return tempFile;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 包装响应以捕获输出内容
    private static class CharResponseWrapper extends jakarta.servlet.http.HttpServletResponseWrapper {
        private StringWriter writer = new StringWriter();

        public CharResponseWrapper(jakarta.servlet.http.HttpServletResponse response) {
            super(response);
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriter(writer);
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            throw new UnsupportedOperationException("getOutputStream not supported");
        }

        @Override
        public String toString() {
            return writer.toString();
        }
    }
}

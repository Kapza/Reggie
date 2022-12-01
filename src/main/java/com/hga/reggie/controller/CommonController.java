package com.hga.reggie.controller;

import com.hga.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.http.HttpResponse;
import java.util.UUID;

/**
 * 文件的上传和下载
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${reggie.path}")
    private String basePath;

    @RequestMapping("/upload")
    public R<String> upload(MultipartFile file) throws IOException {
        log.info("file:{}",file.toString());
        //原始文件名
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //使用UUID重新生成文件名
        String fileName = UUID.randomUUID() +suffix;
        //创建一个目录对象
        File dir=new File(basePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //转存
        try {
            file.transferTo(new File(basePath+fileName));
            R.success("文件上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.error("文件上传失败");
    }

    /**
     * 文件下载
     * @param response
     * @param name
     */
    @GetMapping("/download")
    public void download(HttpServletResponse response, String name){
        //输入流来获取文件内容
        try {
            FileInputStream fileInputStream=new FileInputStream(new File(basePath+name));
            //输出流回写到游览器，在游览器展示图片
            ServletOutputStream outputStream=response.getOutputStream();
            //设置response文件类型
            response.setContentType("image/jpeg");
            //输入的同时输出
            int len=0;
            byte[] bytes=new byte[1024];
            while ((len=fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            //关闭资源
            outputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

package cn.ling.angelhack.api.service;

import java.io.FileOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

@Component
@Slf4j
public class UploadService {


    /**
     * 上传图片。
     */

    protected String savePic(String image) {
        // 只允许jpg
//        String header = "data:image/jpeg;base64,";
//        if (image.indexOf(header) != 0) {
//            return null;
//        }
//
//        // 去掉头部
//        image = image.substring(header.length());

        // 写入磁盘
        boolean success = false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] decodedBytes = decoder.decodeBuffer(image);
            Long now = System.currentTimeMillis();
            String imgFilePath = String.format("D://pics//img_%d.jpg", now);
            FileOutputStream out = new FileOutputStream(imgFilePath);
            out.write(decodedBytes);
            out.close();
            success = true;
            return imgFilePath;
        } catch (Exception e) {
            success = false;
            log.error("failed to save pic", e);
        }
        return null;

    }


}

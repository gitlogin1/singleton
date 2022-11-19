package test;

import java.io.InputStream;

public class ImageIo {
    public static void main(String[] args) {
        ImageIo imageIo = new ImageIo();
        InputStream inputStream = ImageIo.class.getClassLoader().getResourceAsStream("images/bulletD.gif");
        System.out.println(inputStream);
    }
}

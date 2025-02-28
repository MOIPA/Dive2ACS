package com.company.UDP;


import java.math.BigInteger;
import java.util.Arrays;

public class StringBytes {
    public static void main(String[] args) {
        String tmp = "hello";
        byte[] bytes = tmp.getBytes();
        System.out.println(bytes[0]);
        Byte[] bs = {0x0F, 0x1F, 0x2F, 0x3F, 0x4F, 0x5F, 0x6F};
        System.out.println(Arrays.toString(new Byte[]{bs[2], bs[3]}));

        byte[] data = {
                (byte) 0xfc, 0x00, (byte) 0xb7, 0x00
        };
//        System.out.println(Integer.toBinaryString(183));
//        System.out.println(bit2byte(Integer.toBinaryString(183)));
        for (Byte datum : data) {
            System.out.println(Integer.toBinaryString((datum & 0xFF) + 0x100).substring(1));
        }
//        System.out.println(new BASE64Encoder().encode(data));
//        System.out.println(Integer.toBinaryString(183));
        System.out.println(" 第二和第三个字节拼接后的十进制数");
        System.out.println(new BigInteger(1, new byte[]{data[2], data[3]}).toString(10));

        System.out.println("==========");
//        byte[] bytes1 = bit2byte(getBinaryString(Integer.toBinaryString(192), 16));
//        for (byte b : bytes1) {
//            System.out.println(b);
//        }
        UDPContent udpContent = new UDPContent(129, 155, 65531, 55555, 44444, 33333, 4194967296L, 22222);
        byte[] bytes2 = gendrateBytes(udpContent);
        for (byte b : bytes2) {
            System.out.println(Integer.toBinaryString((b & 0xFF) + 0x100).substring(1));
        }
    }

    private static String getBinaryString(String s, int strLen) {
//        String s = Integer.toBinaryString(num);
        if (s.length() < strLen) {
            for (int i = s.length(); i < strLen; i++) {
                s = "0" + s;
            }
        } else s = s.substring(s.length() - strLen);
        return s;
    }

    private static byte[] gendrateBytes(UDPContent udpContent) {
        try {
            byte[] res = new byte[16];
            // 1字节
            byte[] eigenValueByte = bit2byte(getBinaryString(Integer.toBinaryString(udpContent.getEigenValue()), 8));
            // 1字节
            byte[] spareBytebit2byte = bit2byte(getBinaryString(Integer.toBinaryString(udpContent.getSpare()), 8));
            // 车辆调远程指令id 2字节
            byte[] idByte = bit2byte(getBinaryString(Integer.toBinaryString(udpContent.getId()), 16));
            // 车辆调服务器编号 2字节
            byte[] serverCodeByte = bit2byte(getBinaryString(Integer.toBinaryString(udpContent.getServerCode()), 16));
            // 长度 2字节
            byte[] lengthByte = bit2byte(getBinaryString(Integer.toBinaryString(udpContent.getLength()), 16));
            // 数据 控制命令地址 2字节
            byte[] cmdAddressByte = bit2byte(getBinaryString(Integer.toBinaryString(udpContent.getCmdAddress()), 16));
            // 数据 控制命令数值 4字节
            byte[] cmdValueByte = bit2byte(getBinaryString(Long.toBinaryString(udpContent.getCmdValue()), 32));
            // CRC校验位 2字节
            byte[] crcByte = bit2byte(getBinaryString(Integer.toBinaryString(udpContent.getCrc()), 16));

            // 组装udp
            res[0] = eigenValueByte[0];
            res[1] = spareBytebit2byte[0];
            res[2] = idByte[0];
            res[3] = idByte[1];
            res[4] = serverCodeByte[0];
            res[5] = serverCodeByte[1];
            res[6] = lengthByte[0];
            res[7] = lengthByte[1];
            res[8] = cmdAddressByte[0];
            res[9] = cmdAddressByte[1];
            res[10] = cmdValueByte[0];
            res[11] = cmdValueByte[1];
            res[12] = cmdValueByte[2];
            res[13] = cmdValueByte[3];
            res[14] = crcByte[0];
            res[15] = crcByte[1];
            return res;
        } catch (Exception e) {
        }
        return null;
    }

    public static byte[] bit2byte(String bString) {
        byte[] res = new byte[bString.length() / 8];
        for (int k = 0; k < bString.length() / 8; k++) {
            String tmpStr = bString.substring(k * 8, k * 8 + 8);
            for (int i = tmpStr.length() - 1, j = 0; i >= 0; i--, j++) {
                res[k] += (Byte.parseByte(tmpStr.charAt(i) + "") * Math.pow(2, j));
            }
        }
        return res;
    }

}

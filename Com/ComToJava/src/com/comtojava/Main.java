package com.comtojava;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class Main {
    public static void main(String[] args) {
        try {
            ActiveXComponent dotnetCom = null;
            dotnetCom = new ActiveXComponent("ComCryption.Application");     //需要调用的C#代码中的命名空间名和类名。

            String strKey = "AaBbCcDd";
            String strToEncrypt = "I am Niu Xiaotong";
            String strToDecrypt = "";
            String strDecrypted = "";

            System.out.println("加密前的字符串"+strToEncrypt);  //原始的字符串。

            Variant varEn = Dispatch.call(dotnetCom, "EncryptDES", strToEncrypt, strKey);
            strToDecrypt = varEn.toString();  //返回需要的字符串
            System.out.println("加密后的字符串"+strToDecrypt);  //加密后得到的字符串。

            Variant varDe = Dispatch.call(dotnetCom, "DecryptDES", strToDecrypt, strKey);
            strDecrypted = varDe.toString();  //返回需要的字符串
            System.out.println("解密后的字符串"+strDecrypted);  //输出解密后的字符串。检查结果是否正确。

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
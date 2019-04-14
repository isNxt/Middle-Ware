using System;
using System.IO;
using System.Text;
using System.Runtime.InteropServices;
using System.Security.Cryptography;
/*
* DES 
* 对称加密算法 
* DES全称为Data Encryption Standard，即数据加密标准，是一种使用密钥加密的块算法，
* 1977年被美国联邦政府的国家标准局确定为联邦资料处理标准（FIPS），并授权在非密级政府通信中使用，随后该算法在国际上广泛流传开来。
*/
namespace ComCryption
{
    [Guid("83B046BF-5FFC-4B61-9794-3DAE3F9D2599")]
    public interface IMyClass
    {
        string EncryptDES(string str, string key); //DES加密
        string DecryptDES(string str, string key); //DES解密
    }

    [ClassInterface(ClassInterfaceType.None)]
    [Guid("124D6645-6B41-4C50-9939-BBBE149C1F65")]
    [ProgId("ComCryption.Application")]
    public class ComCryption : IMyClass
    {
        /*
         * DES加密功能
         * <param name="strToEncrypt">要加密的字符串</param>
         * <param name="strKey">密钥，必须为8位</param>
         * <returns>以Base64格式返回的加密字符串</returns>
         */
        public string EncryptDES(string strToEncrypt, string strKey)
        {
            using (DESCryptoServiceProvider des = new DESCryptoServiceProvider())
            {
                byte[] inputByteArray = Encoding.UTF8.GetBytes(strToEncrypt);
                des.Key = ASCIIEncoding.ASCII.GetBytes(strKey);
                des.IV = ASCIIEncoding.ASCII.GetBytes(strKey);
                System.IO.MemoryStream ms = new System.IO.MemoryStream();
                using (CryptoStream cs = new CryptoStream(ms, des.CreateEncryptor(), CryptoStreamMode.Write))
                {
                    cs.Write(inputByteArray, 0, inputByteArray.Length);
                    cs.FlushFinalBlock();
                    cs.Close();
                }
                string str = Convert.ToBase64String(ms.ToArray());
                ms.Close();
                return str;
            }
        }
        /*
         * DES解密功能
         * <param name="strToDecrypt">要解密的字符串</param>
         * <param name="strKey">密钥，必须为8位</param>
         * <returns>以Base64格式返回的加密字符串</returns>
         */

        public string DecryptDES(string strToDecrypt, string strKey)
        {
            byte[] inputByteArray = Convert.FromBase64String(strToDecrypt);
            using (DESCryptoServiceProvider des = new DESCryptoServiceProvider())
            {
                des.Key = ASCIIEncoding.ASCII.GetBytes(strKey);
                des.IV = ASCIIEncoding.ASCII.GetBytes(strKey);
                MemoryStream ms = new MemoryStream();
                using (CryptoStream cs = new CryptoStream(ms, des.CreateDecryptor(), CryptoStreamMode.Write))
                {
                    cs.Write(inputByteArray, 0, inputByteArray.Length);
                    cs.FlushFinalBlock();
                    cs.Close();
                }
                string str = Encoding.UTF8.GetString(ms.ToArray());
                ms.Close();
                return str;
            }
        }

    }
}


using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Net;
using System.Windows.Forms;
namespace SmartChef_Admin.Utils
{
    class FileUtil
    {
        public static string IP_SERVER = "192.168.43.140";
        private static string path = @"E:\InsertQuery.txt";
        public static void ExportQueryFile (String query) 
        {
            // If file does not exists , system have to create new File . 
            if (!File.Exists(path))
            {
                // Create a file to write to. 
                using (StreamWriter sw = File.CreateText(path))
                {
                    sw.WriteLine(query);
                }
            }
            else
            {
                using (StreamWriter sw = File.AppendText(path))
                {
                    sw.WriteLine(query);
                }
            }

        }
        public static string DownLoadFile (String url )
        {
            string file = System.IO.Path.GetFileName(url);
            MessageBox.Show(url);
            string saveFile = @"E:\Cong Nghe Phan Mem\Cong-Nghe-Phan-Mem-\WebServices\image\WebContent\" + file;
            
            string hostfile = "http://" + IP_SERVER + ":8070/image/"+file;
            WebClient cln = new System.Net.WebClient(); 
            cln.DownloadFile(url, saveFile);
            return hostfile; 
        }
    }
}

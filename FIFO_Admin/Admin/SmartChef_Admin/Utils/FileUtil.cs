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
            MessageBox.Show(file);
            string saveFile = @"E:\SmartChef\image\WebContent\" + file;
            
            string hostfile = "http://localhost:8070/image/"+file;
            WebClient cln = new System.Net.WebClient(); 
            cln.DownloadFile(url, saveFile);
            return hostfile; 
        }
    }
}

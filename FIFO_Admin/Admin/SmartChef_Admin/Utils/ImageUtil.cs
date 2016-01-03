using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using System.Net; 
namespace SmartChef_Admin.Utils
{
    class ImageUtil
    {
        //public static Image FromURL ( string url )
        //{
            
        //    HttpWebRequest request = HttpWebRequest.Create(url) as HttpWebRequest;
        //    HttpWebResponse respone = request.GetResponse() as HttpWebResponse;
        //    return Image.FromStream(respone.GetResponseStream(), true); 
        //}
        public static byte [] FromUrl (String url )
        {
            byte[] imageData = null; 
            try
            {
                WebClient client = new WebClient();
               imageData = client.DownloadData(url);
               
            }
            catch (Exception ex)
            {
               imageData = null ; 
            }

            return imageData; 
        }
    }
}

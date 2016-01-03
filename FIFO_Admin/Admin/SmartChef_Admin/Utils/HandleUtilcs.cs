using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Net.NetworkInformation;
namespace SmartChef_Admin.Utils
{
    class HandleUtilcs
    {
        public static String getIPAddress()
        {
            NetworkInterface[] ifaceList = NetworkInterface.GetAllNetworkInterfaces();
            string ip = "";
            int count = 0 ; 
            foreach (NetworkInterface iface in ifaceList)
            {
                if (iface.OperationalStatus == OperationalStatus.Up)
                {
                    UnicastIPAddressInformationCollection unicastIPC = iface.GetIPProperties().UnicastAddresses;
                    foreach (UnicastIPAddressInformation unicast in unicastIPC)
                    {
                        Console.WriteLine(unicast.Address.AddressFamily + "\t: " + unicast.Address);
                        if (count == 1)
                        {
                            ip = unicast.Address.ToString();
                        }
                        count++;
                    }
                }
            }
            return ip; 
        }
    }
}

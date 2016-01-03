using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MySql.Data.MySqlClient;
using SmartChef_Admin.Database;
namespace SmartChef_Admin
{
    public partial class Login : Form
    {
        private MysqlConectionService mysqlConectionService;
        public MySqlConnection con;
        DataTable dt = new DataTable("tblAdmin");

        MySqlDataAdapter da = new MySqlDataAdapter(); 
       
        public Login()
        {
            InitializeComponent();
        }

        private void lblpassword_Click(object sender, EventArgs e)
        {

        }
        public string getPassword(string email, string password)
        {
            string result = "";
            DataSet dataSet = mysqlConectionService.login(email, password);
            result = dataSet.ToString();
            result = dataSet.Tables[0].Columns.ToString();
            foreach (DataTable table in dataSet.Tables)
            {
                foreach (DataRow row in table.Rows)
                {
                    result = row["privilegeID"].ToString();
                }
            }
            return result;
        }
        private void btnLogin_Click(object sender, EventArgs e)
        {
           
            string name = ""; 
            name = txtname.Text;
            string pass = ""; 
            pass = txtpassword.Text;
            string prv = "";
            prv = getPassword(name, pass);
            if (prv != null)                               //co ton tai user
            {
                if (prv.Equals("1"))//                                 //kiem tra fai admin k
                {
                    SmartChef s = new SmartChef();
                    s.Show();
                    this.Visible = false;
                }
                else
                {
                    MessageBox.Show("Tên đăng nhập hoặc mật khẩu không đúng", "Error",MessageBoxButtons.OK);
                    txtname.Text = "";
                    txtpassword.Text = "";
                }
            }
            else
            {
                MessageBox.Show("Tên đăng nhập hoặc mật khẩu không đúng","Error",MessageBoxButtons.OK);
                txtname.Text = "";
                txtpassword.Text = "";
            }
            mysqlConectionService.CloseConnection();
        }

        private void Login_Load(object sender, EventArgs e)
        {
            mysqlConectionService = new MysqlConectionService();
            mysqlConectionService.OpenConnection();
        }
    }
}

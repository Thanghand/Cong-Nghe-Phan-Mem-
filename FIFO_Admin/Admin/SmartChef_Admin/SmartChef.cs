using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;
using SmartChef_Admin.Database;
using SmartChef_Admin.Model;
using SmartChef_Admin.Utils; 
namespace SmartChef_Admin
{
    public partial class SmartChef : Form
    {
        public SmartChef()
        {
            InitializeComponent();
        }
        private  int flagInsert = 0 ; 
        private MysqlConectionService mysqlConectionService ;

        private static string query = "select * from MEAL ; ";

        private void LoadMealData()
        {
            DataSet dataSet = mysqlConectionService.LoadData(query);
            dgvMeal.DataSource = dataSet.Tables[0].DefaultView;
          
        }

        

        private void Form1_Load(object sender, EventArgs e)
        {
            mysqlConectionService =  new MysqlConectionService();
            mysqlConectionService.OpenConnection();
            this.LoadMealData();
            cbbMealTypeID.SelectedIndex = 0;
            cbbFesID.SelectedIndex = 0;
            cbbNationID.SelectedIndex = 0;
            cbbDiet.SelectedIndex = 0;
            cbbInsertedBy.SelectedIndex = 0;
        }

        private void Form1_FormClosed(object sender, FormClosedEventArgs e)
        {
            mysqlConectionService.CloseConnection(); 
        }

        private void btnInsertMeal_Click(object sender, EventArgs e)
        {
            if (txtMealName.Text == "")
            {
                MessageBox.Show("Tên món ăn ko được để trống");
                return;
            }

            Meal meal = new Meal();
            meal.MealName = txtMealName.Text; 
            meal.MealTypeID = cbbMealTypeID.SelectedIndex + 1;
            meal.NationID = cbbNationID.SelectedIndex + 1;
            meal.FesID = cbbFesID.SelectedIndex + 1;
            meal.DietID = cbbDiet.SelectedIndex + 1;
            string url = txtMealPicture.Text; 
            string file = "" ; 
            if (  url.Contains("http://"))
            {
                file  = FileUtil.DownLoadFile(url);
             
                meal.MealPic = file; 
            }
          
            
            meal.Description = txtDescription.Text;
            meal.Tutorial = txtTutorial.Text;
            meal.InsertedBy = cbbInsertedBy.SelectedItem.ToString();
            mysqlConectionService.InsertMeal(meal);
            string query = DatabaseUtil.GetMealInsertQuery(meal);
            // Export File Query 
            FileUtil.ExportQueryFile(query); 
            MessageBox.Show("Inserting successfull!");
            flagInsert = 1; 
            this.LoadMealData();
            
        }

        private void btnUpdateMeal_Click(object sender, EventArgs e)
        {
            DialogResult dialogResult = MessageBox.Show("Are you sure you want to update this(these) row(s)", "Update row", MessageBoxButtons.YesNo);
            if (dialogResult == DialogResult.Yes)
            {
                Meal meal = new Meal();
                meal.MealName = txtMealName.Text;
                meal.MealTypeID = cbbMealTypeID.SelectedIndex + 1;
                meal.NationID = cbbNationID.SelectedIndex + 1;
                meal.FesID = cbbFesID.SelectedIndex + 1;
                meal.DietID = cbbDiet.SelectedIndex + 1;
                meal.MealPic = txtMealPicture.Text;
                meal.Description = txtDescription.Text;
                meal.Tutorial = txtTutorial.Text;
                foreach (DataGridViewRow row in this.dgvMeal.SelectedRows)
                {
                    
                    DataGridViewCell cell = row.Cells[0];
                    int id = int.Parse(cell.Value.ToString());
                    mysqlConectionService.UpdateMeal(meal,id);
                }

                MessageBox.Show("Updating successfull!");
                this.LoadMealData();
            }
            else if (dialogResult == DialogResult.No)
            {
                return;
            }
        }

        private void btnDeleteMeal_Click(object sender, EventArgs e)
        {
            DialogResult dialogResult = MessageBox.Show("Are you sure you want to delete this(these) row(s)", "Delete row", MessageBoxButtons.YesNo);
            if (dialogResult == DialogResult.Yes)
            {
                int checkLastRow = dgvMeal.Rows.Count;
                if (dgvMeal.SelectedRows[0].Index < checkLastRow - 1)
                foreach (DataGridViewRow row in this.dgvMeal.SelectedRows)
                {
                    DataGridViewCell cell = row.Cells[0];
                    int id = int.Parse(cell.Value.ToString());
                    mysqlConectionService.DeleteMeal(id);
                    dgvMeal.Rows.RemoveAt(row.Index);
                }

                //MessageBox.Show("Deleting successfull!");
            }
            else if (dialogResult == DialogResult.No)
            {
                return;
            }
        }

        private void dataGridView1_SelectionChanged(object sender, EventArgs e)
        {
           
            int checkLastRow = dgvMeal.Rows.Count - 1 ;
            dgvMeal.Rows[checkLastRow].Selected = false; 
            foreach (DataGridViewRow row in this.dgvMeal.SelectedRows)
            {
                    row.Selected = true; 
                    this.cbbNationID.SelectedIndex = int.Parse((row.Cells[1].Value.ToString())) - 1;
                    this.cbbMealTypeID.SelectedIndex = int.Parse((row.Cells[2].Value.ToString())) - 1;
                    this.cbbDiet.SelectedIndex = int.Parse((row.Cells[4].Value.ToString())) - 1;
                    this.cbbFesID.SelectedIndex = int.Parse((row.Cells[3].Value.ToString())) - 1;
                  
                    this.txtMealName.Text = row.Cells[5].Value.ToString();
                    this.txtMealPicture.Text = row.Cells[6].Value.ToString();
                    this.txtDescription.Text = row.Cells[8].Value.ToString();
                    this.txtTutorial.Text = row.Cells[9].Value.ToString();
                    String url = row.Cells[6].Value.ToString();
                    //if (url.Contains("http://"))
                    //{      
                    //    if (ImageUtil.FromUrl(url) != null)
                    //    {
                    //        this.pbMealPicture.Image = Image.FromStream(new MemoryStream(ImageUtil.FromUrl(url)));
                    //        this.pbMealPicture.SizeMode = PictureBoxSizeMode.StretchImage;
                    //    }

                    //}
              
               
            }

                
            try
            {
              
            }
            catch ( Exception ex )
            {
                throw ex; 
            }
                  
        }

        private void btnDownload_Click(object sender, EventArgs e)
        {
            string url = @"http://media.foody.vn/res/g9/88787/s125x125/foody-mr-seaweed-rong-bien-chay-toi-761-635533198034038123.JPG"; 
            string file = System.IO.Path.GetFileName(url);  
            string saveFile =  @"E:\" + file; 
            MessageBox.Show("Show: " + saveFile); 
            System.Net.WebClient cln = new System.Net.WebClient();
            cln.DownloadFile(url, saveFile);
        }

        private void btnClear_Click(object sender, EventArgs e)
        {
            this.txtMealName.Text = "";
            this.txtMealPicture.Text = "";
            this.txtDescription.Text = "";
            this.txtTutorial.Text = ""; 
        }

        private void btnChangeIP_Click(object sender, EventArgs e)
        {
            String destIP = txtDestIP.Text.ToString();
            String srcIP = txtSrcIP.Text.ToString();
            int i = 0; 
            
                foreach (DataGridViewRow row in this.dgvMeal.Rows)
                {

                    if (i < dgvMeal.Rows.Count - 1)
                    {
                        DataGridViewCell cellId = row.Cells[0];
                        int id = int.Parse(cellId.Value.ToString());
                        DataGridViewCell cellImageUrl = row.Cells[6];
                        string imageUrl = cellImageUrl.Value.ToString();

                        if (imageUrl.Contains(srcIP))
                        {
                            imageUrl = imageUrl.Replace(srcIP, destIP);
                            mysqlConectionService.UpdateHostImage(imageUrl, id);
                        }
                        i++;
                    }
                    else
                        break;             
                }
                this.LoadMealData();
        }

        private void pbMealPicture_Click(object sender, EventArgs e)
        {

        }

        private void txtSrcIP_TextChanged(object sender, EventArgs e)
        {

        }


    }
}

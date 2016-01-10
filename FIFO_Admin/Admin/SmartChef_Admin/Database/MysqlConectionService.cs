using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MySql.Data.MySqlClient ;
using System.Data;
using System.Windows.Forms;
using SmartChef_Admin.Model;
using SmartChef_Admin.Utils; 
namespace SmartChef_Admin.Database
{
    class MysqlConectionService
    {
        private string connectionString = "Server=" + FileUtil.IP_SERVER + ";Port=3306;Database=fifo;Uid=root;Pwd=1234;";

        private MySqlConnection mySqlConnection; 

        public void OpenConnection()
        {
            try
            {
                mySqlConnection = new MySqlConnection(connectionString);
                mySqlConnection.Open();
            }
            catch (Exception e)
            {
                MessageBox.Show("Error: " + e.Message);
                throw;
            }
        }

        public void CloseConnection()
        {
            if(mySqlConnection.State == ConnectionState.Open)
                mySqlConnection.Close();
        }

        public DataSet LoadData(String query)
        {
            DataSet dataSet = new DataSet();
            MySqlCommand cmd = mySqlConnection.CreateCommand();
            cmd.CommandText = query;
            cmd.ExecuteNonQuery();
            MySqlDataAdapter adapter = new MySqlDataAdapter(cmd);
            adapter.Fill(dataSet);
            return dataSet;
        }
        public DataSet login (String email, String password){
            DataSet dataSet = new DataSet();
            MySqlCommand cmd = mySqlConnection.CreateCommand();
            cmd.CommandText = "select privilegeID from user where email = @email and password = @password;";
            cmd.Parameters.AddWithValue("@email", email);
            cmd.Parameters.AddWithValue("@password", password);
            cmd.ExecuteNonQuery();
            MySqlDataAdapter adapter = new MySqlDataAdapter(cmd);
            adapter.Fill(dataSet);
            return dataSet;
        }

        public DataSet searchMeal(String nameMeal)
        {
            DataSet dataSet = new DataSet();
            MySqlCommand cmd = mySqlConnection.CreateCommand();
            string query = "select * from meal where meal.mealNameVN like '%" + nameMeal + "%'";
            cmd.CommandText = @query;
            cmd.Parameters.AddWithValue("@nameMeal", nameMeal);
            cmd.ExecuteNonQuery();
            MySqlDataAdapter adapter = new MySqlDataAdapter(cmd);
            adapter.Fill(dataSet);
            return dataSet;
        }
        public void InsertMeal(Meal meal)
        {
            try
            {
                MySqlCommand cmd = mySqlConnection.CreateCommand();
                cmd.CommandText = "insert into meal(mealTypeID,mealNameVN,mealPicture,descriptionVN,tutorialVN,dietID)"
                                       + "values (@mealTypeID,@mealName,@mealPic,@description,@tutorial,@dietID)";
                cmd.Parameters.AddWithValue("@mealTypeID", meal.MealTypeID);
                cmd.Parameters.AddWithValue("@mealName", meal.MealName);
                cmd.Parameters.AddWithValue("@mealPic", meal.MealPic);
                cmd.Parameters.AddWithValue("@description", meal.Description);
                cmd.Parameters.AddWithValue("@tutorial", meal.Tutorial);
                cmd.Parameters.AddWithValue("@dietID", meal.DietID);
                cmd.ExecuteNonQuery();
            }
            catch (Exception e)
            {
                MessageBox.Show("Error: " + e.Message);
                throw;
            }
        }

        public void UpdateMeal(Meal meal, int id)
        {
            try
            {
                MySqlCommand cmd = mySqlConnection.CreateCommand();
                cmd.CommandText = "update meal set mealTypeID=@mealTypeID,"
                                  + "mealNameVN=@mealName,mealPicture=@mealPic,descriptionVN=@description,tutorialVN=@tutorial,dietID=@dietID"
                                    + " where mealID=" + id;
                cmd.Parameters.AddWithValue("@mealTypeID", meal.MealTypeID);
                cmd.Parameters.AddWithValue("@mealName", meal.MealName);
                cmd.Parameters.AddWithValue("@mealPic", meal.MealPic);
                cmd.Parameters.AddWithValue("@description", meal.Description);
                cmd.Parameters.AddWithValue("@tutorial", meal.Tutorial);
                cmd.Parameters.AddWithValue("@dietID", meal.DietID);
                cmd.ExecuteNonQuery();
            }
            catch (Exception e)
            {
                MessageBox.Show("Error: " + e.Message);
                throw;
            }
        }
        public void UpdateHostImage(String imageUrl, int id )
        {
            try
            {
                MySqlCommand cmd = mySqlConnection.CreateCommand();
                cmd.CommandText = "update meal set mealPicture=@mealPic"
                                    + " where mealID=" + id;
                cmd.Parameters.AddWithValue("@mealPic", imageUrl);
                cmd.ExecuteNonQuery();
            }
            catch (Exception e)
            {
                MessageBox.Show("Error: " + e.Message);
                throw;
            }
         
        }
        public void DeleteMeal(int id)
        {
            try
            {
                MySqlCommand cmd = mySqlConnection.CreateCommand();
                cmd.CommandText = "delete from meal where mealID=" + id;
                cmd.ExecuteNonQuery();
            }
            catch (Exception e)
            {
                MessageBox.Show("Error: " + e.Message);
                throw;
            }
        }
    }
}

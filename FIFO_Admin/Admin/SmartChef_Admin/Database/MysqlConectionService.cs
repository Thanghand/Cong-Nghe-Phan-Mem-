using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MySql.Data.MySqlClient ;
using System.Data;
using System.Windows.Forms;
using SmartChef_Admin.Model;
namespace SmartChef_Admin.Database
{
    class MysqlConectionService
    {
        private string connectionString = "Server=localhost;Database=fifo;Uid=root;Pwd=1234;";

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

        public void InsertMeal(Meal meal)
        {
            try
            {
                MySqlCommand cmd = mySqlConnection.CreateCommand();
                cmd.CommandText = "insert into meal(nationID,mealTypeID,festivalID,mealNameVN,mealPicture,descriptionVN,tutorialVN,insertedByEmail,dietID)"
                                       + "values (@nationID,@mealTypeID,@fesID,@mealName,@mealPic,@description,@tutorial,@insertedBy,@dietID)";
                cmd.Parameters.AddWithValue("@nationID", meal.NationID);
                cmd.Parameters.AddWithValue("@mealTypeID", meal.MealTypeID);
                cmd.Parameters.AddWithValue("@fesID", meal.FesID);
                cmd.Parameters.AddWithValue("@mealName", meal.MealName);
                cmd.Parameters.AddWithValue("@mealPic", meal.MealPic);
                cmd.Parameters.AddWithValue("@description", meal.Description);
                cmd.Parameters.AddWithValue("@tutorial", meal.Tutorial);
                cmd.Parameters.AddWithValue("@insertedBy", meal.InsertedBy);
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
                cmd.CommandText = "update meal set nationID=@nationID,mealTypeID=@mealTypeID,festivalID=@fesID,"
                                  + "mealNameVN=@mealName,mealPicture=@mealPic,descriptionVN=@description,tutorialVN=@tutorial,dietID=@dietID"
                                    + " where mealID=" + id;
                cmd.Parameters.AddWithValue("@nationID", meal.NationID);
                cmd.Parameters.AddWithValue("@mealTypeID", meal.MealTypeID);
                cmd.Parameters.AddWithValue("@fesID", meal.FesID);
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

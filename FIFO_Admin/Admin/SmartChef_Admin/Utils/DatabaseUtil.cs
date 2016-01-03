using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SmartChef_Admin.Model; 
namespace SmartChef_Admin.Utils
{
    class DatabaseUtil
    {
        public static String GetMealInsertQuery(Meal meal)
        {
            String query = "insert into meal(nationID,mealTypeID,festivalID,mealNameVN,mealPicture,descriptionVN,tutorialVN,insertedByEmail,dietID)"
            + "values('" + meal.NationID + "','" + meal.MealTypeID + "','" + meal.FesID + "','" + meal.MealName
            + "','" + meal.MealPic + "','" + meal.Description + "','" + meal.Tutorial + "','" + meal.InsertedBy + "','"  + meal.DietID
            + "');";
            return query;
        }
    }
}

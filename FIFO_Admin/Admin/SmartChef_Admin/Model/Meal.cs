using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SmartChef_Admin.Model
{
    class Meal
    {
        
        private string mealName;
        public string MealName
        {
            get
            {
                return mealName;
            }
            set
            {
                mealName = value;
            }
        }

        private int nationID;
        public int NationID
        {
            get
            {
                return nationID;
            }
            set
            {
                nationID = value;
            }
        }

        private int mealTypeID;
        public int MealTypeID
        {
            get
            {
                return mealTypeID;
            }
            set
            {
                mealTypeID = value;
            }
        }

        private int timeID;
        public int TimeID
        {
            get
            {
                return timeID;
            }
            set
            {
                timeID = value;
            }
        }

        private int fesID;
        public int FesID
        {
            get
            {
                return fesID;
            }
            set
            {
                fesID = value;
            }
        }

        private string mealPic;
        public string MealPic
        {
            get
            {
                return mealPic;
            }
            set
            {
                mealPic = value;
            }
        }

        private string description;
        public string Description
        {
            get
            {
                return description;
            }
            set
            {
                description = value;
            }
        }

        private string tutorial;
        public string Tutorial
        {
            get
            {
                return tutorial;
            }
            set
            {
                tutorial = value;
            }
        }

        private string insertedBy;
        public string InsertedBy
        {
            get
            {
                return insertedBy;
            }
            set
            {
                insertedBy = value;
            }
        }
        private int dietId;
        public int DietID
        {
            get
            {
                return dietId; 
            }
            set
            {
                dietId = value;
            }
        }
    }
}

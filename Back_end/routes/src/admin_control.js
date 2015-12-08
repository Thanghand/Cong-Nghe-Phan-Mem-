// Add module 
var _ = require('underscore-node');
var fs = require('fs');
var generateTimeID = require('../utils/generateIDUtil.js');
// Declare variables 9
var mDatabase;
var usersCollection;
var restaurantCollection;
var adminControllService = {
    // Set database 
    setDatabase: function(database) {
        mDatabase = database;
        usersCollection = mDatabase.collection("demoCollection");
        restaurantCollection = mDatabase.collection("restaurantCollection");
    },
    login: function(req, res) {
        // Get data to compare 
        // console.log(req.body);
        var objectId = generateTimeID.initTimeStampID();
        var params = req.body;
        console.log(params);
        // Check account has existed 
        usersCollection.findOne({
            fullName: params.fullName
        }, function(err, item) {
            console.log(item);
            if (item == null && params.typeUser == "Admin") {
                var inputData = {
                    "_id": objectId,
                    "email": params.email,
                    "fullName": params.fullName,
                    "firstName": params.firstName,
                    "lastName": params.lastName,
                    "password": params.password,
                    "typeUser": params.typeUser,
                    "foodCollections": [{
                        "nameFoodCollection": "",
                        "descriptionCollection": "",
                        "listFoods": [{
                            "idFood": "",
                            "nameFood": "",
                            "priceOfFood": "",
                            "imageOfFood": "",
                            "rating": ""
                        }]
                    }]
                };
                usersCollection.insert(inputData, {
                    w: 1
                }, function(err, records) {
                    console.log("Record added as " + records);
                });
                res.json("Register Successfull");
            } else {
                console.log("Compare ");
                var response = "";
                if (item.fullName == params.fullName && params.password == item.password && params.email == item.email) {
                    response = item;
                } else
                    response = "Login Failed";
                res.json(response);
            }
        });

    },

    /*
     * This function use to insert or update data in database
     */

    insertRestaurant: function(req, res) {
        var params = req.body;
        var objectId = generateTimeID.initTimeStampID();
        var inputData = {
            "_id": objectId,
            "nameRestaurant": params.nameRestaurant,
            "addressOfRestaurant": params.addressOfRestaurant,
            "city": params.city,
            "district": params.district,
            "timeToOpen": params.timeToOpen,
            "timeToClose": params.timeToClose,
            "imageRestaurant": params.imageRestaurant,
            "userAdmin": params.userAdmin,
            "password": params.password,
            "listConfirmOrders": [{
                "idOrderBill": "",
                "total": "",
                "timeOrder": "",
                "status": "",
                "listBills": [{
                    "idFood": "",
                    "nameFood": "",
                    "priceOfFood": ""
                }]
            }]
        }
        restaurantCollection.insert(inputData, {
            w: 1,
        }, function(err, records) {
            console.log("Record added as " + records);
            res.json(records);
        });
    },

    /*
     * This function use to update restaurant
     */

    updateRestaurant: function(req, res) {
        var objectId = req.params.idRestaurant;
        var params = req.body;
        restaurantCollection.update({
            "_id": objectId
        }, {
            $set: {
                "nameRestaurant": params.nameRestaurant,
                "addressOfRestaurant": params.addressOfRestaurant,
                "timeToOpen": params.timeToOpen,
                "timeToClose": params.timeToClose,
                "imageRestaurant": params.imageRestaurant,
                "userAdmin": params.userAdmin,
                "password": params.password
            }
        }, function(err, result) {
            console.log(result);
            res.json(result);
        });
    },
    /*
     * This function use to get All Restaurant from database  
     */

    getAllRestaurant: function(req, res) {
        var listRestaurant = [];
        var cursor = restaurantCollection.find({});
        cursor.each(function(err, doc) {
            if (err) {
                console.log(err);
            } else {
                console.log('Fetched:', doc);
                listRestaurant.push(doc);
            }
        });
        res.json(listRestaurant);
    },

    /*
     * This function use to get detail infomation of restaurant 
     */

    getDetailRestaurant: function(req, res) {
        var idRestaurant = req.params.idRestaurant;
        restaurantCollection.findOne({
            "_id": idRestaurant
        }, function(err, item) {
            res.json(item);
        });
    },

    /*
     * This function use to get All Food  of restaurant 
     */

    getAllFoodOfRestaurant: function(req, res) {
        var idRestaurant = req.params.idRestaurant;
        restaurantCollection.findOne({
            "_id": idRestaurant
        }, function(err, item) {
            res.json(item.listFoods);
        });
    },

    /*
     * This function use to filter restaurant . For example : "City" , "District".
     */

    filterRestaurants: function(req, res) {
        var params = req.body;
        var listRestaurant = [];
        var cursor = restaurantCollection.find({
            "city": params.city,
            "district": params.district
        });
        cursor.each(function(err, doc) {
            if (err) {
                console.log(err);
            } else {
                console.log('Fetched:', doc);
                listRestaurant.push(doc);
            }
        });
        res.json(listRestaurant);
    },

    /*
     * This function use to search restaurant by keywords. 
     */

    searchRestaurants: function(req, res) {
        var keyWords = req.params.keyWords;
        var listRestaurant = [];
        var cursor = restaurantCollection.find({
            nameRestaurant: /keyWords/
        });
        cursor.each(function(err, doc) {
            if (err) {
                console.log(err);
            } else {
                console.log('Fetched:', doc);
                listRestaurant.push(doc);
            }
        });
        res.json(listRestaurant);
    }
};
module.exports = adminControllService;

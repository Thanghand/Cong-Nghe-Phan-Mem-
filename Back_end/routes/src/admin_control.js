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
    },
    login: function(req, res) {
        // Get data to compare 
        // console.log(req.body);
        var fullName = req.body.fullName;
        var lastName = req.body.lastName;
        var objectId = generateTimeID.initTimeStampID();
        // Check account has existed 
        usersCollection.findOne({
            fullName: fullName
        }, function(err, item) {
            console.log(item);
            if (item == null) {
                var inputData = {
                    "_id": objectId,
                    "fullName": fullName,
                    "lastName": lastName
                };
                usersCollection.insert(inputData, {
                    w: 1
                }, function(err, records) {
                    console.log("Record added as " + records);
                });
            }
        });



        res.json("OK");
    },

    insertOrUpdateRestaurant: function(req, res) {

    },

    getAllRestaurant: function(req, res) {

    },

    getDetailRestaurant: function(req, res) {

    },

    getAllFoodOfRestaurant: function(req, res) {

    }

};

module.exports = adminControllService;

// Add module 
var _ = require('underscore-node');
var fs = require('fs');
// Declare variables 
var mDatabase;
var usersCollection;
var restaurantCollection;
var adminControllService = {
    // Set database 
    setDatabase: function(database) {
        mDatabase = database;
        usersCollection = mDatabase.collection("UsersCollection");
    },
    login: function(req, res) {
        // Get data to compare 
        console.log(req.body.fullName);
        // usersCollection.findOne({
        //     "fullName": req.params.
        // }, function(err, item) {
        //     console.log('Found:', item);
        //     db.close();
        // });
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

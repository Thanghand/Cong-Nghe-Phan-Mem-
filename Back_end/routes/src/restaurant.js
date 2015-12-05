var _ = require('underscore-node');
var fs = require('fs');

var mDatabase;
var restaurantCollection = "RestaurantCollection";
var mRestaurantCollection;
var restaurants = {
    // Set collection from database
    setDatabase: function(database) {
        mDatabase = database;
        mRestaurantCollection = mDatabase.collection(restaurantCollection);
    },
    getAllFood: function(req, res) {

    },
    insertFood: function(req, res) {
        console.log(req.file);
        var imageName = req.file.originalname;
        fs.readFile(req.file.path, function(err, data) {
            /// If there's an error
            if (!imageName) {

                console.log("There was an error")
                res.redirect("/");
                res.end();

            } else {
                console.log("Begin 456");
                var newPath = __dirname + "/images/fullsize/" + imageName;
                console.log(__dirname);
                /// write file to uploads/fullsize folder
                fs.writeFile(newPath, data, function(err) {
                    if (err) {
                        console.log(err);
                    } else {
                        response = {
                            message: 'File uploaded successfully',
                            filename: req.file.originalname
                        };
                    }
                    console.log(response);
                    res.end(JSON.stringify(response));
                });
            }
        });
        // res.writeHead(200, {
        //     'Content-Type': 'text/plain'
        // });
        // res.end("Success");
    },
    getFoodByID: function(req, res) {

    }
};

module.exports = restaurants;

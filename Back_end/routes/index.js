var express = require("express");
var bodyParser = require('body-parser');
var app = express();
var multer  = require('multer');
/// Include the expre ss body parser
app.use(bodyParser.json());
app.use(express.static('public'));
app.use(bodyParser.urlencoded({ extended: false }));
var upload = multer({ dest: '/uploads/' });

var router = express.Router();
var auth = require('./auth.js');
var products = require('./products.js');
var user = require('./users.js');
var restaurants = require('./src/restaurant.js');
var upload = multer({ dest: '/src/images' });

// Database MongoDB 
var MongoClient = require('mongodb').MongoClient;
var database ;
MongoClient.connect("mongodb://localhost:27017/demo", function(err, db) {
	database = db ;
	userCollection = db.collection('Thangcollection');
	user.setDb(userCollection);
});

/*
 * Routes that can be accessed by any one
 */ 
router.post('/login', auth.login);

/*
 * Users
 */

router.get('/api/v1/admin/users', user.getAll);
router.get('/api/v1/admin/users/insert', user.insert);
router.get('/api/v1/admin/user/:id', user.getOne);
router.post('/api/v1/admin/user/', user.create);
router.put('/api/v1/admin/user/:id', user.update);
router.delete('/api/v1/admin/user/:id', user.delete);
/*
 * Restaurants
 */
console.log(restaurants);
router.post('/api/v1/restaurants/insertfood',upload.single('file') ,restaurants.insertFood);

// Export Mobulde Router
module.exports = router;
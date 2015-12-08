var express = require("express");
var bodyParser = require('body-parser');
var app = express();
var multer  = require('multer');
var MongoClient = require('mongodb').MongoClient;
/// Include the expre ss body parser
app.use(bodyParser.json());
app.use(express.static('public'));
app.use(bodyParser.urlencoded({ extended: false }));
var upload = multer({ dest: '/src/images/' });

var router = express.Router();
var auth = require('./auth.js');
var products = require('./products.js');
var user = require('./users.js');
var restaurants = require('./src/restaurant.js');
var adminControl = require('./src/admin_control.js');
var configDatabase = require('./utils/configDatabase.js');
// Database MongoDB 
// var MongoClient = require('mongodb').MongoClient;
var urlConnectToDatabase = configDatabase.typeSQL + "://" + configDatabase.host
 + ":" + configDatabase.port+ "/" + configDatabase.database;
 
MongoClient.connect(urlConnectToDatabase, function(err, db) {
	adminControl.setDatabase(db);
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

router.post('/api/v1/restaurants/insertfood',upload.single('file') ,restaurants.insertFood);
/*
 * Admin Controll Service 
 */ 
router.post('/api/admin/login', adminControl.login);

// Export Mobulde Router
module.exports = router;
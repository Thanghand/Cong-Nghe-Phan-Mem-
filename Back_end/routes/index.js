var express = require('express');
var router = express.Router();
var auth = require('./auth.js');
var products = require('./products.js');
var user = require('./users.js');
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

module.exports = router;
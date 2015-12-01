var express = require('express');
var router = express.Router();
 
var auth = require('./auth.js');
var products = require('./products.js');
var user = require('./users.js');
var places = require('./places.js');
var majors = require('./majors.js');
/*
 * Routes that can be accessed by any one
 */
router.post('/login', auth.login);
 

 
/*
 * Users
 */
router.get('/api/v1/admin/users', user.getAll);
router.get('/api/v1/admin/user/:id', user.getOne);
router.post('/api/v1/admin/user/', user.create);
router.put('/api/v1/admin/user/:id', user.update);
router.delete('/api/v1/admin/user/:id', user.delete);

/*
 * Places
 */
router.get('/api/v1/places', places.getPlaces);
router.get('/api/v1/places/:id', places.getDetailPlaces);
/*
 * Majors
 */
router.get('/api/v1/majors', majors.getAllMajor);

module.exports = router;
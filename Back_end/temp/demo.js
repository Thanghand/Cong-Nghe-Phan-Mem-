var Db = require('mongodb').Db,
    MongoClient = require('mongodb').MongoClient,
    ObjectID = require('mongodb').ObjectID
  //  BSON = require('mongodb').pure().BSON,
    assert = require('assert');

// Get a timestamp in seconds
var timestamp = Math.floor(new Date().getTime()/1000);
// Create a date with the timestamp
var timestampDate = new Date(timestamp*1000);

// Create a new ObjectID with a specific timestamp
var objectId = new ObjectID(timestamp);
console.log(objectId);
// Verify that the hex string is 24 characters long
assert.equal(24, objectId.toHexString().length);
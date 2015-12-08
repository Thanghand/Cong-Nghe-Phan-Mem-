var ObjectID = require('mongodb').ObjectID;

var generateTimeID = {

    initTimeStampID: function() {
        // Get a timestamp in seconds
        var timestamp = Math.floor(new Date().getTime() / 1000);
        // Create a date with the timestamp
        var timestampDate = new Date(timestamp * 1000);

        // Create a new ObjectID with a specific timestamp
        var objectId = new ObjectID(timestamp);
        return objectId;
    }
};

module.exports = generateTimeID;
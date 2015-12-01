var _ = require('underscore-node');
var fs = require('fs');
var contents = fs.readFileSync("../fake_backend/fake_data/data_major.json");
var allMajor = JSON.parse(contents);

var majors = {
  getAllMajor: function (req, res){
    res.json(allMajor.majors);
  }
}

module.exports = majors;

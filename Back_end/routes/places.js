var _ = require('underscore-node');
var fs = require('fs');
var contents = fs.readFileSync("../fake_backend/fake_data/data_places.json");
var allPlaces = JSON.parse(contents);
var places  = {

  getPlaces: function (req, res) {
    var listPlaces = [] 
    _.each(allPlaces.places,function (item){
      var newItem = {
        nameOfPlace: item.nameOfPlace,
        linkOfWebSite: item.linkOfWebSite,
        linkOfFacebook: item.linkOfFacebook,
        addressOfPlace: item.addressOfPlace
      };
      listPlaces.push(newItem);
    })
    res.json(listPlaces);
  },
  getDetailPlaces: function(req, res){
    var id = req.params.id;
    var data = _.find(allPlaces.places, function (item){
    
      return item.id == id ; 
    })
    res.json(data);
  }
};



module.exports = places;

var users = {
  getAll: function(req, res) {
    var allusers = data; // Spoof a DB call
    res.json(allusers);
  },
 
  getOne: function(req, res) {
    var id = req.params.id;
    var user = data[0]; // Spoof a DB call
    res.json(user);
  },
 
  create: function(req, res) {
    var newuser = req.body;
    data.push(newuser); // Spoof a DB call
    res.json(newuser);
  },
 
  update: function(req, res) {
    var updateuser = req.body;
    var id = req.params.id;
    data[id] = updateuser // Spoof a DB call
    res.json(updateuser);
  },
 
  delete: function(req, res) {
    var id = req.params.id;
    data.splice(id, 1) // Spoof a DB call
    res.json(true);
  },
  insert: function (req, res){
  
    var doc1 = {
      'Name': 'My Name is THang Hoang Cao Dep Trai Nhat Tu Truoc Toi Nay 1223333334555443432'
    };
    mCollection.insert(doc1);
    res.json("Insert Success");
  },
  setDb: function(collection) {
   // db = database ; 
   mCollection = collection; 
  }
};
var db ;
var mCollection;
var data = [{
  name: 'user 1',
  id: '1'
}, {
  name: 'user 2',
  id: '2'
}, {
  name: 'user 3',
  id: '3'
}];
 
module.exports = users;
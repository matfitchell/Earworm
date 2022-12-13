// essentially main for the angular 

//test data for the list, for testing purposes only
/*var testData = [{
    Name: "Mitchell",
    Location: "CSUN"
    },{
    Name: "Alvaro",
    Location: "USA"
    },{
    Name: "Nath",
    Location: "LA"
    },{
    Name: "Rodulfo",
    Location: "CSUN"
    },{
    Name: "Charles",
    Location: "Csun"
    }
];

//list
// will modify next time to get data from backend
var app = angular.module('displayList', []);
    app.controller("displayListCtlr", function($scope){

        // testing purposes only
        $scope.data = testData;
    });

*/

/*Log In to Sign Up page*/
var app = angular.module('login', []);
    app.controller("signUpClick", function($scope){
        $scope.login = true;
        $scope.signUp = true;
      

        $scope.showSignUp = function (){
            $scope.login = false;
            $scope.signUp = false;
        }
    });
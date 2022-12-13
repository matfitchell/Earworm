// essentially main for the angular 

//test data for the list, for testing purposes only
var testData = [{
    userName: "mitchman",
    password: "pass1",
    firstName:"Mitch",
    lastName: "Mercer"
    },{
    userName: "coolguy69",
    password: "pass2",
    firstName: "Cool",
    lastName: "Guy"
    },{
    userName: "charlesguy",
    password: "pass3",
    firstName: "Nick",
    lastName: "Cannon"
    },{
    userName: "slushieguy",
    password: "pass4",
    firstName: "Sarah",
    lastName: "Connor"
    },{
    userName: "lombocchew",
    password: "pass5",
    firstName: "Lewis",
    lastName: "Carrol"
    },{
    userName: "slapyamammy",
    password: "pass6",
    firstName: "Chris",
    lastName: "Brown"
    },{
    userName: "garusvyk",
    password: "pass7",
    firstName: "Leah",
    lastName: "Remini"
    },{
    userName: "loopertwo",
    password: "pass8",
    firstName: "Dawn",
    lastName: "Joy"
    },{
    userName: "partypooper2",
    password: "pass9",
    firstName: "Poopy",
    lastName: "Party"
    },{
    userName: "railgunner",
    password: "pass10",
    firstName: "Mark",
    lastName: "Twain"
    }
];

//list
// will modify next time to get data from backend
var app = angular.module('displayList', []);
    app.controller("displayListCtlr", function($scope){

        // testing purposes only
        $scope.data = testData;
    });



/*Log In to Sign Up page*/
var app = angular.module('login', []);
    app.controller("signUpClick", function($scope){
        $scope.login = true;
        $scope.signUp = true;
      

        $scope.showSignUp = function (){
            $scope.login = false;
            $scope.signUp = false;
        }

        $scope.backLogin = function (){
            $scope.login = true;
            $scope.signUp = true;
        }
        
    });

/*Homepage*/
var app = angular.module('homepage', []);
    app.controller("homepagectrl", function($scope){
        $scope.matchList = true;
        $scope.showUserProfile = false;
        $scope.userSettings = false;

        $scope.showMatchList= function (){
            $scope.matchList = true;
            $scope.showUserProfile = false;
            $scope.userSettings = false;
        }

        $scope.showProfile = function (){
            $scope.matchList = false;
            $scope.showUserProfile = true;
            $scope.userSettings = false;
        }
        
        $scope.showProfilet = function (){
            $scope.matchList = false;
            $scope.showUserProfile = false;
            $scope.userSettings = true;
        }

    });


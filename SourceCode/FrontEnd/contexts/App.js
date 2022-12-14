// essentially main for the angular 

//test data for the list, for testing purposes only
var testData = [{
    userName: "mitchman",
    password: "pass1",
    firstName:"Mitch",
    lastName: "Mercer",
    image: "/images/1.png" 
    },{
    userName: "coolguy69",
    password: "pass2",
    firstName: "Cool",
    lastName: "Guy",
    image: "/images/3.jpg" 
    },{
    userName: "charlesguy",
    password: "pass3",
    firstName: "Nick",
    lastName: "Cannon",
    image: "/images/1.png" 
    },{
    userName: "slushieguy",
    password: "pass4",
    firstName: "Sarah",
    lastName: "Connor",
    image: "/images/2.webp" 
    },{
    userName: "lombocchew",
    password: "pass5",
    firstName: "Lewis",
    lastName: "Carrol",
    image: "/images/3.jpg" 
    },{
    userName: "slapyamammy",
    password: "pass6",
    firstName: "Chris",
    lastName: "Brown",
    image: "/images/1.png" 
    },{
    userName: "garusvyk",
    password: "pass7",
    firstName: "Leah",
    lastName: "Remini",
    image: "/images/2.webp" 
    },{
    userName: "loopertwo",
    password: "pass8",
    firstName: "Dawn",
    lastName: "Joy",
    image: "/images/3.jpg" 
    },{
    userName: "partypooper2",
    password: "pass9",
    firstName: "Poopy",
    lastName: "Party",
    image: "/images/1.png"
    },{
    userName: "railgunner",
    password: "pass10",
    firstName: "Mark",
    lastName: "Twain",
    image: "/images/1.png" 
    }
];

//list
// will modify next time to get data from backend
/*var app = angular.module('displayList', []);
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

        $scope.data = testData;

    });

    
function showDiv() {
        document.getElementById('center').style.display = "block";
        location.href="pages/homepage.html";
        alert("All Signed Up! Welcome!");
        
     }

